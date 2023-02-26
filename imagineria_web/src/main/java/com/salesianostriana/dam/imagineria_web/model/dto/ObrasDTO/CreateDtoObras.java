package com.salesianostriana.dam.imagineria_web.model.dto.ObrasDTO;

import com.salesianostriana.dam.imagineria_web.validation.annotation.PositivePrice;
import lombok.*;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CreateDtoObras {

    @NotEmpty(message = "{obrasEdit.name.notempty}")
    private String nombre;

    private String estilo;

    @NotEmpty(message = "{obrasEdit.titulo.notempty}")
    private String titulo;

    private LocalDate fecha;

    @PositivePrice(message = "{obrasEdit.precio.unique}")
    @Min(value = 0, message = "{obrasEdit.precio.min}")
    private double precio;

    @NotEmpty(message = "{obrasEdit.estado.notempty}")
    private String estado;

    @URL(message = "{obrasEdit.img.notempty}")
    private String img;
}