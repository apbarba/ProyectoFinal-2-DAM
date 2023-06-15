package com.salesianostriana.dam.imagineria_web.model.dto.CategoriaDTO;

import com.salesianostriana.dam.imagineria_web.model.Categoria;
import com.salesianostriana.dam.imagineria_web.model.dto.ImagineroDto.CreateDtoImaginero;
import org.springframework.stereotype.Component;

@Component
public class ConverterDtoCategoria {

    /**
     * Método para la creación de una categoria nueva (actualmente no tengo pensado en crear categorias, pero
     * si en agún futuro se requiera de una nueva, ya está creada)
     * @param create
     * @return devuelve una categoria nueva
     */
    public Categoria createDtoCategoria(CreateDtoCategoria create){

        return Categoria.builder()
                .nombre(create.getNombre())
                .descripcion(create.getDescripcion())
                .build();
    }

    /**
     * Método para la visualización al completo o de una categoria en la
     * que se ha utilizado DTO para mostrar un poco de los detalles
     * @param categoria
     * @return información limitada de las categorias o una
     */
    public GetDtoCategoria categoriaToCategoria(Categoria categoria){

        return GetDtoCategoria.builder()
                .id(categoria.getId())
                .nombre(categoria.getNombre())
                .descripcion(categoria.getDescripcion())
                .build();
    }
}
