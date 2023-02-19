package com.salesianostriana.dam.imagineria_web.controller;

import com.salesianostriana.dam.imagineria_web.model.Categoria;
import com.salesianostriana.dam.imagineria_web.model.Obras;
import com.salesianostriana.dam.imagineria_web.model.dto.CategoriaDTO.EditDtoCategoria;
import com.salesianostriana.dam.imagineria_web.model.dto.CategoriaDTO.GetDtoCategoria;
import com.salesianostriana.dam.imagineria_web.search.util.SearchCriteria;
import com.salesianostriana.dam.imagineria_web.search.util.SearchCriteriaExtractor;
import com.salesianostriana.dam.imagineria_web.services.CategoriaService;
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

    @GetMapping("/{id}")
    public Categoria getById(@PathVariable UUID id){

        return categoriaService.findById(id);
    }

    @GetMapping("/author/{categoria_name}")
    public ResponseEntity<List<Categoria>> getByCategoria(@PathVariable String name){

        return buildResponseOfAList(categoriaService.findByName(name));
    }

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

   // @PreAuthorize("@categoriaRepository.findById(#id).orElse(new com.salesianostriana.dam.imagineria_web.model.Categoria()).author == authentication.principal.getId().toString()")
    @PutMapping("/{id}")
    public Categoria edit(@PathVariable UUID id, @Valid @RequestBody EditDtoCategoria edited) {

        return categoriaService.edit(id, edited);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){

        categoriaService.delete(id);

        return ResponseEntity
                .noContent()
                .build();
    }
}
