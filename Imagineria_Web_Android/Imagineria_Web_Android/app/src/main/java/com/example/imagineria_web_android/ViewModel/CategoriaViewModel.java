package com.example.imagineria_web_android.ViewModel;

import android.app.Application;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.imagineria_web_android.API.CategoriaApi;
import com.example.imagineria_web_android.Model.Categoria.Categoria;
import com.example.imagineria_web_android.Model.Categoria.CategoriaResponse;
import com.example.imagineria_web_android.RetrofitInstance;

import java.util.List;

public class CategoriaViewModel extends AndroidViewModel {

    private MutableLiveData<List<Categoria>> categoria;

    public CategoriaViewModel(@NonNull Application application) {
        super(application);
        categoria = new MutableLiveData<>();
    }

    public LiveData<List<Categoria>> getCategoria(){
        return categoria;
    }

    public void loadCategoria(){
        CategoriaApi apiInterface = RetrofitInstance.getRetrofitInstance(getApplication().getApplicationContext()).create(CategoriaApi.class);
        Call<CategoriaResponse> call = apiInterface.getCategoria();
        call.enqueue(new Callback<CategoriaResponse>() {
            @Override
            public void onResponse(Call<CategoriaResponse> call, Response<CategoriaResponse> response) {
                CategoriaResponse categoriaResponse = response.body();
                if (categoriaResponse != null && categoriaResponse.getContent() != null){
                    categoria.postValue(categoriaResponse.getContent());
                }
            }

            @Override
            public void onFailure(Call<CategoriaResponse> call, Throwable t) {
                //Mensajes de error
            }
        });
    }
}
