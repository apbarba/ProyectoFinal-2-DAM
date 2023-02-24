package com.salesianostriana.dam.imagineria_web.controller;

import com.salesianostriana.dam.imagineria_web.model.Imaginero;
import com.salesianostriana.dam.imagineria_web.model.User;
import com.salesianostriana.dam.imagineria_web.model.Obras;
import com.salesianostriana.dam.imagineria_web.model.dto.ObrasDTO.EditDtoObras;
import com.salesianostriana.dam.imagineria_web.repository.ObrasRepository;
import com.salesianostriana.dam.imagineria_web.search.util.SearchCriteria;
import com.salesianostriana.dam.imagineria_web.search.util.SearchCriteriaExtractor;
import com.salesianostriana.dam.imagineria_web.services.ObrasService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/obras")
@RequiredArgsConstructor
public class ObrasController {

    private final ObrasService obrasService;
    //@GetMapping("/")
    //public List<Obras> getAll(){
        //return obrasService.findAll();
 //   }
    @Operation(summary = "Obtiene todas las obras")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado todas las obras correctamente",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Obras.class)),
                            examples = {@ExampleObject(
                                    value = """
                                                {
                                                    "content": [
                                                        {
                                                            "id": "0528bac8-b04b-11ed-afa1-0242ac120002",
                                                            "name": "Esperanza Macarena",
                                                            "precio": 12000.0,
                                                            "titulo": "Esperanza Macarena de Madrid",
                                                            "img": "https://pbs.twimg.com/profile_images/3107512731/770c233907a6270047b9826b2abac100_400x400.jpeg",
                                                            "estado": "Adquirida",
                                                            "fecha": "2022-10-07",
                                                            "estilo": "Barroca",
                                                            "createdAt": null,
                                                            "imaginero": {
                                                                "id": "c0a8000d-8665-1750-8186-6587bb010001",
                                                                "name": "Antonio Eslava Rubio",
                                                                "edad": 1,
                                                                "localidad": "Sumberbakti"
                                                            }
                                                        },
                                                        {
                                                            "id": "0528bac8-b04b-11ed-afa1-0242ac120001",
                                                            "name": "María Magdalena",
                                                            "precio": 6000.0,
                                                            "titulo": "María Magdalena de las Aguas",
                                                            "img": "https://www.nazarenodesantamaria.com/fotonoticias/contenidos/titulares/santa_maria_magdalena_portada.jpg",
                                                            "estado": "Adquirida",
                                                            "fecha": "2022-10-07",
                                                            "estilo": "Barroca",
                                                            "createdAt": null,
                                                            "imaginero": "c0a8000d-8665-1750-8186-6587bb010001"
                                                        },
                                                        {
                                                            "id": "0528bac8-b04b-11ed-afa1-0242ac120003",
                                                            "name": "El Huerto",
                                                            "precio": 10000.0,
                                                            "titulo": "Cristo en la Oración en el Huerto",
                                                            "img": "https://2.bp.blogspot.com/-8MKlQWtv9AI/U0ppcgNWD8I/AAAAAAAASTQ/bcfpWqMzMtU/s1600/oracion+huerto+1.JPG",
                                                            "estado": "Adquirida",
                                                            "fecha": "2022-10-07",
                                                            "estilo": "Barroca",
                                                            "createdAt": null,
                                                            "imaginero": "c0a8000d-8665-1750-8186-6587bb010001"
                                                        },
                                                        {
                                                            "id": "0528bac8-b04b-11ed-afa1-0242ac120004",
                                                            "name": "Cautivo",
                                                            "precio": 15000.0,
                                                            "titulo": "Cristo Cautivo",
                                                            "img": "https://pbs.twimg.com/media/FnEnQDhWYAAyLv-?format=jpg&name=large",
                                                            "estado": "Adquirida",
                                                            "fecha": "2022-10-07",
                                                            "estilo": "Barroca",
                                                            "createdAt": null,
                                                            "imaginero": "c0a8000d-8665-1750-8186-6587bb010001"
                                                        },
                                                        {
                                                            "id": "0528bac8-b04b-11ed-afa1-0242ac120005",
                                                            "name": "Las Penas",
                                                            "precio": 13000.0,
                                                            "titulo": "La Virgen de las Penas",
                                                            "img": "https://pbs.twimg.com/media/DgrhkUkXkAEhlgC.jpg",
                                                            "estado": "Adquirida",
                                                            "fecha": "2022-10-07",
                                                            "estilo": "Barroco",
                                                            "createdAt": null,
                                                            "imaginero": "c0a8000d-8665-1750-8186-6587bb010001"
                                                        },
                                                        {
                                                            "id": "0528bac8-b04b-11ed-afa1-0242ac120006",
                                                            "name": "La Buena Muerte",
                                                            "precio": 20000.0,
                                                            "titulo": "Cristo de la Buena Muerte",
                                                            "img": "https://pbs.twimg.com/media/EKSmk62WwAA0HzR.jpg",
                                                            "estado": "Adquirida",
                                                            "fecha": "2022-10-07",
                                                            "estilo": "Barroco",
                                                            "createdAt": null,
                                                            "imaginero": {
                                                                "id": "c0a8000d-8665-1750-8186-6587bb010002",
                                                                "name": "Juan de Mesa",
                                                                "edad": 2,
                                                                "localidad": "Zhaxirabdain"
                                                            }
                                                        },
                                                        {
                                                            "id": "0528bac8-b04b-11ed-afa1-0242ac120007",
                                                            "name": "La Misericordia",
                                                            "precio": 20000.0,
                                                            "titulo": "Cristo de La Misericordia",
                                                            "img": "https://cinturondeesparto.com/0.1/wp-content/uploads/2017/11/f8d30d61-529c-4e97-a6dc-206948af0a18.jpg",
                                                            "estado": "Adquirida",
                                                            "fecha": "2022-10-07",
                                                            "estilo": "Barroco",
                                                            "createdAt": null,
                                                            "imaginero": "c0a8000d-8665-1750-8186-6587bb010002"
                                                        },
                                                        {
                                                            "id": "0528bac8-b04b-11ed-afa1-0242ac120008",
                                                            "name": "Gran Poder",
                                                            "precio": 40000.0,
                                                            "titulo": "Cristo del Gran Poder",
                                                            "img": "https://i.pinimg.com/originals/ec/e1/51/ece1514c7b9a415d32210c0eefb52263.jpg",
                                                            "estado": "Adquirida",
                                                            "fecha": "2022-10-07",
                                                            "estilo": "Barroco",
                                                            "createdAt": null,
                                                            "imaginero": "c0a8000d-8665-1750-8186-6587bb010002"
                                                        },
                                                        {
                                                            "id": "0528bac8-b04b-11ed-afa1-0242ac120009",
                                                            "name": "Inmaculada Concepción",
                                                            "precio": 12000.0,
                                                            "titulo": "Inmaculada Concepción",
                                                            "img": "https://static-sevilla.abc.es/media/pasionensevilla/2018/10/inmaculada_atribuida_a-juan-de-mesa.jpg",
                                                            "estado": "Adquirida",
                                                            "fecha": "2022-10-07",
                                                            "estilo": "Barroca",
                                                            "createdAt": null,
                                                            "imaginero": "c0a8000d-8665-1750-8186-6587bb010002"
                                                        },
                                                        {
                                                            "id": "0528bac8-b04b-11ed-afa1-0242ac120010",
                                                            "name": "El Valle",
                                                            "precio": 13000.0,
                                                            "titulo": "La Virgen del Valle",
                                                            "img": "https://www.elvalle.org/wp-content/uploads/2015/08/virgendelvalle-04.jpg",
                                                            "estado": "Adquirida",
                                                            "fecha": "2022-10-07",
                                                            "estilo": "Barroca",
                                                            "createdAt": null,
                                                            "imaginero": "c0a8000d-8665-1750-8186-6587bb010002"
                                                        }
                                                    ],
                                                    "pageable": {
                                                        "sort": {
                                                            "empty": true,
                                                            "unsorted": true,
                                                            "sorted": false
                                                        },
                                                        "offset": 0,
                                                        "pageSize": 10,
                                                        "pageNumber": 0,
                                                        "unpaged": false,
                                                        "paged": true
                                                    },
                                                    "last": false,
                                                    "totalPages": 5,
                                                    "totalElements": 50,
                                                    "size": 10,
                                                    "number": 0,
                                                    "sort": {
                                                        "empty": true,
                                                        "unsorted": true,
                                                        "sorted": false
                                                    },
                                                    "first": true,
                                                    "numberOfElements": 10,
                                                    "empty": false
                                                }                                     
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ninguna obra",
                    content = @Content),
            @ApiResponse(responseCode = "401",
            description = "No estás loggeado",
            content = @Content)
    })
    @GetMapping("/")
    public ResponseEntity<Page<Obras>> searchObras(@RequestParam(value = "search", defaultValue = "")
                                                   String search, @PageableDefault(size = 52, page = 0)Pageable pageable){

        List<SearchCriteria> params = SearchCriteriaExtractor.extractSearchCriteriaList(search);

        Page<Obras> result = obrasService.search(params, pageable);

        if (result.isEmpty()){

            return ResponseEntity
                    .notFound()
                    .build();
        }

        return  ResponseEntity
                .ok(result);
    }

    private ResponseEntity<List<Obras>> buildResponseOfAList(List<Obras> obras){

        if (obras.isEmpty())

            return ResponseEntity
                    .notFound()
                    .build();
        else

            return ResponseEntity
                    .ok(obras);
    }
    @Operation(summary = "Se obtiene los detalles de la obra por su id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Obra encontrada",
                    content = {@Content(mediaType = "aplication/json",
                            schema = @Schema(implementation = Obras.class),
                            examples = {@ExampleObject(
                                    value = """
                                                "id": "0528bac8-b04b-11ed-afa1-0242ac120002",
                                                            "name": "Esperanza Macarena",
                                                            "precio": 12000.0,
                                                            "titulo": "Esperanza Macarena de Madrid",
                                                            "img": "https://pbs.twimg.com/profile_images/3107512731/770c233907a6270047b9826b2abac100_400x400.jpeg",
                                                            "estado": "Adquirida",
                                                            "fecha": "2022-10-07",
                                                            "estilo": "Barroca",
                                                            "createdAt": null,
                                                            "imaginero": {
                                                                "id": "c0a8000d-8665-1750-8186-6587bb010001",
                                                                "name": "Antonio Eslava Rubio",
                                                                "edad": 1,
                                                                "localidad": "Sumberbakti"
                                                            }                                                                              
                                            """
                            )}
                    )}),
            @ApiResponse(
                    responseCode = "404",
                    description = "Obra inexistente",
                    content = @Content),
            @ApiResponse(
                    responseCode = "401",
                    description = "No está logged",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public Obras getById(@PathVariable UUID id){

        return obrasService.findById(id);
    }

    @Operation(summary = "Crea una nueva obra")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Obrac reada",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Obras.class),
                            examples = {@ExampleObject(
                                    value = """
                                                {
                                                    {
                                                    "id": "0a281c47-4073-4cd6-b5ce-f43b989ccfcc",
                                                    "name": "Lágrimas",
                                                    "precio": 12000.0,
                                                    "titulo": "Santísima de las Lágrimas",
                                                    "img": "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRfM9A8DoBZuJYIdOJ8IA-YWhbFcAwC4dAQ4eUxSLEI&s",
                                                    "estado": "Finalizada",
                                                    "fecha": null,
                                                    "estilo": null,
                                                    "createdAt": "2023-02-23T21:46:09.7526577",
                                                } 
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "400", description = "Datos erróneos",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "No está autorizado para realizar esta petición",
                    content = @Content)
    })
    @PostMapping("/")
    public ResponseEntity<Obras> createNewObras(@Valid @RequestBody EditDtoObras obras) {

        Obras created = obrasService.save(obras);

        URI createdURI = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity
                .created(createdURI)
                .body(created);

    }

   // @PostMapping("/")
  //  public ResponseEntity<Obras> createNewObras(@Valid @RequestPart("obras") EditDtoObras obras,
    //                                            @RequestPart("file")MultipartFile file) {

      //  Obras created = obrasService.save(obras, file);

        //URI createdURI = ServletUriComponentsBuilder
          //      .fromCurrentRequest()
            //    .path("/{id}")
              //  .buildAndExpand(created.getId()).toUri();

      //  return ResponseEntity
        //        .created(createdURI)
          //      .body(created);

   // }
   @Operation(summary = "Modifica los datos de las obras")
   @ApiResponses(value = {
           @ApiResponse(responseCode = "200", description = "Categoria modificada correctamente",
                   content = {@Content(mediaType = "application/json",
                           schema = @Schema(implementation = Obras.class),
                           examples = {@ExampleObject(
                                   value = """
                                                {
                                                    {
                                                    "id": "0a281c47-4073-4cd6-b5ce-f43b989ccfcc",
                                                    "name": "Lágrimas de Humildad",
                                                    "precio": 12000.0,
                                                    "titulo": "Santísima de las Lágrimas",
                                                    "img": "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRfM9A8DoBZuJYIdOJ8IA-YWhbFcAwC4dAQ4eUxSLEI&s",
                                                    "estado": "Finalizada",
                                                    "fecha": null,
                                                    "estilo": null,
                                                    "createdAt": "2023-02-23T21:46:09.7526577",
                                                } 
                                            """
                           )}
                   )}),
           @ApiResponse(responseCode = "400", description = "Datos erróneos",
                   content = @Content),
            @ApiResponse(responseCode = "401", description = "No está autorizado para realizar esta opción")
   })
    @PutMapping("/{id}")
    public Obras edit(@PathVariable UUID id, @Valid @RequestBody EditDtoObras edited) {

        return obrasService.edit(id, edited);
    }
    @Operation(summary = "Obra eliminada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "ELa obra ha sido eliminada correctamente",
                    content = {}),
            @ApiResponse(responseCode = "404",
                    description = "No se han encontrado la obra",
                    content = @Content),
            @ApiResponse(responseCode = "401",
                    description = "No se le está permitido realizar esta opcion",
                    content = @Content),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){

        obrasService.delete(id);

        return ResponseEntity
                .noContent()
                .build();
    }


}
