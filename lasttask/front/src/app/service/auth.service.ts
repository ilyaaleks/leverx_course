import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {UserService} from './user.service';
import {UserLogin} from '../model/user-login';
import {JwtHelperService} from '@auth0/angular-jwt';
import {User} from '../model/user';
@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient,
              private userService: UserService,
              private router: Router) {
  }

  public getToken(): string {
    return localStorage.getItem('token');
  }

  login(username: string, password: string) {
    const user: UserLogin = {
      username: username,
      password: password
    };
    return this.http.post<any>('api/auth/token', user).subscribe(
      (token: any) => {
        const helper = new JwtHelperService();
        const decodedToken = helper.decodeToken(token.token);
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
        this.userService.activeUser.next(user);
        this.router.navigate(['/story']);
      });
  }

  logout() {
    localStorage.removeItem('token');
    this.userService.activeUser.next(null);
    this.router.navigate(['/home']);
    this.http.post('api/auth/logout', null).subscribe();
  }
  //
  // public isAuthenticated(): boolean {
  //   const token = this.getToken();
  //   return tokenNotExpired(null, token);
  // }
}
