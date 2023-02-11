package com.salesianostriana.dam.imagineria_web.services;

import com.salesianostriana.dam.imagineria_web.model.Imaginero;
import com.salesianostriana.dam.imagineria_web.repository.ImagineroRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImaginerosService {

    private final ImagineroRepository imagineroRepository;

    public Imaginero findByUsername(String username){

        return  imagineroRepository.findByUsername(username);
    }

    public Optional<Imaginero> findById(Long id){

        return imagineroRepository.findById(id);
    }

    public List<Imaginero> findByName(String name){

        return imagineroRepository.findByName(name);
    }

    public Imaginero save(Imaginero imaginero){

        return imagineroRepository.save(imaginero);
    }
}
