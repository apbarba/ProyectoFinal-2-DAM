package com.example.imagineria_web_android.API;

import com.example.imagineria_web_android.Model.Obras.Obra;
import com.example.imagineria_web_android.Model.Obras.ObrasResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ObraApi {

    @GET("obras/")
    Call<ObrasResponse> getObras();

    @POST("obras/")
    Call<Obra> createObra(@Body Obra obra);

    @GET("obras/{id}")
    Call<Obra> getObraById(@Path("id") String id);

    @PUT("obras/{id}")
    Call<Obra> updateObra(@Path("id") String id, @Body Obra obra);

    @DELETE("obras/{id}")
    Call<Void> deleteObra(@Path("id") String id);

}
