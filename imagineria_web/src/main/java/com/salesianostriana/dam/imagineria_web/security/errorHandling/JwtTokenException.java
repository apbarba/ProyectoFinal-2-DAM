package com.salesianostriana.dam.imagineria_web.security.errorHandling;

public class JwtTokenException extends RuntimeException{

    public JwtTokenException(String message) {

        super(message);
    }
}
