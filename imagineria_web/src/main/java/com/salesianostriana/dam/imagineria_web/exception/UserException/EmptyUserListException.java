package com.salesianostriana.dam.imagineria_web.exception.UserException;

import javax.persistence.EntityNotFoundException;

public class EmptyUserListException extends EntityNotFoundException {

    public EmptyUserListException(){

        super("No se han encontrado los imagineros");
    }
}
