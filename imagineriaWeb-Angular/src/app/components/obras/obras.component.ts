import { Component, OnInit } from '@angular/core';
import { ObrasService } from '../../services/obras.service';
import { Obra } from '../../models/obra.model';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { User } from 'src/app/models/user.model';

@Component({
  selector: 'app-obras',
  templateUrl: './obras.component.html',
  styleUrls: ['./obras.component.css']
})
export class ObrasComponent implements OnInit {

  obras: Obra[] = [];
  
  constructor(private obrasService: ObrasService, private router: Router, private authService: AuthService){}
  
  ngOnInit(): void {
   this.obrasService.getAllObras().subscribe((data: any) => {
    console.log(data);
    this.obras = data.content as Obra[];
   });
  }

  editar(id: string) {
    this.router.navigate(['/obras/editar', id]);
  }

  eliminar(id: string) {
    if (confirm('¿Está seguro de eliminar esta obra?')) {
      this.obrasService.deleteObra(id).subscribe(() => {
        this.ngOnInit();
      });
    }
  }

  toggleFavorito(obra: any) {
    obra.favorito = !obra.favorito;
    if (obra.favorito) {
      this.authService.getUserId().subscribe(userId => {
        this.authService.addFavorito(userId, obra.id).subscribe(data => {
          console.log(data);
        }, error => {
          console.log(error);
        });
      });
    }
  }

  cargarFavoritos() {
    this.authService.getFavoritosUsuarioActual().subscribe(obras => {
      this.obras = obras;
    });
  }
  
}
