package com.example.imagineria_web_android.ViewModel;

import android.app.Application;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.imagineria_web_android.API.ImagineroApi;
import com.example.imagineria_web_android.Model.Imagineros.Imaginero;
import com.example.imagineria_web_android.Model.Imagineros.ImagineroResponse;
import com.example.imagineria_web_android.Repository.ImagineroRepository;
import com.example.imagineria_web_android.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

public class ImagineroViewModel extends AndroidViewModel {

    private ImagineroRepository repository;
    private MutableLiveData<List<Imaginero>> imaginero;

    public ImagineroViewModel(@NonNull Application application) {
        super(application);
        imaginero = new MutableLiveData<>();
    }

    public LiveData<List<Imaginero>> getImaginero(){
        return imaginero;
    }

    public void loadImaginero(){
        ImagineroApi apiInterface = RetrofitInstance.getRetrofitInstance(getApplication().getApplicationContext()).create(ImagineroApi.class);
        Call<ImagineroResponse> call = apiInterface.getImaginero();
        call.enqueue(new Callback<ImagineroResponse>() {
            @Override
            public void onResponse(Call<ImagineroResponse> call, Response<ImagineroResponse> response) {
                ImagineroResponse imagineroResponse = response.body();
                if (imagineroResponse != null && imagineroResponse.getContent() != null){
                    imaginero.postValue(imagineroResponse.getContent());
                }
            }

            @Override
            public void onFailure(Call<ImagineroResponse> call, Throwable t) {
                //Los mensajes o manejo de errores aqui
            }
        });
    }

    public void createImaginero(Imaginero newImaginero) {
        ImagineroApi apiInterface = RetrofitInstance.getRetrofitInstance(getApplication().getApplicationContext()).create(ImagineroApi.class);
        Call<Imaginero> call = apiInterface.createImaginero(newImaginero);
        call.enqueue(new Callback<Imaginero>() {
            @Override
            public void onResponse(Call<Imaginero> call, Response<Imaginero> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Imaginero> currentImagineros = imaginero.getValue();
                    if (currentImagineros == null) {
                        currentImagineros = new ArrayList<>();
                    }
                    currentImagineros.add(response.body());
                    imaginero.postValue(currentImagineros);
                }
            }

            @Override
            public void onFailure(Call<Imaginero> call, Throwable t) {
                //Los mensajes o manejo de errores aqui
            }
        });
    }
}
