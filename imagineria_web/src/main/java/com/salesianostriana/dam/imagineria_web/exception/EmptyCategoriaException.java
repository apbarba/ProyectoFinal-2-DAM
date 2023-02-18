package com.salesianostriana.dam.imagineria_web.exception;

import javax.persistence.EntityNotFoundException;

public class EmptyCategoriaException extends EntityNotFoundException {

    public EmptyCategoriaException(){

        super("No se ha podido encontrar las categorias");
    }
}
