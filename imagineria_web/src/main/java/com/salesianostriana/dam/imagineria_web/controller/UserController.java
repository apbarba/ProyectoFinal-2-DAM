package com.salesianostriana.dam.imagineria_web.controller;

import com.salesianostriana.dam.imagineria_web.files.service.StorageService;
import com.salesianostriana.dam.imagineria_web.model.Obras;
import com.salesianostriana.dam.imagineria_web.model.User;
import com.salesianostriana.dam.imagineria_web.model.dto.UserDTO.ChangePasswordRequest;
import com.salesianostriana.dam.imagineria_web.model.dto.UserDTO.CreateDtoUser;
import com.salesianostriana.dam.imagineria_web.model.dto.UserDTO.EditDtoUser;
import com.salesianostriana.dam.imagineria_web.model.dto.UserDTO.UserResponse;
import com.salesianostriana.dam.imagineria_web.model.dto.JwtDto.JwtImagineroResponse;
import com.salesianostriana.dam.imagineria_web.model.dto.LoginDto.LoginRequest;
import com.salesianostriana.dam.imagineria_web.repository.ObrasRepository;
import com.salesianostriana.dam.imagineria_web.security.jwt.access.JwtProvider;
import com.salesianostriana.dam.imagineria_web.security.jwt.refresh.RefreshToken;
import com.salesianostriana.dam.imagineria_web.security.jwt.refresh.RefreshTokenException;
import com.salesianostriana.dam.imagineria_web.security.jwt.refresh.RefreshTokenRequest;
import com.salesianostriana.dam.imagineria_web.security.jwt.refresh.RefreshTokenService;
import com.salesianostriana.dam.imagineria_web.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.Access;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final  UserService userService;

    private final AuthenticationManager authenticationManager;

    private final JwtProvider jwtProvider;
    private final StorageService storageService;

    private final RefreshTokenService refreshTokenService;
    private final ObrasRepository obrasRepository;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se muestra correctamente el perfil de usuario",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = JwtImagineroResponse.class),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "id": "c0a8000d-867f-1abe-8186-7fc3eb3d0001",
                                                "username": "apbarba2",
                                                "email": "barba.loana22@triana.salesianos.edu",
                                                "name": "Ana Admin",
                                                "createdAt": "23/02/2023 20:33:11"
                                            }
                                            """
                            )})}),
            @ApiResponse(responseCode = "401",
                    description = "No tienes permiso para realizar esta opcion",
                    content = @Content),
            @ApiResponse(responseCode = "403",
                    description = "Token expirado o no tienes acceso",
                    content = @Content),
    })
    @PostMapping("/auth/register")
    public ResponseEntity<UserResponse> createImagineroWithUserRole(@Valid @RequestBody CreateDtoUser getDtoImaginero) { //@Valid

        User imaginero = userService.createImagineroWithUserRole(getDtoImaginero);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(UserResponse.fromUser(imaginero));
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se muestra correctamente el perfil de usuario",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = JwtImagineroResponse.class),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "id": "c0a8000d-867f-1abe-8186-7fc4f4790002",
                                                "username": "apbarba",
                                                "email": "pilarbarba03@gmail.com",
                                                "name": "Ana Pilar",
                                                "createdAt": "23/02/2023 20:34:19"
                                            }
                                            """
                            )})}),
            @ApiResponse(responseCode = "401",
                    description = "No tienes permiso para realizar esta opcion",
                    content = @Content),
            @ApiResponse(responseCode = "403",
                    description = "Token expirado o no tienes acceso",
                    content = @Content),
    })
    @PostMapping("/auth/register/admin")
    public ResponseEntity<UserResponse> createImagineroWithAdminRole(@Valid @RequestBody CreateDtoUser getDtoImaginero) {

        User imaginero = userService.createImagineroWithAdminRole(getDtoImaginero);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(UserResponse.fromUser(imaginero));
    }
    @Operation(summary = "Método para loguear a un usuario ya registrado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Usuario logueado correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = LoginRequest.class),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "id": "c0a8000d-867f-1abe-8186-7fbac7930000",
                                                "username": "anabarba",
                                                "email": "gigante@gmail.com",
                                                "name": "ana",
                                                "createdAt": "23/02/2023 20:23:12",
                                                "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJjMGE4MDAwZC04NjdmLTFhYmUtODE4Ni03ZmJhYzc5MzAwMDAiLCJpYXQiOjE2NzcxODA3NzcsImV4cCI6MTE1NTc5NzExMTd9.rSXl-GDsBYbcvKUUpcDDzh8vPHWt4LdyLklWL58gms9aU09ogxriutiqhg0GewwgvOexzCDKzDecX66EWG_pig",
                                                "refreshToken": "3568912a-144a-4cea-a82a-079fa82fd2e4"
                                            }
                                             """
                            )}
                    )}),
            @ApiResponse(responseCode = "401",
                    description = "No existe, registrate",
                    content = @Content)
    })
    @PostMapping("auth/login")
    public ResponseEntity<JwtImagineroResponse> login(@RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken(authentication);

        User imaginero = (User) authentication.getPrincipal();

        refreshTokenService.deleteByUser(imaginero);

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(imaginero);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(JwtImagineroResponse.of(imaginero, token, refreshToken.getToken()));

    }

    @Operation(summary = "Modificación de la contraseña vieja por moderna")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "{Modificación con éxito",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ChangePasswordRequest.class),
                            examples = {@ExampleObject(
                                    value = """
                                         {
                                            "id": "c0a8000d-867f-1abe-8186-7fbac7930000",
                                            "username": "anabarba",
                                            "email": "gigante@gmail.com",
                                            "name": "ana",
                                            "createdAt": "23/02/2023 20:23:12"
                                        }
                                           """
                            )}
                    )}),
            @ApiResponse(responseCode = "401",
                    description = "No estas logeado",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Los datos son incorrectos",
                    content = @Content)
    })
    @PutMapping("/user/changePassword")
    public ResponseEntity<UserResponse> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest,
                                                       @AuthenticationPrincipal User loggedUser) {

        try {
            if (userService.passwordMatch(loggedUser, changePasswordRequest.getOldPassword())) {
                Optional<User> modified = userService.changePassword(
                        loggedUser.getId(), changePasswordRequest.getNewPassword());

                if (modified.isPresent())

                    return ResponseEntity.ok(UserResponse.fromUser(modified.get()));

            } else {

                throw new RuntimeException();
            }
        } catch (RuntimeException ex) {

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password Data Error");
        }

        return null;
    }
    @Operation(summary = "Usuario eliminado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "El usuario ha sido eliminado correctamente",
                    content = {}),
            @ApiResponse(responseCode = "404",
                    description = "No se han encontrado el usuario en la base de datos",
                    content = @Content),
            @ApiResponse(responseCode = "401",
                    description = "No se está loggeado",
                    content = @Content),
    })
    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> delete(@PathVariable User user){

        userService.delete(user);

        return ResponseEntity
                .noContent()
                .build();
    }
    @Operation(summary = "Detalles del usuario activo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Los detalles del usuario se muestran correctamente",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = UserResponse.class))
                    }),
            @ApiResponse(responseCode = "401",
                    description = "No se está loggeado",
                    content = @Content),
    })
    @GetMapping("/me")
    public UserResponse profile(@AuthenticationPrincipal User user){

        return UserResponse.fromUser(user);
    }
    @Operation(summary = "Se añade una obra a la lista de favoritos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha añadido correctamente la obra a la lista de favoritos",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "id": "ac1af001-868a-165e-8186-8a7669930000",
                                                "name": "ana",
                                                "password": "{bcrypt}$2a$10$OKT97urWJqSWUnKJgGtjuu2GnuASVpDZpAKZRQc3tlIr417S05WLm",
                                                "email": "gigante@gmail.com",
                                                "username": "anabarba",
                                                "verifyPassword": null,
                                                "favoritos": [
                                                    {
                                                        "id": "0528bac8-b04b-11ed-afa1-0242ac120002",
                                                        "name": "Esperanza Macarena",
                                                        "precio": 12000.0,
                                                        "titulo": "Esperanza Macarena de Madrid",
                                                        "img": "https://pbs.twimg.com/profile_images/3107512731/770c233907a6270047b9826b2abac100_400x400.jpeg",
                                                        "estado": "Adquirida",
                                                        "fecha": "2022-10-07",
                                                        "estilo": "Barroca",
                                                        "createdAt": null,
                                            }
                                            """
                            )})}),
            @ApiResponse(responseCode = "401",
                    description = "No tienes permiso para realizar esta opcion",
                    content = @Content),
            @ApiResponse(responseCode = "403",
                    description = "Token expirado o no tienes acceso",
                    content = @Content),
    })
    @PostMapping("user/{userId}/favoritos/{obraId}")
    public User addFavorito(@PathVariable UUID userId,
                            @PathVariable UUID obraId){

        return userService.addFavorito(userId, obraId);
    }
    @Operation(summary = "Se obtiene los detalles de la lista de favoritos del usuario")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Obras encontrada",
                    content = {@Content(mediaType = "aplication/json",
                            schema = @Schema(implementation = User.class),
                            examples = {@ExampleObject(
                                    value = """
                                                {
                                                     "id": "0528bac8-b04b-11ed-afa1-0242ac120002",
                                                     "name": "Esperanza Macarena",
                                                     "precio": 12000.0,
                                                     "titulo": "Esperanza Macarena de Madrid",
                                                     "img": "https://pbs.twimg.com/profile_images/3107512731/770c233907a6270047b9826b2abac100_400x400.jpeg",
                                                     "estado": "Adquirida",
                                                     "fecha": "2022-10-07",
                                                     "estilo": "Barroca",
                                                     "createdAt": null,
                                                     "user": {
                                                                "id": "ac1af001-868a-165e-8186-8a7669930000",
                                                                "name": "ana",
                                                                "password": "{bcrypt}$2a$10$OKT97urWJqSWUnKJgGtjuu2GnuASVpDZpAKZRQc3tlIr417S05WLm",
                                                                "email": "gigante@gmail.com",
                                                                "username": "anabarba",
                                                                "verifyPassword": null,
                                                            }    
                                                }                                                                              
                                            """
                            )}
                    )}),
            @ApiResponse(
                    responseCode = "404",
                    description = "Usuario inexistente",
                    content = @Content),
            @ApiResponse(
                    responseCode = "401",
                    description = "No está loggeado",
                    content = @Content)
    })
    @GetMapping("user/{id}/favoritos")
    public List<Obras> getAllFavoritos(@PathVariable UUID id){

     return userService.getFavoritos(id);
    }

    @Operation(summary = "Se obtiene la lista de obras favoritas del usuario logeado")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Obras encontradas",
                    content = {@Content(mediaType = "aplication/json",
                            schema = @Schema(implementation = User.class),
                            examples = {@ExampleObject(
                                    value = """
                                                {
                                                     "id": "0528bac8-b04b-11ed-afa1-0242ac120002",
                                                     "name": "Esperanza Macarena",
                                                     "precio": 12000.0,
                                                     "titulo": "Esperanza Macarena de Madrid",
                                                     "img": "https://pbs.twimg.com/profile_images/3107512731/770c233907a6270047b9826b2abac100_400x400.jpeg",
                                                     "estado": "Adquirida",
                                                     "fecha": "2022-10-07",
                                                     "estilo": "Barroca",
                                                     "createdAt": null,
                                                     "user": {
                                                                "id": "ac1af001-868a-165e-8186-8a7669930000",
                                                                "name": "ana",
                                                                "password": "{bcrypt}$2a$10$OKT97urWJqSWUnKJgGtjuu2GnuASVpDZpAKZRQc3tlIr417S05WLm",
                                                                "email": "gigante@gmail.com",
                                                                "username": "anabarba",
                                                                "verifyPassword": null,
                                                            }    
                                                }                                                                          
                                            """
                            )}
                    )}),
            @ApiResponse(
                    responseCode = "404",
                    description = "Usuario inexistente",
                    content = @Content),
            @ApiResponse(
                    responseCode = "401",
                    description = "No está loggeado",
                    content = @Content)
    })
    @GetMapping("user/{userId}/favorited")
    public ResponseEntity<User> getUserWithFavoritedObras(@PathVariable UUID userId) {

        User user = userService.findUserWithFavoritedObras(userId);

        return ResponseEntity.ok(user);
    }

    @Operation(summary = "Se elimina una obra favorita de la lista")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Se eliminó correctamente la obra fav",
                    content = {}),
            @ApiResponse(responseCode = "404",
                    description = "No se han encontrado el usuario o la obra",
                    content = @Content),
            @ApiResponse(responseCode = "401",
                    description = "No se está loggeado",
                    content = @Content),
    })
    @DeleteMapping("user/{userId}/favoritos/{obraId}")
    public ResponseEntity<?> removeFav(@PathVariable UUID userId,
                                       @PathVariable UUID obraId){

        userService.removeFavObra(userId, obraId);

        return ResponseEntity
                .noContent()
                .build();
    }

    @PutMapping("user/{id}/avatar")
    public ResponseEntity<User> changeUserAvatar(@PathVariable UUID id, @RequestPart("avatar")MultipartFile avatarFile){
        Optional<User> optionalUser = userService.findById(id);

        if (!optionalUser.isPresent()){
            return ResponseEntity
                    .notFound()
                    .build();
        }
        User user = optionalUser.get();
        String filename = storageService.store(avatarFile);

        user.setAvatar(filename);
        userService.save(user);

        return ResponseEntity
                .ok(user);
    }
}
