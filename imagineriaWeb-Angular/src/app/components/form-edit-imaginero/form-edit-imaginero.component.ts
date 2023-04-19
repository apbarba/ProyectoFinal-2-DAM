import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Imaginero } from 'src/app/models/imaginero.model';
import { ImagineriService } from 'src/app/services/imagineri.service';
import { MatCardModule } from '@angular/material/card';


@Component({
  selector: 'app-form-edit-imaginero',
  templateUrl: './form-edit-imaginero.component.html',
  styleUrls: ['./form-edit-imaginero.component.css']
})
export class FormEditImagineroComponent implements OnInit{

  imaginero: Imaginero = {
    id: '',
    name: '',
    edad: 0,
    localidad: '',
    obras: null
  };


  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private imagineroService: ImagineriService
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id') ?? '';
    this.imagineroService.getImagineroById(id).subscribe((imaginero) => {
      this.imaginero = imaginero as Imaginero;
    });
  }

  submitForm(form: NgForm) {
    const id = this.imaginero.id;
    const { name, edad, localidad } = form.value;
    this.imagineroService.editImaginero(id, edad, localidad).subscribe(() => {
      this.router.navigate(['/imagineros']);
    });
  }
}
