package com.salesianostriana.dam.imagineria_web.exception.ImaginerosException;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

public class ImagineroNotFoundException extends EntityNotFoundException {

    public ImagineroNotFoundException(){

        super("No se ha podido encontrar al imaginero");
    }

    public  ImagineroNotFoundException(UUID id){

        super(String.format("No se ha podido encontrar al imaginero por su id"));
    }

    public ImagineroNotFoundException(String name){

        super("No se ha encontrado a un imaginero por ese nombre");
    }
}
