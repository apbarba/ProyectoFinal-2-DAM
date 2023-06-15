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
/**
 * Clase del servicio de las categorias, donde se declararán los métodos
 * necesarios para las peticiones que se realizarán
 */
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    private final ConverterDtoCategoria converterDtoCategoria;

    /**
     * Método donde se busca a una categoria por  id, si no es el caso
     * de que exista, salta una excepción
     * @param id
     * @return la categoria correspondiente de id
     */
    public Categoria findById(UUID id){

        return categoriaRepository.findById(id)
                .orElseThrow(() -> new CategoriaNotFoundException(id));
    }

    /**
     * Método que realizamos para la creación de una categoria si se nos
     * requiere en algún momento. Utilizamos nuestras clase DTO que realizamos
     * para la facilitación de las varibales necesarias, además de llamar
     * al método que realizamos también en nuestra clase converter de la creación
     * @param create
     * @return la categoria creada
     */
    public GetDtoCategoria save(CreateDtoCategoria create){

        Categoria categoria = converterDtoCategoria.createDtoCategoria(create);
        categoriaRepository.save(categoria);

        return converterDtoCategoria.categoriaToCategoria(categoria);
    }

    /**
     * Método que realiza la edición de una categoria.
     * @param id, se busca el id de la categoria que queremos editar
     * @param edit,  utilizamos la clase dto que creamos de la edición
     *               para que nos resulte más fácil por las variables que se requieren
     * @return la categoria modificada
     */
    public Categoria edit(UUID id, EditDtoCategoria edit){

        return categoriaRepository.findById(id)
                .map(categoria -> {
                    categoria.setNombre(edit.getNombre());
                    categoria.setDescripcion(edit.getDescripcion());

                    return categoriaRepository.save(categoria);
                })
                .orElseThrow(() -> new CategoriaNotFoundException());
    }

    /**
     * Método que se utiliza para la eliminación de una categoria
     * pero que no borra las obras que tiene asociada a estas
     * @param id, necesitamos saber la categoria que queremos saber
     */
    public void delete(UUID id){

        if (categoriaRepository.existsById(id))
            categoriaRepository.deleteById(id);
    }

    /**
     * Método que nos muestra todas las categorias que tenemos creadas o que se encuentrasn
     * en nuestra base de datos. Se utiliza un filtrado para ser más específicos en la búsqueda y
     * se nos muestra paginada
     * @param params
     * @param pageable
     * @return las categorias que tenemos
     */
    public Page<Categoria> search(List<SearchCriteria> params, Pageable pageable) {

        CategoriaSpecificationBuilder categoriaSpecificationBuilder =

                new CategoriaSpecificationBuilder(params);

        Specification<Categoria> spec =  categoriaSpecificationBuilder.build();

        return categoriaRepository.findAll(spec, pageable);
    }


}
