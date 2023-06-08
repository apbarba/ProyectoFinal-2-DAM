import { Component, OnInit } from '@angular/core';
import { ObrasService } from '../../services/obras.service';
import { Obra } from '../../models/obra.model';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { User } from 'src/app/models/user.model';
import { Observable, map } from 'rxjs';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-obras',
  templateUrl: './obras.component.html',
  styleUrls: ['./obras.component.css']
})
export class ObrasComponent implements OnInit {

  obras: Obra[] = [];
  private userId: string | undefined;
  imageUrls: SafeUrl[] = [];
  img: string[] = [];

  constructor(private obrasService: ObrasService, private router: Router, private authService: AuthService, private http: HttpClient, private sanitizer: DomSanitizer){
  }
  
  ngOnInit(): void {
   this.obrasService.getAllObras().subscribe((data: any) => {
    console.log(data);
    this.obras = data.content as Obra[];
   });

   this.authService.currentUser.subscribe(user => {
    this.userId = user.id;
  });

  this.obrasService.getAllObras().subscribe((data: any) => {
    this.obras = data.content as Obra[];

    // Construir la URL completa de la imagen
    for (let obra of this.obras) {
      const imageUrl = `http://localhost:8080/${obra.img}`;
      this.imageUrls.push(imageUrl);
    }
  })
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
    if (obra.favorito && this.userId) {
      this.authService.addFavorito(this.userId, obra.id).subscribe(data => {
        console.log(data);
      }, error => {
        console.log(error);
      });
    }
  }


  cargarFavoritos() {
    this.authService.getFavoritosUsuarioActual().subscribe(obras => {
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

  getImageUrl(img: string): Observable<SafeUrl> {
    const url = 'http://localhost:8080/' + img;
    return this.http.get(url, { responseType: 'blob' }).pipe(
      map((blob: Blob | MediaSource) => this.sanitizer.bypassSecurityTrustUrl(URL.createObjectURL(blob)))
    );
  
}
}
