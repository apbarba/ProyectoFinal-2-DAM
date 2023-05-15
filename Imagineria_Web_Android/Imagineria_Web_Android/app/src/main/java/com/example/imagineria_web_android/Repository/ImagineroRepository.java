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

public class ImagineroRepository {

    private final ImagineroApi apiService;
    private final MutableLiveData<List<Imaginero>> imagineros = new MutableLiveData<>();
    private final MutableLiveData<Imaginero> createdImaginero = new MutableLiveData<>();

    public ImagineroRepository(Context context) {
        apiService = RetrofitInstance.getRetrofitInstance(context).create(ImagineroApi.class);
    }

    public LiveData<List<Imaginero>> getImagineros() {
        return imagineros;
    }

    public LiveData<Imaginero> getCreatedImaginero() {
        return createdImaginero;
    }

   /* public void loadImagineros() {
        apiService.getImagineros().enqueue(new Callback<List<Imaginero>>() {
            @Override
            public void onResponse(Call<List<Imaginero>> call, Response<List<Imaginero>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    imagineros.postValue(response.body());
                } else {
                    // manejar error
                }
            }

            @Override
            public void onFailure(Call<List<Imaginero>> call, Throwable t) {
                // manejar error
            }
        });
    }*/

    public void createImaginero(Imaginero newImaginero) {
        apiService.createImaginero(newImaginero).enqueue(new Callback<Imaginero>() {
            @Override
            public void onResponse(Call<Imaginero> call, Response<Imaginero> response) {
                if (response.isSuccessful() && response.body() != null) {
                    createdImaginero.postValue(response.body());
                } else {
                    // manejar error
                }
            }

            @Override
            public void onFailure(Call<Imaginero> call, Throwable t) {
                // manejar error
            }
        });
    }
}
