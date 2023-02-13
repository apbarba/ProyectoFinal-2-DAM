package com.salesianostriana.dam.imagineria_web.exception;

import javax.persistence.EntityNotFoundException;

public class EmptyObrasListException extends EntityNotFoundException {

    public EmptyObrasListException(){

        super("No se ha podido encontrar las obras");
    }
}
