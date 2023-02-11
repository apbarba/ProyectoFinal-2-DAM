package com.salesianostriana.dam.imagineria_web.services;

import com.salesianostriana.dam.imagineria_web.model.Imaginero;
import com.salesianostriana.dam.imagineria_web.model.Obras;
import com.salesianostriana.dam.imagineria_web.repository.ObrasRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ObrasService {

    private final ObrasRepository obrasRepository;

    public List<Obras> findByImaginero(Imaginero imaginero){

        return obrasRepository.findByImaginero(imaginero);
    }

    public Optional<Obras> findById(Long id){

        return obrasRepository.findById(id);
    }

    public List<Obras> findByTitulo(String titulo){

        return obrasRepository.findByTitulo(titulo);
    }

    public List<Obras> findByEstado(String estado){

        return obrasRepository.findByEstado(estado);
    }

    public Obras save(Obras obras){

        return obrasRepository.save(obras);
    }

    public void delete(Obras obras){

        obrasRepository.delete(obras);
    }
}
