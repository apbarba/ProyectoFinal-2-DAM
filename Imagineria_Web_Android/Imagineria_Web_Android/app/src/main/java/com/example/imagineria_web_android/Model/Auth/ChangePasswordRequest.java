package com.example.imagineria_web_android.Model.Auth;

public class ChangePasswordRequest {
    private String oldPassword;
    private String newPassword;
    private String verifyNewPassword;

    public ChangePasswordRequest(String oldPassword, String newPassword, String verifyNewPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
        this.verifyNewPassword = verifyNewPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public String getVerifyNewPassword() {
        return verifyNewPassword;
    }
}

