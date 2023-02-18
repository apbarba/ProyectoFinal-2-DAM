package com.salesianostriana.dam.imagineria_web.exception;

import javax.persistence.EntityNotFoundException;

public class EmptyImagineroException extends EntityNotFoundException {

    public EmptyImagineroException(){

        super("No se han podido encontrar a los imagineros");
    }
}
