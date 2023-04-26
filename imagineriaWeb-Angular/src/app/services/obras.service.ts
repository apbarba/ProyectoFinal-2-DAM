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
      "name": obra.nombre,
      "titulo": obra.titulo,
      "estado": obra.estado,
      "estilo": obra.estilo,
      "categoria": obra.categoria
    });
  }

  editObra(id: string, nombre: string, precio: number, titulo: string, estado: string, estilo: string, img: string){
    const body = {
      nombre: nombre,
      precio: precio,
      titulo: titulo,
      estado:estado,
      estilo: estilo,
      img: img
    };
    return this.http.put(`${environment.apiUrl}/obras/${id}`, body);
  }

  getObraById(id: string) {
    return this.http.get(`${environment.apiUrl}/obras/${id}`);
  }
}
