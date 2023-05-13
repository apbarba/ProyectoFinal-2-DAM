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
import com.example.imagineria_web_android.RetrofitInstance;

import java.util.List;

public class ImagineroViewModel extends AndroidViewModel {

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
}
