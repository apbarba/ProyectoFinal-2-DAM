package com.salesianostriana.dam.imagineria_web.exception;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

public class FavoritosNotFoundException extends EntityNotFoundException {

    public FavoritosNotFoundException(){

        super("No se ha podido indicar como favorito");
    }

    public  FavoritosNotFoundException(UUID id){

        super(String.format("No se ha podido encontrar el favorito indicado"));
    }
}
