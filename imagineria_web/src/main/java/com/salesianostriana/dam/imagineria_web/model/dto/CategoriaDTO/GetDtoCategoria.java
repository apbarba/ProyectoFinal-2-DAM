package com.salesianostriana.dam.imagineria_web.model.dto.CategoriaDTO;

import com.salesianostriana.dam.imagineria_web.model.Categoria;
import com.salesianostriana.dam.imagineria_web.model.Imaginero;
import com.salesianostriana.dam.imagineria_web.model.dto.ImagineroDto.GetDtoImaginero;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Normalized;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@RequiredArgsConstructor
@Data
public class GetDtoCategoria {

    @NotEmpty(message = "{cagetoriaGet.name.notempty}")
    private String nombre;

    @NotEmpty(message = "{categoriaGet.descripcion.notempty}")
    private String descripcion;

    public static Categoria toCategoria(GetDtoCategoria edit){

        return Categoria.builder()
                .nombre(edit.getNombre())
                .descripcion(edit.getDescripcion())
                .build();
    }
}
