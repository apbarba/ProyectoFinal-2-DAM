package com.example.imagineria_web_android.Interfaces;

import com.example.imagineria_web_android.Auth.LoginRequest;
import com.example.imagineria_web_android.Model.JwtImagineroResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {


    @POST("auth/login")
    Call<JwtImagineroResponse> login(@Body LoginRequest loginRequest);
}
