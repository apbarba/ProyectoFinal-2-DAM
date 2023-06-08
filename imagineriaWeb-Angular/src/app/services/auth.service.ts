import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { BehaviorSubject, Observable, map, switchMap, tap } from 'rxjs';
import { Router } from '@angular/router';
import { environment } from 'src/environments/enviroments';
import { CookieService } from 'ngx-cookie-service';
import { User } from '../models/user.model';
import { Obra } from '../models/obra.model';
import { LoginResponse } from '../models/LoginResponse.model';

@Injectable({
  providedIn: 'root',
})
export class AuthService {

  public currentUserSubject: BehaviorSubject<User>;

  constructor(private http: HttpClient, private cookies: CookieService, private router: Router) {
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
    }).pipe(
      tap((data: any) => {
        const user: User = {
          email: data.email,
          id: data.id,
          username: data.username,
          avatar: data.avatar
        };
        this.currentUserSubject.next(user);
        this.setToken(data.token);
      })
    );
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

  changePassword(oldPassword: string, newPassword: string, verifyNewPassword: string, user: User): Observable<any> {
    const changePasswordRequest = {
      oldPassword,
      newPassword,
      verifyNewPassword
    };
    return this.http.put(`${environment.apiUrl}/user/changePassword`, changePasswordRequest, {
      headers: {
        Authorization: `Bearer ${user.token}`
      }
    });
  }

  addFavorito(userId: string, obraId: string): Observable<User> {
    return this.http.post<User>(`${environment.apiUrl}/user/${userId}/favoritos/${obraId}`, {});
  }
 
  public getUserId(): Observable<string> {
    return this.currentUser.pipe(
      map((user: { id: any; }) => user.id)
    );
  }

  getFavoritos(userId: string): Observable<Obra[]> {
    return this.http.get<Obra[]>(`${environment.apiUrl}/user/${userId}/favoritos`);
  }

  getFavoritosUsuarioActual(): Observable<Obra[]> {
    return this.currentUser.pipe(
      switchMap((user: User) => {
        return this.http.get<Obra[]>(`${environment.apiUrl}/user/${user.id}/favoritos`);
      })
    );
  }

  deleteFavoritos(userId: string, obraId: string):Observable<Obra[]>{
    return this.http.delete<Obra[]>(`${environment.apiUrl}/user/${userId}/favoritos/${obraId}`);
  }

}
