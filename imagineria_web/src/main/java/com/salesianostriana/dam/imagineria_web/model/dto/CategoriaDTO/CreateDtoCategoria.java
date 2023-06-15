package com.salesianostriana.dam.imagineria_web.model.dto.CategoriaDTO;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
/**
 * Clase DTO para facilitar los atributos que queremos que sean requeridos para la creación de
 * una categoria
 */
public class CreateDtoCategoria {

    @NotEmpty(message = "{categoriaGet.name.notempty}")
    private String nombre;

    @NotEmpty(message = "{categoriaGet.descripcion.notempty}")
    private String descripcion;
}
