package com.salesianostriana.dam.imagineria_web.model.dto.ObrasDTO;

import com.salesianostriana.dam.imagineria_web.model.dto.ImagineroDto.GetDtoImaginero;
import com.salesianostriana.dam.imagineria_web.validation.annotation.PositivePrice;
import lombok.*;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
/**
 * Dto con las variables que requerimos para mostrar la información
 * de las obras limitadas
 */
public class GetDtoObras {

    private UUID id;

    private String name;

    private String estilo;

    private LocalDate fecha;

    private double precio;

    private String estado;

    private String img;


}
