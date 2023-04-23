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

  getAllObras() {
    return this.http.get(`${environment.apiUrl}/obras/`);
  }

  createObra(obra: Obra) {
    console.log(obra);
    return this.http.post(`${environment.apiUrl}/obras/`, {
      "precio": obra.precio,
      "name": obra.name,
      "titulo": obra.titulo,
      "estado": obra.estado,
      "estilo": obra.estilo,
      "categoria": obra.categoria
    });
  }
}
