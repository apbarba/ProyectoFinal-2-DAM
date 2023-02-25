package com.salesianostriana.dam.imagineria_web.services;

import com.salesianostriana.dam.imagineria_web.exception.*;
import com.salesianostriana.dam.imagineria_web.model.Favoritos;
import com.salesianostriana.dam.imagineria_web.model.Obras;
import com.salesianostriana.dam.imagineria_web.model.User;
import com.salesianostriana.dam.imagineria_web.repository.FavoritosRepository;
import com.salesianostriana.dam.imagineria_web.repository.ObrasRepository;
import com.salesianostriana.dam.imagineria_web.repository.UserRepository;
import com.salesianostriana.dam.imagineria_web.search.spec.FavoritosSearch.FavoritosSpecificationBuilder;
import com.salesianostriana.dam.imagineria_web.search.spec.ObrasSearch.ObrasSpecificationBuilder;
import com.salesianostriana.dam.imagineria_web.search.util.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FavoritosService {

    private final ObrasRepository obrasRepository;
    private final UserRepository userRepository;

    public void marcarComoFavorita(UUID userId, UUID obraId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        Obras obra = obrasRepository.findById(obraId)
                .orElseThrow(() -> new ObrasNotFoundException(obraId));

        obra.addUser(user);

        obrasRepository.save(obra);
    }





}
