package com.example.imagineria_web_android.Model.Auth;

public class LoginResponse {

    private String id;
    private String username;
    private String email;
    private String name;
    private String createdAt;
    private String token;
    private String refreshToken;

    public String getId() {
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

    public String getCreatedAt() {
        return createdAt;
    }

    public String getToken() {
        return token;
    }

    public String getRefreshToken() {
        return refreshToken;
    }
}
