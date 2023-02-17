package com.salesianostriana.dam.imagineria_web.services;

import com.salesianostriana.dam.imagineria_web.exception.EmptyObrasListException;
import com.salesianostriana.dam.imagineria_web.exception.ObrasNotFoundException;
import com.salesianostriana.dam.imagineria_web.model.User;
import com.salesianostriana.dam.imagineria_web.model.Obras;
import com.salesianostriana.dam.imagineria_web.model.dto.ObrasDTO.EditDtoObras;
import com.salesianostriana.dam.imagineria_web.repository.ObrasRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ObrasService {

    private final ObrasRepository obrasRepository;

    public List<Obras> findByImaginero(User imaginero){

        List<Obras> obras = obrasRepository.findByImaginero(imaginero);

        if (obras.isEmpty()){

            throw new EmptyObrasListException();
        }

        return obras;
    }

    public Obras findById(Long id){

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

    public void delete(Long id){

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

    public Obras edit(Long id, EditDtoObras edit){

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
}
