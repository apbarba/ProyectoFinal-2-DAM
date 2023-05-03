package com.example.imagineria_web_android.Model;

import java.time.Instant;
import java.util.UUID;

public class JwtImagineroResponse {
    private UUID id;
    private String username;
    private String email;
    private String name;
    private Instant createdAt;
    private String token;
    private String refreshToken;

    public JwtImagineroResponse(UUID id, String username, String email, String name, Instant createdAt, String token, String refreshToken) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.name = name;
        this.createdAt = createdAt;
        this.token = token;
        this.refreshToken = refreshToken;
    }

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public String getToken() {
        return token;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public static JwtImagineroResponse of(User imaginero, String token, String refreshToken) {
        return new JwtImagineroResponse(imaginero.getId(), imaginero.getUsername(), imaginero.getEmail(), imaginero.getName(), imaginero.getCreatedAt(), token, refreshToken);
    }
}
