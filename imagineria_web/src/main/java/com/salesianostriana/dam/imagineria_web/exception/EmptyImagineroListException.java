package com.salesianostriana.dam.imagineria_web.exception;

import javax.persistence.EntityNotFoundException;

public class EmptyImagineroListException extends EntityNotFoundException {

    public EmptyImagineroListException(){

        super("No se han encontrado los imagineros");
    }
}
