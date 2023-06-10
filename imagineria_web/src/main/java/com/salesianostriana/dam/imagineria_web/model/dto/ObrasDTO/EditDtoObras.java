package com.salesianostriana.dam.imagineria_web.model.dto.ObrasDTO;

import com.salesianostriana.dam.imagineria_web.model.Obras;
import com.salesianostriana.dam.imagineria_web.validation.annotation.PositivePrice;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.MappingException;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EditDtoObras {

    //name, precio, titulo, img, estado

    @NotEmpty(message = "{obrasEdit.name.notempty}")
    private String name;

    @PositivePrice(message = "{obrasEdit.precio.unique}")
    @Min(value = 0, message = "{obrasEdit.precio.min}")
    private double precio;

    @URL(message = "{obrasEdit.img.notempty}")
    private String img;

    @NotEmpty(message = "{obrasEdit.titulo.notempty}")
    private String titulo;

    @NotEmpty(message = "{obrasEdit.estado.notempty}")
    private String estado;

}
