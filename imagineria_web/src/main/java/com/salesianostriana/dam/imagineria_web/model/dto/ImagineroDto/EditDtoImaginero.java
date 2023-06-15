package com.salesianostriana.dam.imagineria_web.model.dto.ImagineroDto;

import com.salesianostriana.dam.imagineria_web.model.Imaginero;
import com.salesianostriana.dam.imagineria_web.model.Obras;
import com.salesianostriana.dam.imagineria_web.model.dto.ObrasDTO.EditDtoObras;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;


@Getter
@Setter
@RequiredArgsConstructor
@Data
/**
 * Dto para la edición de un imaginero en la que solo se declaran
 * las variables necesarias para la realización de este
 */
public class EditDtoImaginero {

    @Min(value = 0, message = "{imagineroEdit.edad.notempty}")
    private int edad;

    @NotEmpty(message = "{imagineroEdit.localidad.blank}")
    private String localidad;


}
