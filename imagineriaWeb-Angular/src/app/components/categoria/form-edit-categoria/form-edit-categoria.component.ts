import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { Categoria } from 'src/app/models/categoria.model';
import { CategoriaService } from 'src/app/services/categoria.service';

@Component({
  selector: 'app-form-edit-categoria',
  templateUrl: './form-edit-categoria.component.html',
  styleUrls: ['./form-edit-categoria.component.css']
})
export class FormEditCategoriaComponent implements OnInit{
  
  categoria: Categoria = {
    id: '',
    nombre: '',
    descripcion: '',
    obras:[]
  }

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private categoriaService: CategoriaService
  ) {}
  
  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id') ?? '';
    this.categoriaService.getCategoriaById(id).subscribe((categoria) => {
      this.categoria = categoria as Categoria;
    });
  }

  submitForm(form: NgForm) {
    const id = this.categoria.id;
    const { nombre, descripcion } = form.value;
    this.categoriaService.editCategoria(id, nombre, descripcion).subscribe(() => {
      this.router.navigate(['/categorias']);
    });
  }

}
