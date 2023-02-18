package com.salesianostriana.dam.imagineria_web.model.dto.CategoriaDTO;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@RequiredArgsConstructor
@Data
public class EditDtoCategoria {

    @NotEmpty(message = "{categoriaEdit.nombre.notempty}")
    private String nombre;

    @NotEmpty(message = "{categoriaEdit.descripcion.notempty}")
    private String descripcion;
}
