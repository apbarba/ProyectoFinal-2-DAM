import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'src/environments/enviroments';
import { Obra } from '../models/obra.model';

@Injectable({
  providedIn: 'root'
})
export class ObrasService {

  constructor(private http: HttpClient, private router: Router) { }

  getAllObras(){
    const token = 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhYzE2ZDAwMS04NzhlLTEyMGItODE4Ny04ZTcyMTg2MzAwMDAiLCJpYXQiOjE2ODE3MjIzMTYsImV4cCI6MTE1NjI1MTYyNTZ9.YFCYaGV6C9nN2sFIJGxyS4lZBdjNbA2CUuXJ5Uexm-6rq05iUL_OtWLiRfvpJLPW_fdZQBiUFThXNm5UwCwl3g';
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`)
    return this.http.get(`${environment.apiUrl}/obras/`, { headers });
  }

  createObra(obra: Obra){
    const token = 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhYzE2ZDAwMS04NzhlLTEyMGItODE4Ny04ZTcyMTg2MzAwMDAiLCJpYXQiOjE2ODE3MjIzMTYsImV4cCI6MTE1NjI1MTYyNTZ9.YFCYaGV6C9nN2sFIJGxyS4lZBdjNbA2CUuXJ5Uexm-6rq05iUL_OtWLiRfvpJLPW_fdZQBiUFThXNm5UwCwl3g';
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`)
    return this.http.post(`${environment.apiUrl}/obras/`, obra, { headers });
  }
}
