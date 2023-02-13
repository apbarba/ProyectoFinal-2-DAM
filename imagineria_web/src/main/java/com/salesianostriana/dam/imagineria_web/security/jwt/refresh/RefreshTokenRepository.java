package com.salesianostriana.dam.imagineria_web.security.jwt.refresh;

import com.salesianostriana.dam.imagineria_web.model.Imaginero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Imaginero> {

    Optional<RefreshToken> findByToken(String token);

    @Modifying
    int deleteByImaginero(Imaginero imaginero);
}
