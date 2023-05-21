package com.example.imagineria_web_android.API;

import android.content.Context;

import com.example.imagineria_web_android.Model.Auth.LoginRequest;
import com.example.imagineria_web_android.Model.Auth.LoginResponse;
import com.example.imagineria_web_android.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthAPI {

    @POST("auth/login")
    Call<LoginResponse> loginUser(@Body LoginRequest loginRequest);

    class Factory {
        public static AuthAPI create(Context context) {
            Retrofit retrofit = RetrofitInstance.getRetrofitInstance(context);
            return retrofit.create(AuthAPI.class);
        }
    }
}
