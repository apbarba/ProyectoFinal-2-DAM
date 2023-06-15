package com.salesianostriana.dam.imagineria_web.services;

import com.salesianostriana.dam.imagineria_web.exception.*;
import com.salesianostriana.dam.imagineria_web.files.service.StorageService;
import com.salesianostriana.dam.imagineria_web.model.Imaginero;
import com.salesianostriana.dam.imagineria_web.model.Obras;
import com.salesianostriana.dam.imagineria_web.model.User;
import com.salesianostriana.dam.imagineria_web.model.dto.ImagineroDto.ConverterDtoImaginero;
import com.salesianostriana.dam.imagineria_web.model.dto.ImagineroDto.CreateDtoImaginero;
import com.salesianostriana.dam.imagineria_web.model.dto.ImagineroDto.EditDtoImaginero;
import com.salesianostriana.dam.imagineria_web.model.dto.ImagineroDto.GetDtoImaginero;
import com.salesianostriana.dam.imagineria_web.model.dto.ObrasDTO.EditDtoObras;
import com.salesianostriana.dam.imagineria_web.repository.ImagineroRepository;
import com.salesianostriana.dam.imagineria_web.repository.ObrasRepository;
import com.salesianostriana.dam.imagineria_web.repository.UserRepository;
import com.salesianostriana.dam.imagineria_web.search.spec.ImagineroSearch.ImagineroSpecificationBuilder;
import com.salesianostriana.dam.imagineria_web.search.spec.ObrasSearch.ObrasSpecificationBuilder;
import com.salesianostriana.dam.imagineria_web.search.util.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
/**
 * Servicio de imaginero en el que se realizan los métodos
 * que tiene nuestra aplicación
 */
public class ImaginerosService {

    private final ImagineroRepository imagineroRepository;
    private final ConverterDtoImaginero converterDtoImaginero;
    private final ObrasRepository obrasRepository;


    /**
     * Método que busca a un imaginero por su id, en el caso de que
     * no se encuentre salta una excepción diciendo que no se ha podido
     * encontrar
     * @param id, el id del imaginero que estamos buscando
     * @return
     */
    public Imaginero findById(UUID id){

        return imagineroRepository.findById(id)
                .orElseThrow(() -> new ImagineroNotFoundException(id));
    }

    /**
     * Método que realiza la creación de un nuevo imaginero, que en el caso
     * de que no se pueda realizar, salta una excepción
     * @param create, utilizamos la clase dto que creamos para la facilitación
     *                de la creación por las variables que requerimos. También utilizamos
     *                el método que hicimos en el converter ya que ahí llamamos a las
     *                variables necesarias
     * @return
     */
    public GetDtoImaginero save(CreateDtoImaginero create){

        Imaginero imaginero = converterDtoImaginero.createImaginero(create);
        imagineroRepository.save(imaginero);

        return converterDtoImaginero.imagineroToImaginero(imaginero);

    }

    /**
     * Método que nos muestra todos los imagineros existentes con las variables necesarias
     * que declaramos en el dto método del converter
     * @param pageable, se muestran los imaginero de forma organizada, paginada
     * @return todos los imaginero paginados
     */
    public Page<GetDtoImaginero> findAllImagineros(Pageable pageable){

        Page<Imaginero> imaginero = imagineroRepository.findAll(pageable);

        if (imaginero.isEmpty()){
            throw new EmptyImagineroException();

        }else {
            return imaginero.map(converterDtoImaginero::imagineroToImaginero);
        }
    }


    /**
     * Método que realiza la modificación de datos de un imaginero
     * @param id, necesitams¡os buscar el imaginero que queremos modificar
     * @param edit, utulizamos el dto de edición para la facilitación de este y que
     *              esté más limpio el código
     * @return, los datos modificados de los imagineros
     */
    public Imaginero edit(UUID id, EditDtoImaginero edit){

        return imagineroRepository.findById(id)
                .map(imaginero -> {
                    imaginero.setEdad(edit.getEdad());
                    imaginero.setLocalidad(edit.getLocalidad());

                    return imagineroRepository.save(imaginero);
                })
                .orElseThrow(() -> new ImagineroNotFoundException());
    }

    //ELIMINA AL IMAGINERO JUNTO A SUS OBRAS ASOCIADAS

    /**
     * Método que elimina a un imaginero junto a las obras que tiene asociadas
     * @param id, necesitamos buscar al imaginero que queremos eliminar
     */
    public void deleteWithObras(UUID id){
        if (imagineroRepository.existsById(id)){
            Imaginero imaginero = imagineroRepository.findById(id).get();
            obrasRepository.deleteAll(imaginero.getObras());
            imagineroRepository.deleteById(id);
        }
    }


    /**
     * Método que elimina a un imaginero pero a sus obras asociadas no
     * @param id, necesitamos buscar al imaginero para que
     *            pueda ser eliminado
     */
    public void delete(UUID id){

        if (imagineroRepository.existsById(id)){
            Imaginero imaginero = imagineroRepository.findById(id).get();

            for(Obras obra : imaginero.getObras()){
                obra.setImaginero(null);
                obrasRepository.save(obra);
            }

            imagineroRepository.deleteById(id);
        }
    }

    /**
     * Método que muestra las obras con el filtrado y paginación. No se utiliza por problemas
     * del filtrado
     * @param params
     * @param pageable
     * @return
     */
    public Page<Imaginero> search(List<SearchCriteria> params, Pageable pageable) {

        ImagineroSpecificationBuilder imagineroSpecificationBuilder =

                new ImagineroSpecificationBuilder(params);

        Specification<Imaginero> spec =  imagineroSpecificationBuilder.build();

        return imagineroRepository.findAll(spec, pageable);
    }

}
