package com.example.imagineria_web_android.API;

import com.example.imagineria_web_android.Model.Obras.ObrasResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ObraApi {

    @GET("obras/")
    Call<ObrasResponse> getObras();
}
