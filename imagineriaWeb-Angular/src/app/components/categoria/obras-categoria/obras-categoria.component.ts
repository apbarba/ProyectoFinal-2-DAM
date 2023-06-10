import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Obra } from 'src/app/models/obra.model';
import { CategoriaService } from 'src/app/services/categoria.service';

@Component({
  selector: 'app-obras-categoria',
  templateUrl: './obras-categoria.component.html',
  styleUrls: ['./obras-categoria.component.css']
})
export class ObrasCategoriaComponent{

  obras: Obra[] = [];
  displayedColumns: string[] = ['nombre', 'fecha', 'titulo'];

  constructor(private categoriaService: CategoriaService, private route: ActivatedRoute){}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id')!;
    this.categoriaService.getObrasByCategoria(id).subscribe((data: any) => {
      this.obras = data.obras as Obra[];
      console.log(this.obras);
    })
}
}
