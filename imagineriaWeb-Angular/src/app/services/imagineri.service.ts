import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'src/environments/enviroments';
import { Imaginero } from '../models/imaginero.model';

@Injectable({
  providedIn: 'root'
})
export class ImagineriService {
  constructor(private http: HttpClient, private router: Router) { }


  getAllImagineros() {
    return this.http.get(`${environment.apiUrl}/imaginero/`);
  }   

  createImaginero(imaginero: Imaginero){
    console.log(imaginero)
    return this.http.post(`${environment.apiUrl}/imaginero/`, {
      "name": imaginero.name,
      "edad": imaginero.edad,
      "localidad": imaginero.localidad
    });
  }
  editImaginero(id: string, edad: number, localidad: string){
    const body = {
      edad: edad,
      localidad: localidad
    };
    return this.http.put(`${environment.apiUrl}/imaginero/${id}`, body);
  }

  getImagineroById(id: string) {
    return this.http.get(`${environment.apiUrl}/imaginero/${id}`);
  }
}
