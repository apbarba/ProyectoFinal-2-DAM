package com.example.imagineria_web_android.API;

import com.example.imagineria_web_android.Model.Imagineros.ImagineroResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ImagineroApi {

    @GET("imaginero/")
    Call<ImagineroResponse> getImaginero();
}
