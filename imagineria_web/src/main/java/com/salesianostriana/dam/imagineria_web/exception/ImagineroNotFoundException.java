package com.salesianostriana.dam.imagineria_web.exception;

import org.springframework.security.core.AuthenticationException;

import javax.persistence.EntityNotFoundException;

public class ImagineroNotFoundException extends AuthenticationException {

    public ImagineroNotFoundException(String mensaje){

        super(mensaje);
    }

    public ImagineroNotFoundException(String mensaje, Throwable cause){
        super(mensaje, cause);
    }
}
