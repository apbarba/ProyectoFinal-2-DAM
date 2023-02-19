package com.salesianostriana.dam.imagineria_web.services;

import com.salesianostriana.dam.imagineria_web.exception.EmptyObrasListException;
import com.salesianostriana.dam.imagineria_web.exception.ObrasNotFoundException;
import com.salesianostriana.dam.imagineria_web.model.Imaginero;
import com.salesianostriana.dam.imagineria_web.model.User;
import com.salesianostriana.dam.imagineria_web.model.Obras;
import com.salesianostriana.dam.imagineria_web.model.dto.ObrasDTO.EditDtoObras;
import com.salesianostriana.dam.imagineria_web.repository.ObrasRepository;
import com.salesianostriana.dam.imagineria_web.search.spec.ObrasSearch.ObrasSpecificationBuilder;
import com.salesianostriana.dam.imagineria_web.search.util.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ObrasService {

    private final ObrasRepository obrasRepository;

    public List<Obras> findByImaginero(Imaginero imaginero){

        List<Obras> obras = obrasRepository.findByImaginero(imaginero);

        if (obras.isEmpty()){

            throw new EmptyObrasListException();
        }

        return obras;
    }

    public Obras findById(UUID id){

        return obrasRepository.findById(id)
                .orElseThrow(() -> new ObrasNotFoundException(id));
    }

    public List<Obras> findByTitulo(String titulo){

        return obrasRepository.findByTitulo(titulo);
    }

    public List<Obras> findByEstado(String estado){

        return obrasRepository.findByEstado(estado);
    }

    public Obras save(EditDtoObras obras){

        return obrasRepository.save(EditDtoObras.toObras(obras));
    }

    public void delete(UUID id){

        if (obrasRepository.existsById(id))
            obrasRepository.deleteById(id);
    }

    public List<Obras> findAll(){

        List<Obras> obras = obrasRepository.findAll();

        if (obras.isEmpty()){

            throw new EmptyObrasListException();
        }

        return obras;
    }

    public Obras edit(UUID id, EditDtoObras edit){

        return obrasRepository.findById(id)
                .map(obras -> {
                    obras.setTitulo(edit.getTitulo());
                    obras.setEstado(edit.getEstado());
                    obras.setPrecio(edit.getPrecio());
                   // obras.setCategoria(edit.getCategoria());
                    obras.setName(edit.getName());
                    obras.setImg(edit.getImg());
                   // obras.setImaginero(edit.getImaginero());

                    return obrasRepository.save(obras);
                })
                .orElseThrow(() -> new ObrasNotFoundException());
    }

    public Page<Obras> search(List<SearchCriteria> params, Pageable pageable) {

        ObrasSpecificationBuilder obrasSpecificationBuilder =

                new ObrasSpecificationBuilder(params);

        Specification<Obras> spec =  obrasSpecificationBuilder.build();

        return obrasRepository.findAll(spec, pageable);
    }
}
