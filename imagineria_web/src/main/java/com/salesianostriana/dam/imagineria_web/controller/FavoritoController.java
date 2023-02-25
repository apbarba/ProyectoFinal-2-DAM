package com.salesianostriana.dam.imagineria_web.controller;

import com.salesianostriana.dam.imagineria_web.services.FavoritosService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/favoritos")
public class FavoritoController {

    private FavoritosService favoritosService;

    @PostMapping("/{userId}/obras/{obraId}")
    public ResponseEntity<?> marcarComoFavorita(@PathVariable UUID userId,
                                                @PathVariable UUID obraId){

        favoritosService.marcarComoFavorita(userId, obraId);

        return ResponseEntity
                .ok()
                .build();
    }
}
