package com.salesianostriana.dam.imagineria_web.exception.ObrasException;

import com.salesianostriana.dam.imagineria_web.model.User;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

public class ObrasNotFoundException extends EntityNotFoundException {

    public ObrasNotFoundException(){

        super("No se ha podido encontrar la obra");
    }

    public  ObrasNotFoundException(UUID id){

        super(String.format("No se ha podido encontrar la obras por su id"));
    }

    public ObrasNotFoundException(UUID obraId, UUID userId){

        super(String.format("La obra con id: " + obraId + "no es un favorito del usuario con id: " + userId));
    }
}
