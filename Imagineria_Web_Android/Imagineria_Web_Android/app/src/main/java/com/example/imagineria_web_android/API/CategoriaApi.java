package com.example.imagineria_web_android.API;

import com.example.imagineria_web_android.Model.Categoria.CategoriaResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CategoriaApi {

    @GET("categoria/")
    Call<CategoriaResponse> getCategoria();
}
