package com.salesianostriana.dam.imagineria_web.services;

import com.salesianostriana.dam.imagineria_web.exception.EmptyFavoritosException;
import com.salesianostriana.dam.imagineria_web.exception.EmptyObrasListException;
import com.salesianostriana.dam.imagineria_web.exception.FavoritosNotFoundException;
import com.salesianostriana.dam.imagineria_web.exception.ObrasNotFoundException;
import com.salesianostriana.dam.imagineria_web.model.Favoritos;
import com.salesianostriana.dam.imagineria_web.model.Obras;
import com.salesianostriana.dam.imagineria_web.repository.FavoritosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FavoritosService {

    private final FavoritosRepository favoritosRepository;

    public Favoritos findById(UUID id){

        return favoritosRepository.findById(id)
                .orElseThrow(() -> new FavoritosNotFoundException(id));
    }

    public List<Favoritos> findAll(){

        List<Favoritos> favoritos = favoritosRepository.findAll();

        if (favoritos.isEmpty()){

            throw new EmptyFavoritosException();
        }

        return favoritos;
    }

    public void delete(UUID id){

        if (favoritosRepository.existsById(id)){

            throw new FavoritosNotFoundException(id);
        }
    }

    public Favoritos guardarFav(Favoritos favoritos){

        return favoritosRepository.save(favoritos);
    }
}
