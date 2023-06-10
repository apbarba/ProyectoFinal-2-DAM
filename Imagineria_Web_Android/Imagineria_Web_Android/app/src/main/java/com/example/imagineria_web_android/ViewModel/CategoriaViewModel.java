package com.example.imagineria_web_android.ViewModel;

import android.app.Application;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

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

    private MutableLiveData<List<Categoria>> categoriaList;
    private MutableLiveData<Categoria> categoria;

    public CategoriaViewModel(@NonNull Application application) {
        super(application);
        categoriaList = new MutableLiveData<>();
        categoria = new MutableLiveData<>();
    }
    public LiveData<Categoria> getCategoria(){
        return categoria;
    }

    public LiveData<List<Categoria>> getCategoriaList(){
        return categoriaList;
    }

    public void loadCategoria(){
        CategoriaApi apiInterface = RetrofitInstance.getRetrofitInstance(getApplication().getApplicationContext()).create(CategoriaApi.class);
        Call<CategoriaResponse> call = apiInterface.getCategoria();
        call.enqueue(new Callback<CategoriaResponse>() {
            @Override
            public void onResponse(Call<CategoriaResponse> call, Response<CategoriaResponse> response) {
                CategoriaResponse categoriaResponse = response.body();
                if (categoriaResponse != null && categoriaResponse.getContent() != null){
                    categoriaList.postValue(categoriaResponse.getContent());
                }
            }

            @Override
            public void onFailure(Call<CategoriaResponse> call, Throwable t) {
                //Mensajes de error
            }
        });
    }

    public void loadCategoriaByid(String id){
        CategoriaApi apiInterface = RetrofitInstance.getRetrofitInstance(getApplication().getApplicationContext()).create(CategoriaApi.class);
        Call<Categoria> call = apiInterface.getCategoriaById(id);
        call.enqueue(new Callback<Categoria>() {
            @Override
            public void onResponse(Call<Categoria> call, Response<Categoria> response) {
                if (response.isSuccessful() && response.body() != null){
                    categoria.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Categoria> call, Throwable t) {

            }
        });
    }

    public void updateCategoria(String id, Categoria updateCategoria){
        CategoriaApi apiInterface = RetrofitInstance.getRetrofitInstance(getApplication().getApplicationContext()).create(CategoriaApi.class);
        Call<Categoria> call = apiInterface.updateCategoria(id, updateCategoria);
        call.enqueue(new Callback<Categoria>() {
            @Override
            public void onResponse(Call<Categoria> call, Response<Categoria> response) {
                if (response.isSuccessful() && response.body() != null){
                    categoria.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Categoria> call, Throwable t) {

            }
        });
    }
    public void deleteCategoria(String id){
        CategoriaApi apiInterface = RetrofitInstance.getRetrofitInstance(getApplication().getApplicationContext()).create(CategoriaApi.class);
        Call<Void> call = apiInterface.deleteCategoria(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()){

                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }
}
