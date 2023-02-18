package com.salesianostriana.dam.imagineria_web.exception;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

public class EmptyFavoritosException extends EntityNotFoundException {

    public  EmptyFavoritosException(){

        super("No se han encontrado los favoritos");
    }
}
