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
/**
 * Servicio de obras en el que se realizan los métodos necesarios
 * para la creación de las peticiones
 */
public class ObrasService {

    private final ObrasRepository obrasRepository;

    private final ImagineroRepository imagineroRepository;

    private final StorageService storageService;
    private final CategoriaRepository categoriaRepository;
    private final ConverterDtoObras converterDtoObras;


    /**
     * Método que realiza la busqueda de la obra por su id, si no la encunetra
     * salta una excepción
     * @param id
     * @return
     */
    public Obras findById(UUID id){

        return obrasRepository.findById(id)
                .orElseThrow(() -> new ObrasNotFoundException(id));
    }

    /**
     * Método en el que realizamos la creación de una obra, y ya que nuestra obra
     * nueva requiere de ser asignada a una categorias, necesitamos el id de la categoria
     * que le asignamos para ver si existe en nuestra base de datos, si no es el caso
     * la obra no se podrá crear por no estar asignada a una categoria
     * @param createDtoObras
     * @return la obra creada
     */
    @Transactional
    public GetDtoObras save2(CreateDtoObras createDtoObras){

        String categoriaString = String.valueOf(createDtoObras.getCategoria());
        if (categoriaString == null) {
            throw new IllegalArgumentException("La categoría no puede ser null");
        }

        UUID categoriaId = UUID.fromString(String.valueOf(createDtoObras.getCategoria()));
        Optional<Categoria> categoriaOpt = categoriaRepository.findById(categoriaId);

        if (!categoriaOpt.isPresent()) {
            // La categoría no existe en la base de datos
            throw new RuntimeException("Categoria no encontrada");
        }

        Categoria categoria = categoriaOpt.get();

        Obras obras = obrasRepository.save(
                Obras.builder()
                        .name(createDtoObras.getName())
                        .titulo(createDtoObras.getTitulo())
                        .estado(createDtoObras.getEstado())
                        .estilo(createDtoObras.getEstilo())
                        .fecha(createDtoObras.getFecha())
                        .img(createDtoObras.getImg())
                        .categoria(categoria)
                        .build()
        );
        return converterDtoObras.obrasToObras(obras);
    }


    /**
     * Método que realiza la eliminnación de una obra
     * @param id, requerimos de buscarla para ver si existe y si si, la eliminamos
     */
    public void delete(UUID id){

        if (obrasRepository.existsById(id))
            obrasRepository.deleteById(id);
    }

    /**
     * Método que realiza la modificación de la obra
     * @param id, buscamos la obra por el id para poder saber sus atributos
     *            y poder modificarla. Si no existe, salta una excepción
     * @param edit, utilizamos la clase dto de edit que creamos
     *              para la facilitación de esta
     * @return la obra modificada
     */
    public Obras edit(UUID id, EditDtoObras edit){

        return obrasRepository.findById(id)
                .map(obras -> {
                    obras.setEstado(edit.getEstado());
                    obras.setPrecio(edit.getPrecio());
                    obras.setName(edit.getName());

                    return obrasRepository.save(obras);
                })
                .orElseThrow(() -> new ObrasNotFoundException());
    }

    /**
     * Método que muestra todas las obras de forma paginada, y con una
     * búsqueda más intensa y más profunda
     * @param params
     * @param pageable
     * @return
     */
    public Page<Obras> search(List<SearchCriteria> params, Pageable pageable) {

        ObrasSpecificationBuilder obrasSpecificationBuilder =

                new ObrasSpecificationBuilder(params);

        Specification<Obras> spec =  obrasSpecificationBuilder.build();

        return obrasRepository.findAll(spec, pageable);
    }

    /**
     * Método que muestra todas las obras que tenemos en la base de datos
     * de forma paginada, ordenada
     * @param pageable
     * @return todas las obras
     */
    public Page<GetDtoObras> findAllObras(Pageable pageable){

        Page<Obras> obras = obrasRepository.findAll(pageable);

        if (obras.isEmpty()){
            throw new EmptyObrasListException();

        }else {
            return obras.map(converterDtoObras::obrasToObras);
        }
    }

    //METODO QUE REALIZARÁ LA BÚSQUEDA POR NOMBRE DE OBRAS

    /**
     * Método que realiza la búsqueda por nombre de las obras,
     * ignorando las mayúsculas y las minúsculas
     * @param name, el nommbre de la obra que queremos buscar
     * @return, la obra buscada, con sus detalles
     */
    public List<Obras> buscarPorNombre(String name) {
        return obrasRepository.findByNameContainingIgnoreCase(name);
    }

}
