package com.salesianostriana.dam.imagineria_web.exception;

import org.springframework.security.core.AuthenticationException;

import java.util.UUID;

public class UserNotFoundException extends AuthenticationException {

    public UserNotFoundException(String mensaje){

        super(mensaje);
    }

    public UserNotFoundException(UUID id){
        super(String.format("No se ha encontrado al usuario con el is en específico"));
    }
}
