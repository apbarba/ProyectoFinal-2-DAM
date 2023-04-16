import { Component, OnInit } from '@angular/core';
import { ObrasService } from '../services/obras.service';
import { Obra } from '../models/obra.model';

@Component({
  selector: 'app-obras',
  templateUrl: './obras.component.html',
  styleUrls: ['./obras.component.css']
})
export class ObrasComponent implements OnInit {

  obras: Obra[] = [];

  constructor(private obrasService: ObrasService){}
  
  ngOnInit(): void {
   this.obrasService.getAllObras().subscribe((data: any) => {
    this.obras = data.content as Obra[];
   });
  }
  
}
