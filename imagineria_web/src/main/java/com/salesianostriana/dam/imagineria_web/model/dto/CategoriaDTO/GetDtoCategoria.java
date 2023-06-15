package com.salesianostriana.dam.imagineria_web.model.dto.CategoriaDTO;

import com.salesianostriana.dam.imagineria_web.model.Categoria;
import com.salesianostriana.dam.imagineria_web.model.Imaginero;
import com.salesianostriana.dam.imagineria_web.model.dto.ImagineroDto.GetDtoImaginero;
import lombok.*;
import org.hibernate.validator.constraints.Normalized;

import javax.validation.constraints.NotEmpty;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
/**
 * Clase DTO para la visualización
 */
public class GetDtoCategoria {

    private UUID id;

    private String nombre;

    private String descripcion;

}
