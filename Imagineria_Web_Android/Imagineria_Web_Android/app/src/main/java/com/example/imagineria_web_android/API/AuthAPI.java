package com.example.imagineria_web_android.API;

import com.example.imagineria_web_android.Model.Auth.LoginRequest;
import com.example.imagineria_web_android.Model.Auth.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthAPI {

    @POST("auth/login")
    Call<LoginResponse> loginUser(@Body LoginRequest loginRequest);
}
