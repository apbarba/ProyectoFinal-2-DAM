package com.example.imagineria_web_android.API;

import com.example.imagineria_web_android.Model.Imagineros.Imaginero;
import com.example.imagineria_web_android.Model.Imagineros.ImagineroResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ImagineroApi {

    @GET("imaginero/")
    Call<ImagineroResponse> getImaginero();

    @POST("imaginero/")
    Call<Imaginero> createImaginero(@Body Imaginero imaginero);

    @GET("imaginero/{id}")
    Call<Imaginero> getImaginero(@Path("id") int idImaginero);

    @PUT("imaginero/{id}")
    Call<Imaginero> updateImaginero(@Path("id") int idImaginero, @Body Imaginero imaginero);
}
