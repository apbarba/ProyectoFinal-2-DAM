package com.salesianostriana.dam.imagineria_web.controller;

import com.salesianostriana.dam.imagineria_web.exception.UserException.UserNotFoundException;
import com.salesianostriana.dam.imagineria_web.model.Favoritos;
import com.salesianostriana.dam.imagineria_web.model.User;
import com.salesianostriana.dam.imagineria_web.search.util.SearchCriteria;
import com.salesianostriana.dam.imagineria_web.search.util.SearchCriteriaExtractor;
import com.salesianostriana.dam.imagineria_web.services.FavoritosService;
import com.salesianostriana.dam.imagineria_web.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/favoritos")
@RequiredArgsConstructor
public class FavoritosController {

    private final FavoritosService favoritosService;

    private final UserService userService;

    //PETICIÓN POST PARA INDICAR UN FAV
    @PostMapping("/{idUser}")
    public ResponseEntity<Favoritos> indicarFav(@PathVariable UUID idUser, @RequestBody Favoritos favoritos){

        User user = userService.findById(idUser)
                .orElseThrow(() -> new UserNotFoundException("Usuario no encontrado"));

 //       favoritos.addUsuario(user);

        Favoritos guardarFav = favoritosService.guardarFav(favoritos);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(guardarFav.getId())
                .toUri();

        return ResponseEntity.created(location).body(guardarFav);
    }

   // @GetMapping("/")
    //public List<Favoritos> findAllFav(){

   //     return favoritosService.findAll();
  //  }

    @GetMapping("/")
    public ResponseEntity<Page<Favoritos>> searchFav(@RequestParam(value = "search", defaultValue = "")
                                                   String search, @PageableDefault(size = 10, page = 0) Pageable pageable){

        List<SearchCriteria> params = SearchCriteriaExtractor.extractSearchCriteriaList(search);

        Page<Favoritos> result = favoritosService.search(params, pageable);

        if (result.isEmpty()){

            return ResponseEntity
                    .notFound()
                    .build();
        }

        return  ResponseEntity
                .ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){

        favoritosService.delete(id);

        return ResponseEntity
                .noContent()
                .build();
    }

}
