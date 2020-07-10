import {Injectable} from '@angular/core';
import {Observable, ReplaySubject, Subject} from 'rxjs';
import {User} from '../model/user';
import {HttpClient} from '@angular/common/http';
import {JwtHelperService} from '@auth0/angular-jwt';

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
    let token = localStorage.getItem("token");
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
      }
      this.activeUser.next(user);
    }
    else
    {
      this.activeUser.next(null);
    }
  }
  saveUser(user: User): Observable<User> {
    return this.httpClient.post<User>("/api/registration",user);
  }
}
