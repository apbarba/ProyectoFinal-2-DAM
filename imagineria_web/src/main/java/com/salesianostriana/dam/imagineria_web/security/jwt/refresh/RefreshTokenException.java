package com.salesianostriana.dam.imagineria_web.security.jwt.refresh;

import com.salesianostriana.dam.imagineria_web.security.errorHandling.JwtTokenException;

public class RefreshTokenException extends JwtTokenException {

    public RefreshTokenException(String msg) {
        super(msg);
    }
}
