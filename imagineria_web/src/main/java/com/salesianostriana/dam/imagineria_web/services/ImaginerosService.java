package com.salesianostriana.dam.imagineria_web.services;

import com.salesianostriana.dam.imagineria_web.exception.EmptyImagineroException;
import com.salesianostriana.dam.imagineria_web.exception.EmptyObrasListException;
import com.salesianostriana.dam.imagineria_web.exception.ImagineroNotFoundException;
import com.salesianostriana.dam.imagineria_web.exception.ObrasNotFoundException;
import com.salesianostriana.dam.imagineria_web.model.Imaginero;
import com.salesianostriana.dam.imagineria_web.model.Obras;
import com.salesianostriana.dam.imagineria_web.model.dto.ImagineroDto.EditDtoImaginero;
import com.salesianostriana.dam.imagineria_web.model.dto.ImagineroDto.GetDtoImaginero;
import com.salesianostriana.dam.imagineria_web.model.dto.ObrasDTO.EditDtoObras;
import com.salesianostriana.dam.imagineria_web.repository.ImagineroRepository;
import com.salesianostriana.dam.imagineria_web.search.spec.ImagineroSearch.ImagineroSpecificationBuilder;
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
public class ImaginerosService {

    private final ImagineroRepository imagineroRepository;

    public Imaginero findById(UUID id){

        return imagineroRepository.findById(id)
                .orElseThrow(() -> new ImagineroNotFoundException(id));
    }

    public List<Imaginero> findByName(String name){

        List<Imaginero> imaginero = imagineroRepository.findByName(name);

        if (imaginero.isEmpty()){

            throw new ImagineroNotFoundException(name);
        }

        return imaginero;
    }

    public Imaginero save(GetDtoImaginero imaginero){

        return imagineroRepository.save(GetDtoImaginero.toImaginero(imaginero));
    }

    public List<Imaginero> findAll(){

        List<Imaginero> imagineros = imagineroRepository.findAll();

        if (imagineros.isEmpty()){

            throw new EmptyImagineroException();
        }

        return imagineros;
    }

    public Imaginero edit(UUID id, EditDtoImaginero edit){

        return imagineroRepository.findById(id)
                .map(imaginero -> {
                    imaginero.setEdad(edit.getEdad());
                    imaginero.setLocalidad(edit.getLocalidad());

                    return imagineroRepository.save(imaginero);
                })
                .orElseThrow(() -> new ImagineroNotFoundException());
    }

    public void delete(UUID id){

        if (imagineroRepository.existsById(id))
            imagineroRepository.deleteById(id);
    }

    public Page<Imaginero> search(List<SearchCriteria> params, Pageable pageable) {

        ImagineroSpecificationBuilder imagineroSpecificationBuilder =

                new ImagineroSpecificationBuilder(params);

        Specification<Imaginero> spec =  imagineroSpecificationBuilder.build();

        return imagineroRepository.findAll(spec, pageable);
    }
}
