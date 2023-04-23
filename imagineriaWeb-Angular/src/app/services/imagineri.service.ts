import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'src/environments/enviroments';

@Injectable({
  providedIn: 'root'
})
export class ImagineriService {
  constructor(private http: HttpClient, private router: Router) { }


  getAllImagineros() {
    return this.http.get(`${environment.apiUrl}/imaginero/`);
  }   

  createImaginero(name: string, edad: number, localidad: string) {
    const body = {
      name: name,
      edad: edad,
      localidad: localidad
    };
    return this.http.post(`${environment.apiUrl}/imaginero/`, body);
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
