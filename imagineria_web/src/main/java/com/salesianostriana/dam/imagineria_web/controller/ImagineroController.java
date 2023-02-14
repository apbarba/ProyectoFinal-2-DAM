package com.salesianostriana.dam.imagineria_web.controller;

import com.salesianostriana.dam.imagineria_web.model.Imaginero;
import com.salesianostriana.dam.imagineria_web.model.dto.ImagineroDTO.CreateDtoImaginero;
import com.salesianostriana.dam.imagineria_web.model.dto.ImagineroDTO.ImagineroResponse;
import com.salesianostriana.dam.imagineria_web.model.dto.JwtDto.JwtImagineroResponse;
import com.salesianostriana.dam.imagineria_web.model.dto.LoginDto.LoginRequest;
import com.salesianostriana.dam.imagineria_web.security.jwt.access.JwtProvider;
import com.salesianostriana.dam.imagineria_web.security.jwt.refresh.RefreshToken;
import com.salesianostriana.dam.imagineria_web.security.jwt.refresh.RefreshTokenException;
import com.salesianostriana.dam.imagineria_web.security.jwt.refresh.RefreshTokenRequest;
import com.salesianostriana.dam.imagineria_web.security.jwt.refresh.RefreshTokenService;
import com.salesianostriana.dam.imagineria_web.services.ImaginerosService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ImagineroController {

    private ImaginerosService imaginerosService;

    private final AuthenticationManager authenticationManager;

    private final JwtProvider jwtProvider;

    private final RefreshTokenService refreshTokenService;


    //SOLAMENTE PARA PROBRAR SI FUNCIONA EL USUARIO
    @PostMapping("/auth/register")
    public ResponseEntity<ImagineroResponse> createImagineroWithUserRole(@RequestBody CreateDtoImaginero getDtoImaginero){

        Imaginero imaginero = imaginerosService.createImaginerWithUserRole(getDtoImaginero);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ImagineroResponse.fromImaginero(imaginero));
    }

    @PostMapping("/auth/register/admin")
    public ResponseEntity<ImagineroResponse> createImagineroWithAdminRole(@RequestBody CreateDtoImaginero getDtoImaginero){

        Imaginero imaginero = imaginerosService.createImaginerWithUserRole(getDtoImaginero);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ImagineroResponse.fromImaginero(imaginero));
    }

    @PostMapping("auth/login")
    public ResponseEntity<JwtImagineroResponse> login(@RequestBody LoginRequest loginRequest){

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken(authentication);

        Imaginero imaginero = (Imaginero) authentication.getPrincipal();

        refreshTokenService.deleteByImaginero(imaginero);

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(imaginero);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(JwtImagineroResponse.of(imaginero, token, refreshToken.getToken()));

    }

    @PostMapping("/refrestoken")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest){

        String refreshToken = refreshTokenRequest.getRefreshToken();

        return refreshTokenService.findByToken(refreshToken)
                .map(refreshTokenService::verify)
                .map(RefreshToken::getImaginero)
                .map(imaginero -> {

                    String token = jwtProvider.generateToken(imaginero);

                    refreshTokenService.deleteByImaginero(imaginero);
                    RefreshToken refreshToken1 = refreshTokenService.createRefreshToken(imaginero);

                    return ResponseEntity
                            .status(HttpStatus.CREATED)
                            .body(JwtImagineroResponse.builder()
                                    .token(token)
                                    .refreshToken(refreshToken1.getToken())
                                    .build());

                }).orElseThrow(() -> new RefreshTokenException("Token no encontrado"));
    }

    //@PutMapping("/user/")
}
