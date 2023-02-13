package com.salesianostriana.dam.imagineria_web.services;

import com.salesianostriana.dam.imagineria_web.exception.ImagineroNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomImagineroDetailsService implements UserDetailsService {

    private final ImaginerosService imaginerosService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return imaginerosService.findByUsername(username)
                .orElseThrow(() -> new ImagineroNotFoundException("No se encuentra un imaginero con este nombre: " + username));
    }
}
