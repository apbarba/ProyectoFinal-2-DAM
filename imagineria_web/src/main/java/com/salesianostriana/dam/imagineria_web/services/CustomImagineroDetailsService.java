package com.salesianostriana.dam.imagineria_web.services;

import com.salesianostriana.dam.imagineria_web.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomImagineroDetailsService implements UserDetailsService {

    private final UserService imaginerosService;

    @Override
    public UserDetails loadUserByUsername(String username) throws org.springframework.security.core.userdetails.UsernameNotFoundException {
        return imaginerosService.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("No se encuentra un imaginero con este nombre: " + username));
    }
}
