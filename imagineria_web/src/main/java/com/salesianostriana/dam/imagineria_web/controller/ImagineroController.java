package com.salesianostriana.dam.imagineria_web.controller;

import com.salesianostriana.dam.imagineria_web.model.Imaginero;
import com.salesianostriana.dam.imagineria_web.model.Obras;
import com.salesianostriana.dam.imagineria_web.model.dto.ImagineroDto.EditDtoImaginero;
import com.salesianostriana.dam.imagineria_web.model.dto.ImagineroDto.GetDtoImaginero;
import com.salesianostriana.dam.imagineria_web.model.dto.ObrasDTO.EditDtoObras;
import com.salesianostriana.dam.imagineria_web.search.util.SearchCriteria;
import com.salesianostriana.dam.imagineria_web.search.util.SearchCriteriaExtractor;
import com.salesianostriana.dam.imagineria_web.services.ImaginerosService;
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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/imaginero")
@RequiredArgsConstructor
public class ImagineroController {

    private final ImaginerosService imaginerosService;

  //  @GetMapping("/")
  //  public List<Imaginero> getAll(){

  //      return imaginerosService.findAll();
 //   }
  @Operation(summary = "Obtiene todos los imagineros")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200",
                  description = "Se han encontrado todas los imagineros correctamente",
                  content = {@Content(mediaType = "application/json",
                          array = @ArraySchema(schema = @Schema(implementation = Imaginero.class)),
                          examples = {@ExampleObject(
                                  value = """
                                              {
                                                  "content": [
                                                      {
                                                          "id": "c0a8000d-8665-1750-8186-6587bb010001",
                                                          "name": "Antonio Eslava Rubio",
                                                          "edad": 1,
                                                          "localidad": "Sumberbakti"
                                                      },
                                                      {
                                                          "id": "c0a8000d-8665-1750-8186-6587bb010002",
                                                          "name": "Juan de Mesa",
                                                          "edad": 2,
                                                          "localidad": "Zhaxirabdain"
                                                      },
                                                      {
                                                          "id": "c0a8000d-8665-1750-8186-6587bb010003",
                                                          "name": "Pedro Roldán",
                                                          "edad": 3,
                                                          "localidad": "Topola"
                                                      },
                                                      {
                                                          "id": "c0a8000d-8665-1750-8186-6587bb010004",
                                                          "name": "Luisa Roldán",
                                                          "edad": 4,
                                                          "localidad": "Santa Rita Aplaya"
                                                      },
                                                      {
                                                          "id": "c0a8000d-8665-1750-8186-6587bb010005",
                                                          "name": "Luis Álvarez Duarte",
                                                          "edad": 5,
                                                          "localidad": "Kašperské Hory"
                                                      }
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
    public ResponseEntity<Page<Imaginero>> searchImaginero(@RequestParam(value = "search", defaultValue = "")
                                                   String search, @PageableDefault(size = 5, page = 0) Pageable pageable){

        List<SearchCriteria> params = SearchCriteriaExtractor.extractSearchCriteriaList(search);

        Page<Imaginero> result = imaginerosService.search(params, pageable);

        if (result.isEmpty()){

            return ResponseEntity
                    .notFound()
                    .build();
        }

        return  ResponseEntity
                .ok(result);
    }

    private ResponseEntity<List<Imaginero>> buildResponseOfAList(List<Imaginero> imagineros){

        if (imagineros.isEmpty())

            return ResponseEntity
                    .notFound()
                    .build();
        else

            return ResponseEntity
                    .ok(imagineros);
    }
    @Operation(summary = "Se obtiene los detalles del imaginero por su id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Obra encontrada",
                    content = {@Content(mediaType = "aplication/json",
                            schema = @Schema(implementation = Imaginero.class),
                            examples = {@ExampleObject(
                                    value = """
                                                {
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
                    description = "Imaginero no encontrado",
                    content = @Content),
            @ApiResponse(
                    responseCode = "401",
                    description = "No está loggeado",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public Imaginero getById(@PathVariable UUID id){

        return imaginerosService.findById(id);
    }

    @Operation(summary = "Crea un nuevo imaginero")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Imaginero creado",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Imaginero.class),
                            examples = {@ExampleObject(
                                    value = """
                                                {
                                                    "id": "c0a8000d-8665-1750-8186-6587bb010006",
                                                    "name" : "Maria",
                                                    "edad" : 45,
                                                    "localidad" : "Granada"
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
    public ResponseEntity<Imaginero> createNewImaginero(@Valid @RequestBody GetDtoImaginero imaginero) {

        Imaginero created = imaginerosService.save(imaginero);

        URI createdURI = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity
                .created(createdURI)
                .body(created);

    }
    @Operation(summary = "Modifica los datos de un imaginero")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Imaginero modificado correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Obras.class),
                            examples = {@ExampleObject(
                                    value = """
                                                {
                                                    "id": "c0a8000d-8665-1750-8186-6587bb010001",
                                                    "name": "Antonio Eslava Rubio",
                                                    "edad": 25,
                                                    "localidad": "Granada"
                                                }
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "400", description = "Datos erróneos",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "No está autorizado para realizar esta opción")
    })
    @PutMapping("/{id}")
    public Imaginero edit(@PathVariable UUID id, @Valid @RequestBody EditDtoImaginero edited) {

        return imaginerosService.edit(id, edited);
    }

    @Operation(summary = "Se elimina a un imaginero, pero no se eliminan sus oobras")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Imaginero eliminado correctamente",
                    content = {}),
            @ApiResponse(responseCode = "404",
                    description = "No se han encontrado la obra  en la base de datos",
                    content = @Content),
            @ApiResponse(responseCode = "401",
                    description = "No requiere de permisos para realizar esta opción",
                    content = @Content),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){

        imaginerosService.delete(id);

        return ResponseEntity
                .noContent()
                .build();
    }
}
