package com.salesianostriana.dam.imagineria_web.model.dto.ImagineroDto;

import com.salesianostriana.dam.imagineria_web.model.Imaginero;
import com.salesianostriana.dam.imagineria_web.model.Obras;
import com.salesianostriana.dam.imagineria_web.model.dto.ObrasDTO.ConverterDtoObras;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConverterDtoImaginero {

    /**
     * Método para la creación de un Imaginero nuevo a base de nuestras clasesDTO
     * que nos facilitan las variables que se requiere para realizar esto
     * @param createDtoImaginero
     * @return un imaginero nuevo
     */
    public Imaginero createImaginero(CreateDtoImaginero createDtoImaginero){

        return Imaginero.builder()
                .name(createDtoImaginero.getName())
                .edad(createDtoImaginero.getEdad())
                .localidad(createDtoImaginero.getLocalidad())
                .build();
    }

    /**
     * Método para ver limitado detalles del imaginero o de los imaginero
     * @param i
     * @return detalles limitados
     */
    public GetDtoImaginero imagineroToImaginero(Imaginero i){

        return GetDtoImaginero.builder()
                .id(i.getId())
                .name(i.getName())
                .edad(i.getEdad())
                .localidad(i.getLocalidad())
             /*   .obras(i.getObras().stream()
                        .map(converterDtoObras::getDtoObras)
                        .toList())*/
                .build();
    }

}
