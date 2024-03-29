package com.salesianostriana.dam.imagineria_web.model.dto.ImagineroDto;

import com.salesianostriana.dam.imagineria_web.model.Obras;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
/**
 * Dto para la creacion de un imaginero en la que solamente se declaran
 * las variables que se van a requerido para esto
 */
public class CreateDtoImaginero {
    @NotEmpty(message = "{imagineroCreate.name.notempty}")
    private String name;

    @Min(value = 0, message = "{imagineroCreate.edad.min}")
    private int edad;

    @NotEmpty(message = "{imagineroCreate.localidad.notempty}")
    private String localidad;

  //  private List<Obras> obras = new ArrayList<>();
}
