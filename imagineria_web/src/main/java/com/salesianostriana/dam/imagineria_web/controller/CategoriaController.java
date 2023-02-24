package com.salesianostriana.dam.imagineria_web.controller;

import com.salesianostriana.dam.imagineria_web.model.Categoria;
import com.salesianostriana.dam.imagineria_web.model.Obras;
import com.salesianostriana.dam.imagineria_web.model.dto.CategoriaDTO.EditDtoCategoria;
import com.salesianostriana.dam.imagineria_web.model.dto.CategoriaDTO.GetDtoCategoria;
import com.salesianostriana.dam.imagineria_web.search.util.SearchCriteria;
import com.salesianostriana.dam.imagineria_web.search.util.SearchCriteriaExtractor;
import com.salesianostriana.dam.imagineria_web.services.CategoriaService;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/categoria")
@RequiredArgsConstructor
public class CategoriaController {
    private final CategoriaService categoriaService;

    //@GetMapping("/")
    //public List<Categoria> getAll(){

     //   return categoriaService.findAll();
   // }

    @Operation(summary = "Obtiene todas las categorias")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado todas las categorias correctamente",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Categoria.class)),
                            examples = {@ExampleObject(
                                    value = """
                                                {
                                                    "content": [
                                                        {
                                                            "id": "c0a8000d-8665-1750-8186-6587bb010011",
                                                            "nombre": "Dolorosas",
                                                            "descripcion": "Imágenes de Dolorosas"
                                                        },
                                                        {
                                                            "id": "c0a8000d-8665-1750-8186-6587bb010012",
                                                            "nombre": "Cristos",
                                                            "descripcion": "Imágenes sobre Cristo"
                                                        },
                                                        {
                                                            "id": "c0a8000d-8665-1750-8186-6587bb010013",
                                                            "nombre": "Niños",
                                                            "descripcion": "Imágenes de niños, tanto de la virgen como de Jesús"
                                                        },
                                                        {
                                                            "id": "c0a8000d-8665-1750-8186-6587bb010014",
                                                            "nombre": "Otros",
                                                            "descripcion": "Imágenes de santos"
                                                        },
                                                        {
                                                            "id": "c0a8000d-8665-1750-8186-6587bb010015",
                                                            "nombre": "Mariana",
                                                            "descripcion": "Imágenes Marianas de la virgen"
                                                        }
                                                    ],
                                                    "pageable": {
                                                        "sort": {
                                                            "empty": true,
                                                            "sorted": false,
                                                            "unsorted": true
                                                        },
                                                        "offset": 0,
                                                        "pageSize": 10,
                                                        "pageNumber": 0,
                                                        "paged": true,
                                                        "unpaged": false
                                                    },                                  
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ninguna categorias",
                    content = @Content),
            @ApiResponse(responseCode = "401",
                    description = "No estás loggeado",
                    content = @Content)
    })
    @GetMapping("/")
    public ResponseEntity<Page<Categoria>> searchCategoria(@RequestParam(value = "search", defaultValue = "")
                                                   String search, @PageableDefault(size = 10, page = 0) Pageable pageable){

        List<SearchCriteria> params = SearchCriteriaExtractor.extractSearchCriteriaList(search);

        Page<Categoria> result = categoriaService.search(params, pageable);

        if (result.isEmpty()){

            return ResponseEntity
                    .notFound()
                    .build();
        }

        return  ResponseEntity
                .ok(result);
    }

    private ResponseEntity<List<Categoria>> buildResponseOfAList(List<Categoria> categorias){

        if (categorias.isEmpty())

            return ResponseEntity
                    .notFound()
                    .build();
        else

            return ResponseEntity
                    .ok(categorias);
    }

    @Operation(summary = "Se obtiene los detalles de la categoria por su id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Categoria encontrada",
                    content = {@Content(mediaType = "aplication/json",
                            schema = @Schema(implementation = Categoria.class),
                            examples = {@ExampleObject(
                                    value = """
                                                {
                                                    "id": "c0a8000d-8665-1750-8186-6587bb010011",
                                                    "nombre": "Dolorosas",
                                                    "descripcion": "Imágenes de Dolorosas"
                                                }                                                                          
                                            """
                            )}
                    )}),
            @ApiResponse(
                    responseCode = "404",
                    description = "Categoria inexistente",
                    content = @Content),
            @ApiResponse(
                    responseCode = "401",
                    description = "No está loggeado",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public Categoria getById(@PathVariable UUID id){

        return categoriaService.findById(id);
    }

    @Operation(summary = "Crea una nueva categoria")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Categoria reada",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Categoria.class),
                            examples = {@ExampleObject(
                                    value = """
                                                {
                                                    "id": "ac1b036d-8682-1172-8186-82a63a510001",
                                                    "nombre": "Semana Santa",
                                                    "descripcion": "Imágenes en especial para la semana más grande"
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
    public ResponseEntity<Categoria> createNewCategoria(@Valid @RequestBody GetDtoCategoria categoria) {

        Categoria created = categoriaService.save(categoria);

        URI createdURI = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity
                .created(createdURI)
                .body(created);

    }

    @Operation(summary = "Modifica los datos de las categorias")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoria modificada correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Categoria.class),
                            examples = {@ExampleObject(
                                    value = """
                                                {
                                                    "id": "c0a8000d-8665-1750-8186-6587bb010011",
                                                    "nombre": "Misterios y Dolorosas",
                                                    "descripcion": "Imágenes para la semana más grande"
                                                }
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "400", description = "Datos erróneos",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "No está autorizado para realizar esta opción")
    })
    @PutMapping("/{id}")
    public Categoria edit(@PathVariable UUID id, @Valid @RequestBody EditDtoCategoria edited) {

        return categoriaService.edit(id, edited);
    }

    @Operation(summary = "Categoria eliminada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "La categoria ha sido eliminada correctamente",
                    content = {}),
            @ApiResponse(responseCode = "404",
                    description = "No se han encontrado la categoria",
                    content = @Content),
            @ApiResponse(responseCode = "401",
                    description = "No se le está permitido realizar esta opcion",
                    content = @Content),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){

        categoriaService.delete(id);

        return ResponseEntity
                .noContent()
                .build();
    }
}
