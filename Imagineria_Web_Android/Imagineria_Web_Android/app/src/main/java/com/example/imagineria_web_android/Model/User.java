package com.example.imagineria_web_android.Model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class User {

    private String name;
    private String password;
    private String email;
    private String username;
    private String verifyPassword;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getVerifyPassword() {
        return verifyPassword;
    }

    public void setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
    }

    public User(String name, String password, String email, String username, String verifyPassword) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.username = username;
        this.verifyPassword = verifyPassword;
    }
}
