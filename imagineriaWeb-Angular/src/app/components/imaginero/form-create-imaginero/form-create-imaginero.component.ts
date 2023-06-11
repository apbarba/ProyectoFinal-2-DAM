import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Imaginero } from 'src/app/models/imaginero.model';
import { ImagineriService } from 'src/app/services/imagineri.service';

@Component({
  selector: 'app-form-create-imaginero',
  templateUrl: './form-create-imaginero.component.html',
  styleUrls: ['./form-create-imaginero.component.css']
})
export class FormCreateImagineroComponent {

  nuevoImaginero: Imaginero = {
    id: '',
    name: '',
    edad: 0,
    localidad: '',
    obras: null
  }

  constructor(private imagineroSefvice: ImagineriService, private router:Router){}

  createImaginero(){
    this.imagineroSefvice.createImaginero(this.nuevoImaginero).subscribe((response: any) => {
      console.log(response); 
      this.router.navigateByUrl("/imagineros");
    });

}
}
