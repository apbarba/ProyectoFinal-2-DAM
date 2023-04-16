import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { Router } from '@angular/router';
import { environment } from 'src/environments/enviroments';
import { CookieService } from 'ngx-cookie-service';

@Injectable({
  providedIn: 'root',
})
export class AuthService {

  constructor(private http: HttpClient, private router: Router, private cookies: CookieService) { }

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
    return this.http.get(`${environment.apiUrl}me`);
  }

  getUserLogged() {
    const token = this.getToken();
  }

}
