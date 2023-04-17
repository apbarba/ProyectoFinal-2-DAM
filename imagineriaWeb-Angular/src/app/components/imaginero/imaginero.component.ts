import { Component, OnInit } from '@angular/core';
import { Imaginero } from 'src/app/models/imaginero.model';
import { ImagineriService } from 'src/app/services/imagineri.service';

@Component({
  selector: 'app-imaginero',
  templateUrl: './imaginero.component.html',
  styleUrls: ['./imaginero.component.css']
})
export class ImagineroComponent implements OnInit {

  imagineros: Imaginero[] = [];

  constructor(private imagineroService: ImagineriService){


  }
  ngOnInit(): void {
    this.imagineroService.getAllImagineros().subscribe((data: any) => {
      this.imagineros = data.content as Imaginero[];
    })
  }

}
