import { Component } from '@angular/core';
import { Obra } from 'src/app/models/obra.model';
import { ObrasService } from 'src/app/services/obras.service';

@Component({
  selector: 'app-form-create-obra',
  templateUrl: './form-create-obra.component.html',
  styleUrls: ['./form-create-obra.component.css']
})
export class FormCreateObraComponent {
  nuevaObra: Obra = { // Crear un objeto literal que representa una nueva obra
    estado: '',
    estilo: '',
    titulo: '',
    fecha: '',
    id: '',
    img: '',
    nombre: '',
    precio: 0
  };

  constructor(private obrasService: ObrasService){}

  agregarObra() {

    this.obrasService.createObra(this.nuevaObra).subscribe(
      (response: any) => {
        console.log('Obra agregada exitosamente', response);
      },
      (error: any) => {
        console.error('Error al agregar la obra', error);
      }
    );
  }

}
