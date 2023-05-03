package com.example.imagineria_web_android.Model;

import java.time.Instant;
import java.util.UUID;

public class User {

    private UUID id;
    private String username;
    private String email;
    private String name;
    private Instant createdAt;

    public User(UUID id, String username, String email, String name, Instant createdAt) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.name = name;
        this.createdAt = createdAt;
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
}
