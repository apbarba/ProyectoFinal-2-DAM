import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import { Router } from '@angular/router';
import { environment } from 'src/environments/enviroments';
import { CookieService } from 'ngx-cookie-service';
import { User } from '../models/user.model';

@Injectable({
  providedIn: 'root',
})
export class AuthService {

  public currentUserSubject: BehaviorSubject<User>;

  constructor(private http: HttpClient, private cookies: CookieService) {
    this.currentUserSubject = new BehaviorSubject<User>({email: '', id: '', username: '', avatar:''});
    const token = this.getToken();
    if (token) {
      const decodedToken = this.getDecodedToken(token);
      const user: User = {
        email: decodedToken.email,
        token: token,
        refreshToken: decodedToken.refresh_token,
        id: decodedToken.sub,
        username: decodedToken.username,
        avatar: decodedToken.avatar
      };
      this.currentUserSubject.next(user);
    }
  }

  public get currentUser(): Observable<User> {
    return this.currentUserSubject.asObservable();
  }

  login(username: string, password: string): Observable<any> {
    return this.http.post(`${environment.apiUrl}/auth/login`, {
      username,
      password
    });
  }

  register(name: string, username: string, email: string, password: string, verifyPassword: string): Observable<any> {
    return this.http.post(`${environment.apiUrl}/auth/register`, {
      name,
      username,
      email,
      password,
      verifyPassword
    });
  }

  setToken(token: string) {
    this.cookies.set("token", token);
  }

  getToken() {
    return this.cookies.get("token");
  }

  getUser() {
    return this.http.get<User>(`${environment.apiUrl}me`);
  }

  getDecodedToken(token: string) {
    try {
      const base64Url = token.split('.')[1];
      const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
      return JSON.parse(atob(base64));
    } catch (error) {
      return null;
    }
  }

  logout() {
    this.cookies.delete("token");
    const emptyUser: User = {
      email: '',
      id: '',
      username: '',
      avatar:''
    };
    this.currentUserSubject.next(emptyUser);
  }


}
