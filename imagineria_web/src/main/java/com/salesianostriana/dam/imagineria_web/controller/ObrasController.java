package com.salesianostriana.dam.imagineria_web.controller;

import com.salesianostriana.dam.imagineria_web.model.User;
import com.salesianostriana.dam.imagineria_web.model.Obras;
import com.salesianostriana.dam.imagineria_web.repository.ObrasRepository;
import com.salesianostriana.dam.imagineria_web.services.ObrasService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/obras")
@RequiredArgsConstructor
public class ObrasController {

    private final ObrasRepository obrasRepository;

    private final ObrasService obrasService;

    @GetMapping("/")
    public ResponseEntity<List<Obras>> getAll(@AuthenticationPrincipal User imaginero){

        return buildResponseOfAList(
                obrasService.findByImaginero(imaginero));
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
    public Obras getById(@PathVariable Long id){

        return obrasService.findById(id);
    }

    @GetMapping("/author/{author}")
    public ResponseEntity<List<Obras>> getByImaginero(@PathVariable User imaginero){

        return buildResponseOfAList(obrasService.findByImaginero(imaginero));
    }

    @PostMapping("/")
    public ResponseEntity<Obras> createNewObras(@RequestBody Obras obras) {

        Obras created = obrasRepository.save(obras);

        URI createdURI = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity
                .created(createdURI)
                .body(created);

    }

    @PreAuthorize("@obrasRepository.findById(#id).orElse(new com.salesianostriana.dam.model.Obras()).author == authentication.principal.getId().toString()")
    @PutMapping("/{id}")
    public Obras edit(@PathVariable Long id, @RequestBody Obras edited) {

        return obrasService.edit(id, edited);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){

        obrasService.delete(id);

        return ResponseEntity
                .noContent()
                .build();
    }


}
