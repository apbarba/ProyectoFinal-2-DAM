import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'src/environments/enviroments';

@Injectable({
  providedIn: 'root'
})
export class ObrasService {

  constructor(private http: HttpClient, private router: Router) { }

  getAllObras(){
    const token = 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhYzE2ZDAwMS04Nzg5LTE4NDEtODE4Ny04OTk4NGIwODAwMDAiLCJpYXQiOjE2ODE2NDA2ODYsImV4cCI6MTE1NjI0MzQ2MjZ9.fCZrJFbmiSts5MkqPHl4UMVPg3RgNFk28ZaHchs8Jvpe1mRDjW9LfA1uofTIrLmbkSwe2CTYAqnxBd5POSeRQg';
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`)
    return this.http.get(`${environment.apiUrl}/obras/`, { headers });
  }
}
