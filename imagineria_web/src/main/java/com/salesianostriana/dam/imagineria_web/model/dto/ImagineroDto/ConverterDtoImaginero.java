package com.salesianostriana.dam.imagineria_web.model.dto.ImagineroDto;

import com.salesianostriana.dam.imagineria_web.model.Imaginero;
import com.salesianostriana.dam.imagineria_web.model.Obras;
import com.salesianostriana.dam.imagineria_web.model.dto.ObrasDTO.ConverterDtoObras;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConverterDtoImaginero {

    private final ConverterDtoObras converterDtoObras;
    public Imaginero createImaginero(CreateDtoImaginero createDtoImaginero, String uri){

        return Imaginero.builder()
                .name(createDtoImaginero.getName())
                .edad(createDtoImaginero.getEdad())
                .localidad(createDtoImaginero.getLocalidad())
                .build();
    }
    /*public GetDtoImaginero getDtoImaginero(Imaginero i){

        return GetDtoImaginero.builder()
                .id(i.getId())
                .name(i.getName())
                .edad(i.getEdad())
                .localidad(i.getLocalidad())
                .obras(i.getObras().stream()
                        .map(converterDtoObras::getDtoObras)
                        .toList())
                .build();
    }*/

}
