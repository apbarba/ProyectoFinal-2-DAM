package com.salesianostriana.dam.imagineria_web.exception.CategoriaException;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

public class CategoriaNotFoundException extends EntityNotFoundException {

    public CategoriaNotFoundException(){

        super("No se ha encontrado la categoria");
    }

    public CategoriaNotFoundException(UUID uuid){

        super(String.format("No se ha encontrado la categoria con el id específico"));
    }

    public CategoriaNotFoundException(String nombre){

        super("No se ha encontrado la categoría con el nombre especificado");
    }
}
