package com.salesianostriana.dam.imagineria_web.model.dto.ObrasDTO;

import com.salesianostriana.dam.imagineria_web.model.Obras;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class ConverterDtoObras {

    public GetDtoObras getDtoObras(Obras obras){

        return GetDtoObras.builder()
                .id(obras.getId())
                .img(obras.getImg())
                .estado(obras.getEstado())
                .estilo(obras.getEstilo())
              //  .fecha(obras.getFecha().toString())
                .nombre(obras.getName())
                .precio(obras.getPrecio())
              //  .comentarios(obras.getComentarios().stream()
                     //   .map(ConverterDtoComentarios::getDtoComentario)
                       // .toList())
                .build();
    }

    public Obras createObra(GetDtoObras getDtoObras){

        return Obras.builder()
                .name(getDtoObras.getNombre())
                .img(getDtoObras.getImg())
                .estado(getDtoObras.getEstado())
                .estilo(getDtoObras.getEstilo())
                .precio(getDtoObras.getPrecio())
               // .fecha(getDtoObras.getFecha().toString())
                .build();
    }
}
