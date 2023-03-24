package com.example.imagineria_web_android.Interface;

import com.example.imagineria_web_android.Model.User;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserApi {

    @POST("/auth/register")
    Call<ResponseBody> createUser(@Body User user);

    @POST("/auth/register/admin")
    Call<ResponseBody> createUserAdmin(@Body User user);

    @POST("auth/login")
    Call<ResponseBody> checkUser(@Body User user);
}
