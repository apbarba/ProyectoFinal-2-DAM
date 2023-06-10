package com.example.imagineria_web_android.Model.Auth;

import com.google.gson.annotations.SerializedName;

public class AvatarChangeResponse {

    @SerializedName("avatarFilename")
    private String avatarFilename;

    public String getAvatarFilename() {
        return avatarFilename;
    }

    public void setAvatarFilename(String avatarFilename) {
        this.avatarFilename = avatarFilename;
    }

    public AvatarChangeResponse(String avatarFilename) {
        this.avatarFilename = avatarFilename;
    }
}
