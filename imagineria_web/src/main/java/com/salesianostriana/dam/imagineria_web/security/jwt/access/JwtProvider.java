package com.salesianostriana.dam.imagineria_web.security.jwt.access;

import com.salesianostriana.dam.imagineria_web.model.User;
import com.salesianostriana.dam.imagineria_web.security.errorHandling.JwtTokenException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

@Log
@Service
public class JwtProvider {

    public static final String TOKEN_TYPE = "JWT";

    public static final String TOKEN_HEADER = "Authorizacion";

    public static final String TOKEN_PREFIX = "Bearer";

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.duration}")
    private int jwtLifeInMinutes;

    private JwtParser jwtParser;
    private SecretKey secretKey;

    @PostConstruct
    public void init() {

        secretKey = Keys.hmacShaKeyFor(jwtSecret.getBytes());

        jwtParser = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build();
    }

    public String generateToken(Authentication authentication) {

        User imaginero = (User) authentication.getPrincipal();

        return generateToken(imaginero);
    }

    public String generateToken(User imaginero) {

        Date tokenExpirationDateTime =
                Date.from(
                        LocalDateTime
                                .now()
                                .plusMinutes(jwtLifeInMinutes)
                                .atZone(ZoneId.systemDefault())
                                .toInstant()
                );

        return Jwts.builder()
                .setHeaderParam("typ", TOKEN_TYPE)
                .setSubject(imaginero.getId().toString())
                .setIssuedAt(new Date())
                .setExpiration(tokenExpirationDateTime)
                .signWith(secretKey)
                .compact();
    }

    public UUID getImagineroIdFromJwtToken(String token){

        return UUID.fromString(
                jwtParser.parseClaimsJwt(token).getBody().getSubject()
        );

    }

    public boolean validateToken(String token){

        try{
            jwtParser.parseClaimsJwt(token);

            return true;
        }catch (SignatureException | MalformedJwtException | ExpiredJwtException | UnsupportedJwtException | IllegalArgumentException ex){

            log.info("Hay un error con el token: " + ex.getMessage());

            throw new JwtTokenException(ex.getMessage());
        }
    }


}
