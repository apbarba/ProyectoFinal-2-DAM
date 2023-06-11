package com.example.imagineria_web_android.API;

import com.example.imagineria_web_android.Model.Imagineros.Imaginero;
import com.example.imagineria_web_android.Model.Imagineros.ImagineroResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ImagineroApi {

    @GET("imaginero/")
    Call<ImagineroResponse> getImaginero(@Query("page") int page);

    @POST("imaginero/")
    Call<Imaginero> createImaginero(@Body Imaginero imaginero);

    @GET("imaginero/{id}")
    Call<Imaginero> getImagineroById(@Path("id") String id);

    @PUT("imaginero/{id}")
    Call<Imaginero> updateImaginero(@Path("id") String id, @Body Imaginero imaginero);

    @DELETE("imaginero/{id}")
    Call<Void> deleteImaginero(@Path("id") String id);
}
