package com.example.imagineria_web_android.API;

import com.example.imagineria_web_android.Model.Imagineros.Imaginero;
import com.example.imagineria_web_android.Model.Imagineros.ImagineroResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ImagineroApi {

    @GET("imaginero/")
    Call<ImagineroResponse> getImaginero();

    @POST("imaginero/")
    Call<Imaginero> createImaginero(@Body Imaginero imaginero);
}
