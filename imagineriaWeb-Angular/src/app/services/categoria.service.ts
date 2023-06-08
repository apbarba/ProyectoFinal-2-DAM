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

  getObrasByCategoria(id: string) {
    return this.http.get(`${environment.apiUrl}/categoria/${id}`);
  }  
  
  editCategoria(id: string, nombre: string, descripcion: string){
    const body = {
      nombre: nombre,
      descripcion: descripcion
    };
    return this.http.put(`${environment.apiUrl}/categoria/${id}`, body);
  }

  getCategoriaById(id: string) {
    return this.http.get(`${environment.apiUrl}/categoria/${id}`);
  }

  deleteCategoria(id: string) {
    return this.http.delete(`${environment.apiUrl}/categoria/${id}`);
  }
}