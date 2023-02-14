package com.salesianostriana.dam.imagineria_web.model.dto.ImagineroDTO;

import com.salesianostriana.dam.imagineria_web.model.Imaginero;
import org.springframework.stereotype.Component;

@Component
public class ConverteDtoImaginero {

    public CreateDtoImaginero getDtoImaginero(Imaginero imaginero){

        return CreateDtoImaginero.builder()
                .name(imaginero.getName())
                .email(imaginero.getEmail())
                .password(imaginero.getPassword())
                .username(imaginero.getUsername())
              //  .obras(imaginero.getObras()
              //          .stream()
              //          .map(ConverterDtoObras::getDtoObras)
              //          .toList())
                .build();
    }

    public Imaginero createImaginero(CreateDtoImaginero getDtoImaginero){

        return Imaginero.builder()
                .name(getDtoImaginero.getName())
                .email(getDtoImaginero.getEmail())
                .password(getDtoImaginero.getPassword())
                .username(getDtoImaginero.getUsername())
              //  .obras(getDtoImaginero.getObras().stream()
             //           .map(ConvertDtoObras::createObras)
               //         .toList())
                .build();
    }
}
