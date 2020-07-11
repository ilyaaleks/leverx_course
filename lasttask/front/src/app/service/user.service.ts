import {Injectable} from '@angular/core';
import {Observable, ReplaySubject, Subject} from 'rxjs';
import {User} from '../model/user';
import {HttpClient} from '@angular/common/http';
import {JwtHelperService} from '@auth0/angular-jwt';
import {PhotoPath} from '../model/photo-path';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private _activeUser: Subject<User> = new ReplaySubject(1);

  get activeUser(): Subject<User> {
    return this._activeUser;
  }

  set activeUser(value: Subject<User>) {
    this._activeUser = value;
  }

  constructor(private httpClient: HttpClient) {
    let token = localStorage.getItem('token');
    const helper = new JwtHelperService();
    const decodedToken = helper.decodeToken(token);
    if (token != null) {
      let user: User = {
        id: decodedToken.id,
        name: decodedToken.name,
        lastName: decodedToken.lastName,
        username: decodedToken.username,
        photoUrl: decodedToken.photoUrl,
        email: decodedToken.email,
        links: null,
        comments: null
      };
      this.activeUser.next(user);
    } else {
      this.activeUser.next(null);
    }
  }

  saveUser(user: User): Observable<User> {
    return this.httpClient.post<User>('/api/registration', user);
  }

  updateCurrentUser(user: User): void {
    this.httpClient.put<User>('/api/user', user).subscribe((token: any) => {
      const helper = new JwtHelperService();
      const decodedToken = helper.decodeToken(token.token);
      localStorage.removeItem('token');
      localStorage.setItem('token', token.token);
      let user: User = {
        id: decodedToken.id,
        lastName: decodedToken.lastName,
        name: decodedToken.name,
        username: decodedToken.username,
        email: decodedToken.email,
        photoUrl: decodedToken.photoUrl,
        links: null,
        comments: null
      };
      this.activeUser.next(user);
    }, (error => {
      console.log("Error: "+error.message);
    }));
  }
  updatePhoto(file: File,username:string):Observable<PhotoPath>
  {
    const formData:FormData=new FormData();
    formData.append('file',file);
    formData.append('username',username);
    return this.httpClient.post<PhotoPath>("/api/user",formData);
  }
  getUserById(id:number):Observable<User>
  {
    return this.httpClient.get<User>("/api/user/"+id);
  }
}
