package com.salesianostriana.dam.imagineria_web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;

public class AuditConfig {

    @Bean
    public AuditorAware<String> auditorAware(){

        return new AuditorAwareImpl();
    }
}
