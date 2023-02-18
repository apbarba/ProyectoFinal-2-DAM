package com.salesianostriana.dam.imagineria_web.services;

import com.salesianostriana.dam.imagineria_web.exception.CategoriaNotFoundException;
import com.salesianostriana.dam.imagineria_web.exception.EmptyCategoriaException;
import com.salesianostriana.dam.imagineria_web.model.Categoria;
import com.salesianostriana.dam.imagineria_web.model.dto.CategoriaDTO.EditDtoCategoria;
import com.salesianostriana.dam.imagineria_web.model.dto.CategoriaDTO.GetDtoCategoria;
import com.salesianostriana.dam.imagineria_web.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public Categoria findById(UUID id){

        return categoriaRepository.findById(id)
                .orElseThrow(() -> new CategoriaNotFoundException(id));
    }

    public List<Categoria> findByName(String name){

        List<Categoria> categoria = categoriaRepository.findByNombre(name);

        if (categoria.isEmpty()){

            throw new CategoriaNotFoundException(name);
        }

        return categoria;
    }

    public Categoria save(GetDtoCategoria categoria){

        return categoriaRepository.save(GetDtoCategoria.toCategoria(categoria));
    }

    public List<Categoria> findAll(){

        List<Categoria> categorias = categoriaRepository.findAll();

        if (categorias.isEmpty()){

            throw new EmptyCategoriaException();
        }

        return categorias;
    }

    public Categoria edit(UUID id, EditDtoCategoria edit){

        return categoriaRepository.findById(id)
                .map(categoria -> {
                    categoria.setNombre(edit.getNombre());
                    categoria.setDescripcion(edit.getDescripcion());

                    return categoriaRepository.save(categoria);
                })
                .orElseThrow(() -> new CategoriaNotFoundException());
    }

    public void delete(UUID id){

        if (categoriaRepository.existsById(id))
            categoriaRepository.deleteById(id);
    }


}
