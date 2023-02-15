package com.salesianostriana.dam.imagineria_web.controller;

import com.salesianostriana.dam.imagineria_web.model.User;
import com.salesianostriana.dam.imagineria_web.model.dto.UserDTO.ChangePasswordRequest;
import com.salesianostriana.dam.imagineria_web.model.dto.UserDTO.CreateDtoImaginero;
import com.salesianostriana.dam.imagineria_web.model.dto.UserDTO.UserResponse;
import com.salesianostriana.dam.imagineria_web.model.dto.JwtDto.JwtImagineroResponse;
import com.salesianostriana.dam.imagineria_web.model.dto.LoginDto.LoginRequest;
import com.salesianostriana.dam.imagineria_web.security.jwt.access.JwtProvider;
import com.salesianostriana.dam.imagineria_web.security.jwt.refresh.RefreshToken;
import com.salesianostriana.dam.imagineria_web.security.jwt.refresh.RefreshTokenException;
import com.salesianostriana.dam.imagineria_web.security.jwt.refresh.RefreshTokenRequest;
import com.salesianostriana.dam.imagineria_web.security.jwt.refresh.RefreshTokenService;
import com.salesianostriana.dam.imagineria_web.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final  UserService userService;

    private final AuthenticationManager authenticationManager;

    private final JwtProvider jwtProvider;

    private final RefreshTokenService refreshTokenService;


    //SOLAMENTE PARA PROBRAR SI FUNCIONA EL USUARIO
    @PostMapping("/auth/register")
    public ResponseEntity<UserResponse> createImagineroWithUserRole(@RequestBody CreateDtoImaginero getDtoImaginero) {

        User imaginero = userService.createImagineroWithUserRole(getDtoImaginero);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(UserResponse.fromUser(imaginero));
    }

    @PostMapping("/auth/register/admin")
    public ResponseEntity<UserResponse> createImagineroWithAdminRole(@RequestBody CreateDtoImaginero getDtoImaginero) {

        User imaginero = userService.createImagineroWithUserRole(getDtoImaginero);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(UserResponse.fromUser(imaginero));
    }

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

    @PostMapping("/refrestoken")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {

        String refreshToken = refreshTokenRequest.getRefreshToken();

        return refreshTokenService.findByToken(refreshToken)
                .map(refreshTokenService::verify)
                .map(RefreshToken::getUser)
                .map(imaginero -> {

                    String token = jwtProvider.generateToken(imaginero);

                    refreshTokenService.deleteByUser(imaginero);
                    RefreshToken refreshToken1 = refreshTokenService.createRefreshToken(imaginero);

                    return ResponseEntity
                            .status(HttpStatus.CREATED)
                            .body(JwtImagineroResponse.builder()
                                    .token(token)
                                    .refreshToken(refreshToken1.getToken())
                                    .build());

                }).orElseThrow(() -> new RefreshTokenException("Token no encontrado"));
    }

    @PutMapping("/user/changePassword")
    public ResponseEntity<UserResponse> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest,
                                                       @AuthenticationPrincipal User loggedUser) {

        try {
            if (userService.passwordMatch(loggedUser, changePasswordRequest.getOldPassword())) {
                Optional<User> modified = userService.editPassword(
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
}
