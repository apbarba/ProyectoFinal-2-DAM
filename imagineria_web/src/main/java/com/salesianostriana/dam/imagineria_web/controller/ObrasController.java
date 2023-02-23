package com.salesianostriana.dam.imagineria_web.controller;

import com.salesianostriana.dam.imagineria_web.model.Imaginero;
import com.salesianostriana.dam.imagineria_web.model.User;
import com.salesianostriana.dam.imagineria_web.model.Obras;
import com.salesianostriana.dam.imagineria_web.model.dto.ObrasDTO.EditDtoObras;
import com.salesianostriana.dam.imagineria_web.repository.ObrasRepository;
import com.salesianostriana.dam.imagineria_web.search.util.SearchCriteria;
import com.salesianostriana.dam.imagineria_web.search.util.SearchCriteriaExtractor;
import com.salesianostriana.dam.imagineria_web.services.ObrasService;
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

    @GetMapping("/")
    public ResponseEntity<Page<Obras>> searchObras(@RequestParam(value = "search", defaultValue = "")
                                                   String search, @PageableDefault(size = 10, page = 0)Pageable pageable){

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

    @GetMapping("/{id}")
    public Obras getById(@PathVariable UUID id){

        return obrasService.findById(id);
    }

   // @GetMapping("/author/{imaginero}")
    //public ResponseEntity<List<Obras>> getByImaginero(@PathVariable Imaginero imaginero){

    //    return buildResponseOfAList(obrasService.findByImaginero(imaginero));
 //   }

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

   // @PreAuthorize("@obrasRepository.findById(#id).orElse(new com.salesianostriana.dam.model.Obras()).author == authentication.principal.getId().toString()")
    @PutMapping("/{id}")
    public Obras edit(@PathVariable UUID id, @Valid @RequestBody EditDtoObras edited) {

        return obrasService.edit(id, edited);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){

        obrasService.delete(id);

        return ResponseEntity
                .noContent()
                .build();
    }


}
