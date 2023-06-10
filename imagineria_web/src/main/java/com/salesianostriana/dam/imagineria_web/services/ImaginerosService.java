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
public class ImaginerosService {

    private final ImagineroRepository imagineroRepository;
    private final UserRepository userRepository;
    private final StorageService storageService;
    private final ConverterDtoImaginero converterDtoImaginero;
    private final ObrasRepository obrasRepository;


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

 /*   public Imaginero save(CreateDtoImaginero imaginero, MultipartFile file, User user){

        Optional<User> user1 = userRepository.findById(user.getId());

        if (user1.isEmpty()){

            throw new UserNotFoundException(user.getId().toString());
        }else {

            String uri = storageService.store(file);

            Imaginero imaginero1 = converterDtoImaginero.createImaginero(imaginero, uri);

            List<Obras> obras = new ArrayList<>();

            for (Obras obras1 : imaginero.getObras()){

                Obras obras2 = obrasRepository.findById(obras1.getId()).get();

                obras.add(obras2);
            }

            imaginero1.getObras().addAll(obras);

            return imagineroRepository.save(imaginero1);
        }

    }*/

    public GetDtoImaginero save(CreateDtoImaginero create){

        Imaginero imaginero = converterDtoImaginero.createImaginero(create);
        imagineroRepository.save(imaginero);

        return converterDtoImaginero.imagineroToImaginero(imaginero);

    }

    public Page<GetDtoImaginero> findAllImagineros(Pageable pageable){

        Page<Imaginero> imaginero = imagineroRepository.findAll(pageable);

        if (imaginero.isEmpty()){
            throw new EmptyImagineroException();

        }else {
            return imaginero.map(converterDtoImaginero::imagineroToImaginero);
        }
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

    //ELIMINA AL IMAGINERO JUNTO A SUS OBRAS ASOCIADAS
    public void deleteWithObras(UUID id){
        if (imagineroRepository.existsById(id)){
            Imaginero imaginero = imagineroRepository.findById(id).get();
            obrasRepository.deleteAll(imaginero.getObras());
            imagineroRepository.deleteById(id);
        }
    }


    //ELIMINACION DE IMAGINERO SIN QUE SE ELIMINEN LAS OBRAS QUE ESTÁN ASOCIADAS A ÉL
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

    public Page<Imaginero> search(List<SearchCriteria> params, Pageable pageable) {

        ImagineroSpecificationBuilder imagineroSpecificationBuilder =

                new ImagineroSpecificationBuilder(params);

        Specification<Imaginero> spec =  imagineroSpecificationBuilder.build();

        return imagineroRepository.findAll(spec, pageable);
    }

    public Optional<Imaginero> findByObras(UUID id){

        return imagineroRepository.findByIdWithObras(id);
    }
}
