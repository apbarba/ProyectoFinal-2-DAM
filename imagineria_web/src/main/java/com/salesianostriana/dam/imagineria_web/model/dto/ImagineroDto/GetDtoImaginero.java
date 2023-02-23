package com.salesianostriana.dam.imagineria_web.model.dto.ImagineroDto;

import com.salesianostriana.dam.imagineria_web.model.Imaginero;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;


@Getter
@Setter
@RequiredArgsConstructor
@Data
public class GetDtoImaginero {

    @NotEmpty(message = "{imagineroGet.name.notempty}")
    private String name;

   @Min(value = 0 , message = "{imaginero.edad.min}")
    private int edad;

    private String localidad;

    public static Imaginero toImaginero(GetDtoImaginero edit){

        return Imaginero.builder()
                .edad(edit.getEdad())
                .localidad(edit.getLocalidad())
                .name(edit.getName())
                .build();
    }
}
