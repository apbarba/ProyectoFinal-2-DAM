package com.salesianostriana.dam.imagineria_web.services;

import com.salesianostriana.dam.imagineria_web.exception.CategoriaNotFoundException;
import com.salesianostriana.dam.imagineria_web.exception.EmptyCategoriaException;
import com.salesianostriana.dam.imagineria_web.model.Categoria;
import com.salesianostriana.dam.imagineria_web.model.Obras;
import com.salesianostriana.dam.imagineria_web.model.dto.CategoriaDTO.ConverterDtoCategoria;
import com.salesianostriana.dam.imagineria_web.model.dto.CategoriaDTO.CreateDtoCategoria;
import com.salesianostriana.dam.imagineria_web.model.dto.CategoriaDTO.EditDtoCategoria;
import com.salesianostriana.dam.imagineria_web.model.dto.CategoriaDTO.GetDtoCategoria;
import com.salesianostriana.dam.imagineria_web.repository.CategoriaRepository;
import com.salesianostriana.dam.imagineria_web.search.spec.CategoriaSearch.CategoriaSpecificationBuilder;
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
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    private final ConverterDtoCategoria converterDtoCategoria;

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

    public GetDtoCategoria save(CreateDtoCategoria create){

        Categoria categoria = converterDtoCategoria.createDtoCategoria(create);
        categoriaRepository.save(categoria);

        return converterDtoCategoria.categoriaToCategoria(categoria);
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

    public Page<Categoria> search(List<SearchCriteria> params, Pageable pageable) {

        CategoriaSpecificationBuilder categoriaSpecificationBuilder =

                new CategoriaSpecificationBuilder(params);

        Specification<Categoria> spec =  categoriaSpecificationBuilder.build();

        return categoriaRepository.findAll(spec, pageable);
    }


}
