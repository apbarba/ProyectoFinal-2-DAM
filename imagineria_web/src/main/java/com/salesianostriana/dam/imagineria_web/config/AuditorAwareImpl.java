package com.salesianostriana.dam.imagineria_web.config;

import com.salesianostriana.dam.imagineria_web.model.Imaginero;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.awt.*;
import java.util.Optional;
import java.util.UUID;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class AuditorAwareImpl implements AuditorAware<String>{

    @Override
    public Optional<String> getCurrentAuditor(){

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();


        return Optional.ofNullable(authentication)
                .map(auth -> (Imaginero) auth.getPrincipal())
                .map(Imaginero::getId)
                .map(UUID::toString);
    }
}
