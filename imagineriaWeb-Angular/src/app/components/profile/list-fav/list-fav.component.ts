import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Obra } from 'src/app/models/obra.model';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-list-fav',
  templateUrl: './list-fav.component.html',
  styleUrls: ['./list-fav.component.css']
})
export class ListFavComponent {

  obras: Obra[] = [];

  constructor(private authService: AuthService, private router: Router){}

  ngOnInit(): void {
    this.cargarFavoritos();
  }

  cargarFavoritos() {
    this.authService.getFavoritosUsuarioActual().subscribe(obras => {
      console.log(obras); // Agrega esta línea para verificar la respuesta de la petición
      this.obras = obras;
    });
  }

  eliminarFavorito(obra: any){
    this.authService.getUserId().subscribe(userId => {
      this.authService.deleteFavoritos(userId, obra.id).subscribe(data => {
        console.log(data)
        this.ngOnInit()
      })
    })
  }  

}
