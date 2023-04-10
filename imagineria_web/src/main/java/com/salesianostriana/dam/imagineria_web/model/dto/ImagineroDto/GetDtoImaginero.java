package com.salesianostriana.dam.imagineria_web.model.dto.ImagineroDto;

import com.salesianostriana.dam.imagineria_web.model.Imaginero;
import com.salesianostriana.dam.imagineria_web.model.dto.ObrasDTO.GetDtoObras;
import lombok.*;
import org.w3c.dom.stylesheets.LinkStyle;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetDtoImaginero {

    private UUID id;

    private String name;

    private int edad;

    private String localidad;

    private List<GetDtoObras> obras;

}
