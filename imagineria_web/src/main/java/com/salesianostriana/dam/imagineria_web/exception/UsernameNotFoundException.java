package com.salesianostriana.dam.imagineria_web.exception;

import org.springframework.security.core.AuthenticationException;

public class UsernameNotFoundException extends AuthenticationException {

    public UsernameNotFoundException(String mensaje){

        super(mensaje);
    }

    public UsernameNotFoundException(String mensaje, Throwable cause){
        super(mensaje, cause);
    }
}
