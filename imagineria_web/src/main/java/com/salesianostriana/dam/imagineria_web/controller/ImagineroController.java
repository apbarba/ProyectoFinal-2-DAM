package com.salesianostriana.dam.imagineria_web.controller;

import com.salesianostriana.dam.imagineria_web.model.Imaginero;
import com.salesianostriana.dam.imagineria_web.model.Obras;
import com.salesianostriana.dam.imagineria_web.model.dto.ImagineroDto.ConverterDtoImaginero;
import com.salesianostriana.dam.imagineria_web.model.dto.ImagineroDto.CreateDtoImaginero;
import com.salesianostriana.dam.imagineria_web.model.dto.ImagineroDto.EditDtoImaginero;
import com.salesianostriana.dam.imagineria_web.model.dto.ImagineroDto.GetDtoImaginero;
import com.salesianostriana.dam.imagineria_web.model.dto.ObrasDTO.EditDtoObras;
import com.salesianostriana.dam.imagineria_web.page.PaginationOrders;
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
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/imaginero")
@RequiredArgsConstructor
public class ImagineroController {

    private final ImaginerosService imaginerosService;

    private final PaginationOrders paginationOrders;

    private final ConverterDtoImaginero converterDtoImaginero;


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
    public ResponseEntity<Page<GetDtoImaginero>> searchImaginero(@RequestParam(value = "search", defaultValue = "")
                                                                String search, @PageableDefault(size = 10, page = 0) Pageable pageable,
                                                                 @NotNull HttpServletRequest request){

        Page<GetDtoImaginero> pageImagineros = imaginerosService.findAllImagineros(pageable);
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(request.getRequestURL().toString());

        return ResponseEntity
                .ok()
                .header("link", paginationOrders.createLinkHeader(pageImagineros, uriComponentsBuilder))
                .body(pageImagineros);
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
    public GetDtoImaginero findById(@PathVariable(value = "id")UUID id){

        return converterDtoImaginero.imagineroToImaginero(imaginerosService.findById(id));

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
    public ResponseEntity<GetDtoImaginero> createNewImaginero(@Valid @RequestBody CreateDtoImaginero imaginero) {

        GetDtoImaginero created = imaginerosService.save(imaginero);

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
    public GetDtoImaginero edit(@PathVariable UUID id, @Valid @RequestBody EditDtoImaginero edited) {

        return converterDtoImaginero.imagineroToImaginero(imaginerosService.edit(id, edited));
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
