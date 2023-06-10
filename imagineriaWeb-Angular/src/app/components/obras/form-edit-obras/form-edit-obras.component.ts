import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Obra } from 'src/app/models/obra.model';
import { ObrasService } from 'src/app/services/obras.service';

@Component({
  selector: 'app-form-edit-obras',
  templateUrl: './form-edit-obras.component.html',
  styleUrls: ['./form-edit-obras.component.css']
})
export class FormEditObrasComponent implements OnInit{

  obra: Obra = {
    id: '',
    precio: 0,
    titulo: '',
    estado: '',
    estilo: '',
    img: '',
    fecha: '',
    categoria: '',
    favorito: false,
    name: ''
  }

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private obrasService: ObrasService
  ) {}


  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id') ?? '';
    this.obrasService.getObraById(id).subscribe((obra) => {
      this.obra = obra as Obra;
    });
  }

  submitForm(form: NgForm){
    const id = this.obra.id;
    const {name, precio, titulo, estado, estilo, img } = form.value;
    this.obrasService.editObra(id, name, precio, titulo, estado, estilo, img).subscribe(() => {
      this.router.navigate(['/obras']);
    })
  }
}
