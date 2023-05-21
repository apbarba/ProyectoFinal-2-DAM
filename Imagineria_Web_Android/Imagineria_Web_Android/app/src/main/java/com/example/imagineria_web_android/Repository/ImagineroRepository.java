package com.example.imagineria_web_android.Repository;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.imagineria_web_android.API.ImagineroApi;
import com.example.imagineria_web_android.Model.Imagineros.Imaginero;
import com.example.imagineria_web_android.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ImagineroRepository {

    private ImagineroApi imagineroApi;

    public ImagineroRepository(Context context) {
        Retrofit retrofit = RetrofitInstance.getRetrofitInstance(context);
        imagineroApi = retrofit.create(ImagineroApi.class);
    }

    public Call<Imaginero> createImaginero(Imaginero imaginero) {
        return imagineroApi.createImaginero(imaginero);
    }

  /*  public Call<Imaginero> updateImaginero(int idImaginero, Imaginero imaginero) {
        return imagineroApi.updateImaginero(idImaginero, imaginero);
    }*/

  /*  public Call<Imaginero> getImaginero(int id) {
        return imagineroApi.getImaginero(id);
    }*/

}
