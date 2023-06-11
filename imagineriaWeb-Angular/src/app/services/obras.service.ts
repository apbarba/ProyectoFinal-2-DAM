import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'src/environments/enviroments';
import { Obra } from '../models/obra.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ObrasService {

  constructor(private http: HttpClient, private router: Router) { }

  getAllObras(page: number): Observable<any> {
    return this.http.get(`${environment.apiUrl}/obras/?page=${page}`);
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

  editObra(id: string, name: string, precio: number, titulo: string, estado: string, estilo: string, img: string){
    const body = {
      name: name,
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

  deleteObra(id: string) {
    return this.http.delete(`${environment.apiUrl}/obras/${id}`);
  }

  getImage(img: string): Observable<Blob> {
    return this.http.get(img, { responseType: 'blob' });
  }
  buscarObraPorNombre(name: string) {
    return this.http.get<Obra[]>(`${environment.apiUrl}/obras/search?name=${name}`);
  }

}
