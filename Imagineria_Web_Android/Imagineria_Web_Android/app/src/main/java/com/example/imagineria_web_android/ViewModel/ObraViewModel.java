package com.example.imagineria_web_android.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.imagineria_web_android.API.ObraApi;
import com.example.imagineria_web_android.Model.Obras.Obra;
import com.example.imagineria_web_android.Model.Obras.ObrasResponse;
import com.example.imagineria_web_android.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ObraViewModel extends AndroidViewModel {

    private MutableLiveData<List<Obra>> obras;

    public ObraViewModel(@NonNull Application application) {
        super(application);
        obras = new MutableLiveData<>();
    }

    public LiveData<List<Obra>> getObras() {
        return obras;
    }

    public void loadObras() {
        ObraApi apiInterface = RetrofitInstance.getRetrofitInstance(getApplication().getApplicationContext()).create(ObraApi.class);
        Call<ObrasResponse> call = apiInterface.getObras();
        call.enqueue(new Callback<ObrasResponse>() {
            @Override
            public void onResponse(Call<ObrasResponse> call, Response<ObrasResponse> response) {
                ObrasResponse obraResponse = response.body();
                if (obraResponse != null && obraResponse.getContent() != null) {
                    obras.postValue(obraResponse.getContent());
                }
            }

            @Override
            public void onFailure(Call<ObrasResponse> call, Throwable t) {
                // Manejo de errores
            }
        });
    }
}
