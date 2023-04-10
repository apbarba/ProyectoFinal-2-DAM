package com.salesianostriana.dam.imagineria_web.model.dto.CategoriaDTO;

import com.salesianostriana.dam.imagineria_web.model.Categoria;
import com.salesianostriana.dam.imagineria_web.model.dto.ImagineroDto.CreateDtoImaginero;
import org.springframework.stereotype.Component;

@Component
public class ConverterDtoCategoria {

    public Categoria createDtoCategoria(CreateDtoCategoria create){

        return Categoria.builder()
                .nombre(create.getNombre())
                .descripcion(create.getDescripcion())
                .build();
    }

    public GetDtoCategoria categoriaToCategoria(Categoria categoria){

        return GetDtoCategoria.builder()
                .id(categoria.getId())
                .nombre(categoria.getNombre())
                .descripcion(categoria.getDescripcion())
                .build();
    }
}
