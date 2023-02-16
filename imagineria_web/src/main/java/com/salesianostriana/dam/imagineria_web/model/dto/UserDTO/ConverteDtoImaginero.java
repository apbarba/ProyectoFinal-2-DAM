package com.salesianostriana.dam.imagineria_web.model.dto.UserDTO;

import com.salesianostriana.dam.imagineria_web.model.User;
import org.springframework.stereotype.Component;

@Component
public class ConverteDtoImaginero {

    public CreateDtoUser getDtoImaginero(User imaginero){

        return CreateDtoUser.builder()
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

    public User createImaginero(CreateDtoUser getDtoImaginero){

        return User.builder()
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
