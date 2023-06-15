package com.salesianostriana.dam.imagineria_web.model.dto.UserDTO;

import com.salesianostriana.dam.imagineria_web.model.User;
import org.springframework.stereotype.Component;

@Component
/**
 * Clase que no se utiliza
 */
public class ConverteDtoImaginero {

    public CreateDtoUser getDtoImaginero(User imaginero){

        return CreateDtoUser.builder()
                .name(imaginero.getName())
                .email(imaginero.getEmail())
                .password(imaginero.getPassword())
                .username(imaginero.getUsername())

                .build();
    }

    public User createImaginero(CreateDtoUser getDtoImaginero){

        return User.builder()
                .name(getDtoImaginero.getName())
                .email(getDtoImaginero.getEmail())
                .password(getDtoImaginero.getPassword())
                .username(getDtoImaginero.getUsername())
                .build();
    }
}
