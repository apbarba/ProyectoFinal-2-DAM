package com.salesianostriana.dam.imagineria_web.controller;

import com.salesianostriana.dam.imagineria_web.model.Imaginero;
import com.salesianostriana.dam.imagineria_web.model.Obras;
import com.salesianostriana.dam.imagineria_web.repository.ObrasRepository;
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

    @GetMapping("/")
    public ResponseEntity<List<Obras>> getAll(@AuthenticationPrincipal Imaginero imaginero){

        return buildResponseOfAList(
                obrasRepository.findByImaginero(imaginero));
    }

    private ResponseEntity<List<Obras>> buildResponseOfAList(List<Obras> obras){

        if (obras.isEmpty())

            return ResponseEntity.notFound()

                    .build();
        else

            return ResponseEntity
                    .ok(obras);
    }

    @GetMapping("/id")
    public ResponseEntity<Obras> getById(@PathVariable Long id){

        return ResponseEntity
                .of(obrasRepository.findById(id));
    }

    @GetMapping("/author/{author}")
    public ResponseEntity<List<Obras>> getByImaginero(@PathVariable Imaginero imaginero){

        return buildResponseOfAList(obrasRepository.findByImaginero(imaginero));
    }

    @PostMapping("/")
    public ResponseEntity<Obras> createNewNote(@RequestBody Obras obras) {

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
    public ResponseEntity<Obras> edit(@PathVariable Long id, @RequestBody Obras edited) {

        return ResponseEntity.of(
                obrasRepository.findById(id)
                        .map(obras -> {
                            obras.setPrecio(edited.getPrecio());
                            obras.setName(edited.getName());
                            obras.setImg(edited.getImg());
                            obras.setEstilo(edited.getEstilo());
                            obras.setFecha(edited.getFecha());
                            obras.setCategoria(edited.getCategoria());
                            return obrasRepository.save(obras);
                        }));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){

        obrasRepository.deleteById(id);

        return ResponseEntity
                .noContent()
                .build();
    }


}
