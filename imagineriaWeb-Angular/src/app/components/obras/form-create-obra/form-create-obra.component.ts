import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { Obra } from 'src/app/models/obra.model';
import { CategoriaService } from 'src/app/services/categoria.service';
import { ObrasService } from 'src/app/services/obras.service';

@Component({
  selector: 'app-form-create-obra',
  templateUrl: './form-create-obra.component.html',
  styleUrls: ['./form-create-obra.component.css']
})
export class FormCreateObraComponent implements OnInit {

  categorias: { id: string, nombre: string } [] = [];

  nuevaObra: Obra = { // Crear un objeto literal que representa una nueva obra
    estado: '',
    estilo: '',
    titulo: '',
    fecha: '',
    id: '',
    img: '',
    nombre: '',
    precio: 0,
    categoria: '',
    favorito: false,
    name: ''
  };

  constructor(private obrasService: ObrasService, private categoriaService: CategoriaService, private router: Router, private snackBar: MatSnackBar){}
 
  ngOnInit(): void {
    this.categoriaService.getAllCategorias().subscribe((data: any) => {
      this.categorias = data.content.map((item: any) => {
        return {
          id: item.id,
          nombre: item.nombre
        }
      })
    });
  }

  agregarObra() {
    this.obrasService.createObra(this.nuevaObra).subscribe(
      (response: any) => {
        this.router.navigateByUrl("/obras")
      },
      (error: any) => {
        this.snackBar.open(error.error.mensaje, 'close', {
          duration: 5000, 
          verticalPosition: 'top'
        })
      }
    );
  }

}
