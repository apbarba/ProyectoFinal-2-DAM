package com.salesianostriana.dam.imagineria_web.services;

import com.salesianostriana.dam.imagineria_web.exception.EmptyObrasListException;
import com.salesianostriana.dam.imagineria_web.exception.ObrasNotFoundException;
import com.salesianostriana.dam.imagineria_web.files.service.StorageService;
import com.salesianostriana.dam.imagineria_web.model.Categoria;
import com.salesianostriana.dam.imagineria_web.model.Imaginero;
import com.salesianostriana.dam.imagineria_web.model.User;
import com.salesianostriana.dam.imagineria_web.model.Obras;
import com.salesianostriana.dam.imagineria_web.model.dto.ObrasDTO.ConverterDtoObras;
import com.salesianostriana.dam.imagineria_web.model.dto.ObrasDTO.CreateDtoObras;
import com.salesianostriana.dam.imagineria_web.model.dto.ObrasDTO.EditDtoObras;
import com.salesianostriana.dam.imagineria_web.model.dto.ObrasDTO.GetDtoObras;
import com.salesianostriana.dam.imagineria_web.repository.CategoriaRepository;
import com.salesianostriana.dam.imagineria_web.repository.ImagineroRepository;
import com.salesianostriana.dam.imagineria_web.repository.ObrasRepository;
import com.salesianostriana.dam.imagineria_web.search.spec.ObrasSearch.ObrasSpecificationBuilder;
import com.salesianostriana.dam.imagineria_web.search.util.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ObrasService {

    private final ObrasRepository obrasRepository;

    private final ImagineroRepository imagineroRepository;

    private final StorageService storageService;
    private final CategoriaRepository categoriaRepository;
    private final ConverterDtoObras converterDtoObras;

    //  public List<Obras> findByImaginero(Imaginero imaginero){

       // List<Obras> obras = obrasRepository.findByImaginero(imaginero);

       // if (obras.isEmpty()){

     //       throw new EmptyObrasListException();
     //   }

     //   return obras;
   // }

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

    public GetDtoObras save(CreateDtoObras create){

        Obras obras = converterDtoObras.createObra(create);
        obrasRepository.save(obras);

        return converterDtoObras.obrasToObras(obras);

    }

    @Transactional
    public Obras save2(CreateDtoObras createDtoObras/*, MultipartFile file*/){

        //String filename = storageService.store(file);

      //  Imaginero imaginero = imagineroRepository.getReferenceById(createDtoObras.getImaginero().getId());
        Optional<Categoria> categoria = categoriaRepository.findById(createDtoObras.getCategoria());
        Obras obras = obrasRepository.save(
                Obras.builder()
                        .name(createDtoObras.getName())
                        .titulo(createDtoObras.getTitulo())
                        .estado(createDtoObras.getEstado())
                        .estilo(createDtoObras.getEstilo())
                        .fecha(createDtoObras.getFecha())
                        .img(createDtoObras.getImg())
                        .precio(createDtoObras.getPrecio())
                        .categoria(categoria.get())
                       // .imaginero(imaginero)
                       // .nombreImaginero(imaginero.getName())
                        .build()
        );

        return obras;
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

    @Transactional
    public Obras save(EditDtoObras getDtoObras, MultipartFile file) {

        String filename = storageService.store(file);

        Obras obras = obrasRepository.save(

                Obras.builder()
                        .img(filename)
                        .build()
        );
        return obras;
    }

    public Page<GetDtoObras> findAllObras(Pageable pageable){

        Page<Obras> obras = obrasRepository.findAll(pageable);

        if (obras.isEmpty()){
            throw new EmptyObrasListException();

        }else {
            return obras.map(converterDtoObras::obrasToObras);
        }
    }

}
