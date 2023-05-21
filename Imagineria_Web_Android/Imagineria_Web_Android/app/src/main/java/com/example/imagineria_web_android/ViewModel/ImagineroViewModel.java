package com.example.imagineria_web_android.ViewModel;

import android.app.Application;

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
    private MutableLiveData<List<Imaginero>> imagineroList;

    private MutableLiveData<Imaginero> imaginero;

    public ImagineroViewModel(@NonNull Application application) {
        super(application);
        imagineroList = new MutableLiveData<>();
        imaginero = new MutableLiveData<>();
    }

    public LiveData<List<Imaginero>> getImagineroList(){
        return imagineroList;
    }

    public LiveData<Imaginero> getImaginero() {
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
                    imagineroList.postValue(imagineroResponse.getContent());
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
                    List<Imaginero> currentImagineros = imagineroList.getValue();
                    if (currentImagineros == null) {
                        currentImagineros = new ArrayList<>();
                    }
                    currentImagineros.add(response.body());
                    imagineroList.postValue(currentImagineros);
                }
            }

            @Override
            public void onFailure(Call<Imaginero> call, Throwable t) {
                //Los mensajes o manejo de errores aqui
            }
        });
    }

    public void loadImagineroById(String id){
        ImagineroApi apiInterface = RetrofitInstance.getRetrofitInstance(getApplication().getApplicationContext()).create(ImagineroApi.class);
        Call<Imaginero> call = apiInterface.getImagineroById(id);
        call.enqueue(new Callback<Imaginero>() {
            @Override
            public void onResponse(Call<Imaginero> call, Response<Imaginero> response) {
                if (response.isSuccessful() && response.body() != null){
                    imaginero.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Imaginero> call, Throwable t) {
                //Los mensajes o manejo de errores aqui
            }
        });
    }
    public void updateImaginero(String id, Imaginero updatedImaginero) {
        ImagineroApi apiInterface = RetrofitInstance.getRetrofitInstance(getApplication().getApplicationContext()).create(ImagineroApi.class);
        Call<Imaginero> call = apiInterface.updateImaginero(id, updatedImaginero);
        call.enqueue(new Callback<Imaginero>() {
            @Override
            public void onResponse(Call<Imaginero> call, Response<Imaginero> response) {
                if (response.isSuccessful() && response.body() != null) {
                    imaginero.postValue(response.body());
                    //Aquí también puedes actualizar la lista de Imagineros, si es necesario.
                }
            }

            @Override
            public void onFailure(Call<Imaginero> call, Throwable t) {
                //Los mensajes o manejo de errores aqui
            }
        });
    }

    public void deleteImaginero(String id) {
        ImagineroApi apiInterface = RetrofitInstance.getRetrofitInstance(getApplication().getApplicationContext()).create(ImagineroApi.class);
        Call<Void> call = apiInterface.deleteImaginero(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    //Aquí puedes hacer algo cuando la eliminación fue exitosa, por ejemplo, actualizar la lista de Imagineros.
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                //Los mensajes o manejo de errores aquí.
            }
        });
    }

}
