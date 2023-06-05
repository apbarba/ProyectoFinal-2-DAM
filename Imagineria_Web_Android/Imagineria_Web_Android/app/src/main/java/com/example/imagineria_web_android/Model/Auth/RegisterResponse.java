package com.example.imagineria_web_android.Model.Auth;

public class RegisterResponse {

    public String id;
    public String username;
    public String email;
    public String name;
    public String avatar;
    public String createdAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public RegisterResponse(String id, String username, String email, String name, String avatar, String createdAt) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.name = name;
        this.avatar = avatar;
        this.createdAt = createdAt;
    }
}

/*{
    "id": "ac13d001-888c-1c39-8188-8cbedb6c0001",
    "username": "apbarba",
    "email": "pilarbarba03@gmail.com",
    "name": "Ana Pilar",
    "avatar": null,
    "createdAt": "05/06/2023 20:08:18"
}*/