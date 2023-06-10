package com.example.imagineria_web_android.API;

import com.example.imagineria_web_android.Model.Categoria.Categoria;
import com.example.imagineria_web_android.Model.Categoria.CategoriaResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CategoriaApi {

    @GET("categoria/")
    Call<CategoriaResponse> getCategoria();

    @GET("categoria/{id}")
    Call<Categoria> getCategoriaById(@Path("id") String id);

    @PUT("categoria/{id}")
    Call<Categoria> updateCategoria(@Path("id") String id, @Body Categoria categoria);

    @DELETE("categoria/{id}")
    Call<Void> deleteCategoria(@Path("id") String id);
}
