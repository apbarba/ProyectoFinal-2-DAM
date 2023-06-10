package com.example.imagineria_web_android.Model.Auth;

public class RegisterRequest {

        private String username;
        private String email;
        private String password;
        private String name;
        private String verifyPassword;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVerifyPassword() {
        return verifyPassword;
    }

    public void setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
    }

    public RegisterRequest(String username, String email, String password, String name, String verifyPassword) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.name = name;
        this.verifyPassword = verifyPassword;
    }
}
