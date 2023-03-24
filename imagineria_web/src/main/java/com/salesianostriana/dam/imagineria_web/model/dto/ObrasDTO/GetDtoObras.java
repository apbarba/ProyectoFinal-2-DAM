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

@Builder
@Getter
@Setter
@RequiredArgsConstructor
@Data
public class GetDtoObras {

    private UUID id;

    @NotEmpty(message = "{obrasEdit.name.notempty}")
    private String nombre;

    @NotEmpty(message = "{obraCreate.estilo.notempty}")
    private String estilo;

    @NotEmpty(message = "{obraCreate.fecha.notempty}")
    private LocalDate fecha;

    @PositivePrice(message = "{obrasEdit.precio.unique}")
    @Min(value = 0, message = "{obrasEdit.precio.min}")
    private double precio;

    @NotEmpty(message = "{obrasEdit.estado.notempty}")
    private String estado;

    @URL(message = "{obrasEdit.img.notempty}")
    private String img;

    private List<GetDtoImaginero> dtoImagineros;

    public GetDtoObras(UUID id, String nombre, String estilo, LocalDate fecha, double precio, String estado, String img) {
        this.id = id;
        this.nombre = nombre;
        this.estilo = estilo;
        this.fecha = fecha;
        this.precio = precio;
        this.estado = estado;
        this.img = img;
    }

}
