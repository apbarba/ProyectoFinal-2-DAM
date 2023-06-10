package com.salesianostriana.dam.imagineria_web;

import com.salesianostriana.dam.imagineria_web.model.User;
import com.salesianostriana.dam.imagineria_web.model.UserRole;
import com.salesianostriana.dam.imagineria_web.model.dto.UserDTO.CreateDtoUser;
import com.salesianostriana.dam.imagineria_web.services.ObrasService;
import com.salesianostriana.dam.imagineria_web.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import java.util.Collections;
import java.util.EnumSet;
import java.util.UUID;

import static com.salesianostriana.dam.imagineria_web.model.UserRole.ADMIN;

@Component
@RequiredArgsConstructor
public class Pruebita {

        private final UserService userService;
        private final PasswordEncoder passwordEncoder;

        @PostConstruct
        public void run() {

        User user = User.builder()
                .id(UUID.fromString("ac1b036d-865e-108e-8186-5e90eaf30000"))
                .name("ana").email("gigante@gmail.com")
                .username("anabarba")
                .password(passwordEncoder.encode("soso"))
                .accountNonExpired(false)
                .enabled(true)
                .rol(EnumSet.of(ADMIN))
                .avatar("perfileIcon.png")
                .build();
        userService.save(user);

        }
}
