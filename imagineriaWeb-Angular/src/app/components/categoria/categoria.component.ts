import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Categoria } from 'src/app/models/categoria.model';
import { CategoriaService } from 'src/app/services/categoria.service';

@Component({
  selector: 'app-categoria',
  templateUrl: './categoria.component.html',
  styleUrls: ['./categoria.component.css']
})
export class CategoriaComponent implements OnInit{
  
  categorias: Categoria[] = [];
  
  constructor(private categoriaService: CategoriaService, private router: Router){}

  ngOnInit(): void {
    this.categoriaService.getAllCategorias().subscribe((data: any) => {
      this.categorias = data.content as Categoria[];
    })
  }

  editar(id: string){
    this.router.navigate(['/categoria/editar', id]);
  }

  eliminar(id: string) {
    if (confirm('¿Está seguro de eliminar esta categoria?')) {
      this.categoriaService.deleteCategoria(id).subscribe(() => {
        this.ngOnInit();
      });
    }
  }

  verObras(id: string){
    this.router.navigate(['/categoria', id, 'obras']);
  }

}
