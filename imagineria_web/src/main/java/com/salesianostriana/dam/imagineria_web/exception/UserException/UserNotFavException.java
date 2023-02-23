package com.salesianostriana.dam.imagineria_web.exception.UserException;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

public class UserNotFavException extends EntityNotFoundException {

    public  UserNotFavException(UUID id){

        super("El usuario no tiene favoritos");
    }
}
