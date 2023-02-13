package com.salesianostriana.dam.imagineria_web.exception;

import javax.persistence.EntityNotFoundException;

public class ObrasNotFoundException extends EntityNotFoundException {

    public ObrasNotFoundException(){

        super("No se ha podido encontrar la obra");
    }

    public  ObrasNotFoundException(Long id){

        super(String.format("No se ha podido encontrar la obras por su id"));
    }
}
