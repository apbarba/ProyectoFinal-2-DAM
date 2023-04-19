import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'src/environments/enviroments';

@Injectable({
  providedIn: 'root'
})
export class ImagineriService {

  private token = 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhYzE2ZDAwMS04NzlhLTFiZDgtODE4Ny05YWViZTQ4NTAwMDAiLCJpYXQiOjE2ODE5MzEzNzEsImV4cCI6MTE1NjI3MjUzMTF9.aCBVmAYv0VnBQCiP4CAh_9v6-2Jre1lhbTBTZOGBBj_5RJF71yvxfelWibHK2Kik6yeaBCBNCuGelNMNOL1PNw';


  constructor(private http: HttpClient, private router: Router) { }


  getAllImagineros(){
    const headers = new HttpHeaders().set('Authorization', `Bearer ${this.token}`)
    return this.http.get(`${environment.apiUrl}/imaginero/`, { headers });
  }   

  createImaginero(name: string, edad: number, localidad: string){
    const headers = new HttpHeaders().set('Authorization', `Bearer ${this.token}`);
    const body = {
      name: name,
      edad: edad,
      localidad: localidad
    };
    return this.http.post(`${environment.apiUrl}/imaginero/`, body, { headers });
  } 
  
  editImaginero(id: string, edad: number, localidad: string){
    const headers = new HttpHeaders().set('Authorization', `Bearer ${this.token}`);
    const body = {
      edad: edad,
      localidad: localidad
    };
    return this.http.put(`${environment.apiUrl}/imaginero/${id}`, body, { headers });
  }

  getImagineroById(id: string) {
    const headers = new HttpHeaders().set('Authorization', `Bearer ${this.token}`);
    return this.http.get(`${environment.apiUrl}/imaginero/${id}`, { headers });
  }
}
