package com.example.imagineria_web_android.API;

import com.example.imagineria_web_android.Model.Auth.User;
import com.example.imagineria_web_android.Model.Obras.Obra;
import com.example.imagineria_web_android.Model.Obras.ObrasResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ObraApi {

    @GET("obras/")
    Call<ObrasResponse> getObras(@Query("page") int page);

    @POST("obras/")
    Call<Obra> createObra(@Body Obra obra);

    @GET("obras/{id}")
    Call<Obra> getObraById(@Path("id") String id);

    @PUT("obras/{id}")
    Call<Obra> updateObra(@Path("id") String id, @Body Obra obra);

    @DELETE("obras/{id}")
    Call<Void> deleteObra(@Path("id") String id);

    @POST("user/{userId}/favoritos/{obraId}")
    Call<User> addFavorito(@Path("userId") String userId, @Path("obraId") String obraId);

    @GET("user/{userId}/favoritos")
    Call<List<Obra>> getObrasFavoritas(@Path("userId") String userId);

    @DELETE("user/{userId}/favoritos/{obraId}")
    Call<Void> removeFavObra(@Path("userId") String userId, @Path("obraId") String obraId);

    @GET("/obras/search")
    Call<List<Obra>> getObrasByName(@Query("name") String name);


}
