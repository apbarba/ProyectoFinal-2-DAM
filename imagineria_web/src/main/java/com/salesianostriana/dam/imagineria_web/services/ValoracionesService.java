package com.salesianostriana.dam.imagineria_web.services;

import com.salesianostriana.dam.imagineria_web.model.Obras;
import com.salesianostriana.dam.imagineria_web.model.Valoraciones;
import com.salesianostriana.dam.imagineria_web.repository.ValoracionesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ValoracionesService {

    private final ValoracionesRepository valoracionesRepository;

    public List<Valoraciones> findByObra(Obras obras){

        return valoracionesRepository.findByObra(obras);
    }

    public Optional<Valoraciones> findById(Long id){

        return valoracionesRepository.findById(id);
    }

    public Valoraciones save(Valoraciones valoraciones){

        return valoracionesRepository.save(valoraciones);
    }

    public void delete(Valoraciones valoraciones){

        valoracionesRepository.delete(valoraciones);
    }
}
