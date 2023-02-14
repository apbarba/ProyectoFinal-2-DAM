package com.salesianostriana.dam.imagineria_web.security.jwt.refresh;

import com.salesianostriana.dam.imagineria_web.model.Imaginero;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    @Value("${jwt.refresh.duration}")
    private int durationInMinutes;

    public Optional<RefreshToken> findByToken(String token) {

        return refreshTokenRepository.findByToken(token);
    }
        public RefreshToken createRefreshToken(Imaginero imaginero) {

            RefreshToken refreshToken = new RefreshToken();

            refreshToken.setImaginero(imaginero);
            refreshToken.setToken(UUID.randomUUID().toString());
            refreshToken.setExpiryDate(Instant.now().plusSeconds(durationInMinutes));

            refreshToken = refreshTokenRepository.save(refreshToken);

            return refreshToken;
        }

        public RefreshToken verify(RefreshToken refreshToken){

        if (refreshToken.getExpiryDate().compareTo(Instant.now()) < 0){

            refreshTokenRepository.delete(refreshToken);

            throw new RefreshTokenException("Sesi´n expirada: " + ". Please, login again");

        }

        return refreshToken;

    }

    @Transactional
    public int deleteByImaginero(Imaginero imaginero){

        return refreshTokenRepository.deleteByImaginero(imaginero);
    }
}
