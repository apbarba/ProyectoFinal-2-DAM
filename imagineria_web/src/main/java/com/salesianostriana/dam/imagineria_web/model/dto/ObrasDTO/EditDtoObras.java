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
/**
 * Dto con las variables necesarias para la realización de la edición
 * de la obra
 */
public class EditDtoObras {
    @NotEmpty(message = "{obrasEdit.name.notempty}")
    private String name;

    @PositivePrice(message = "{obrasEdit.precio.unique}")
    @Min(value = 0, message = "{obrasEdit.precio.min}")
    private double precio;
    @NotEmpty(message = "{obrasEdit.estado.notempty}")
    private String estado;

}
