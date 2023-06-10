package com.salesianostriana.dam.imagineria_web.exception;

import javax.persistence.EntityNotFoundException;

public class FileNotFoundException extends EntityNotFoundException {

    public FileNotFoundException(String fileContent){
        super(String.format("Este tipo de archivo no se puede subir", fileContent));
    }
}
