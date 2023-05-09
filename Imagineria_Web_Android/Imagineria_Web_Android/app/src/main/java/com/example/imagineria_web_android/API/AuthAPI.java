package com.example.imagineria_web_android.API;

import com.example.imagineria_web_android.Model.LoginRequest;
import com.example.imagineria_web_android.Model.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthAPI {

    @POST("auth/login")
    Call<LoginResponse> loginUser(@Body LoginRequest loginRequest);
}
