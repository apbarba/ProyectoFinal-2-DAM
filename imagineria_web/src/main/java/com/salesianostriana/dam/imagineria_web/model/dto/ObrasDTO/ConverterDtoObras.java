package com.salesianostriana.dam.imagineria_web.model.dto.ObrasDTO;

import com.salesianostriana.dam.imagineria_web.model.Obras;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class ConverterDtoObras {

    public GetDtoObras obrasToObras(Obras obras){

        return GetDtoObras.builder()
                .id(obras.getId())
                .img(obras.getImg())
                .estado(obras.getEstado())
                .estilo(obras.getEstilo())
                .fecha(obras.getFecha())
                .name(obras.getName())
                .precio(obras.getPrecio())
                .build();
    }

    public Obras createObra(CreateDtoObras getDtoObras){

        return Obras.builder()
                .name(getDtoObras.getName())
                .img(getDtoObras.getImg())
                .estado(getDtoObras.getEstado())
                .estilo(getDtoObras.getEstilo())
                .precio(getDtoObras.getPrecio())
                .fecha(getDtoObras.getFecha())
                .build();
    }
}
