package com.salesianostriana.dam.imagineria_web.model.dto.CategoriaDTO;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateDtoCategoria {

    @NotEmpty(message = "{categoriaGet.name.notempty}")
    private String nombre;

    @NotEmpty(message = "{categoriaGet.descripcion.notempty}")
    private String descripcion;
}
