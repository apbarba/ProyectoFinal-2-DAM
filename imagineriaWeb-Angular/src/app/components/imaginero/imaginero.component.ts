import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Imaginero } from 'src/app/models/imaginero.model';
import { ImagineriService } from 'src/app/services/imagineri.service';

@Component({
  selector: 'app-imaginero',
  templateUrl: './imaginero.component.html',
  styleUrls: ['./imaginero.component.css']
})
export class ImagineroComponent implements OnInit {

  imagineros: Imaginero[] = [];

  constructor(private imagineroService: ImagineriService, private router: Router){
  }

  ngOnInit(): void {
    this.imagineroService.getAllImagineros().subscribe((data: any) => {
      this.imagineros = data.content as Imaginero[];
    })
  }
  editar(id: string) {
    this.router.navigate(['/imaginero/editar', id]);
  }

  create(){
    this.router.navigate(['/imaginero/create'])
  }

}
