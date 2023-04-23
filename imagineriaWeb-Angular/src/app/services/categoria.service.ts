import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'src/environments/enviroments';

@Injectable({
  providedIn: 'root'
})
export class CategoriaService {
  constructor(private http: HttpClient, private router: Router) { }


  getAllCategorias() {
    return this.http.get(`${environment.apiUrl}/categoria/`);
  }   
}