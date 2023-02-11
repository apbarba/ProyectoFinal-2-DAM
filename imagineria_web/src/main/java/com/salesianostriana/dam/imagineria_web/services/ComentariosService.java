package com.salesianostriana.dam.imagineria_web.services;

import com.salesianostriana.dam.imagineria_web.model.Comentarios;
import com.salesianostriana.dam.imagineria_web.model.Obras;
import com.salesianostriana.dam.imagineria_web.repository.ComentariosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ComentariosService {

    private final ComentariosRepository comentariosRepository;

    public List<Comentarios> findByObra(Obras obra){

        return comentariosRepository.findByObra(obra);
    }

    public Optional<Comentarios> findById(Long id){

        return comentariosRepository.findById(id);
    }

    public Comentarios save(Comentarios comentarios){

        return comentariosRepository.save(comentarios);
    }

    public void delete(Comentarios comentarios){

        comentariosRepository.delete(comentarios);
    }
}
