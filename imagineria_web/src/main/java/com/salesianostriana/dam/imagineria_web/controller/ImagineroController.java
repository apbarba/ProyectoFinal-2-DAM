package com.salesianostriana.dam.imagineria_web.controller;

import com.salesianostriana.dam.imagineria_web.model.Imaginero;
import com.salesianostriana.dam.imagineria_web.model.Obras;
import com.salesianostriana.dam.imagineria_web.model.dto.ImagineroDto.EditDtoImaginero;
import com.salesianostriana.dam.imagineria_web.model.dto.ImagineroDto.GetDtoImaginero;
import com.salesianostriana.dam.imagineria_web.model.dto.ObrasDTO.EditDtoObras;
import com.salesianostriana.dam.imagineria_web.search.util.SearchCriteria;
import com.salesianostriana.dam.imagineria_web.search.util.SearchCriteriaExtractor;
import com.salesianostriana.dam.imagineria_web.services.ImaginerosService;
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

    @GetMapping("/{id}")
    public Imaginero getById(@PathVariable UUID id){

        return imaginerosService.findById(id);
    }

    @GetMapping("/author/{imaginero_name}")
    public ResponseEntity<List<Imaginero>> getByImaginero(@PathVariable String name){

        return buildResponseOfAList(imaginerosService.findByName(name));
    }

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
    @PutMapping("/{id}")
    public Imaginero edit(@PathVariable UUID id, @Valid @RequestBody EditDtoImaginero edited) {

        return imaginerosService.edit(id, edited);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){

        imaginerosService.delete(id);

        return ResponseEntity
                .noContent()
                .build();
    }
}
