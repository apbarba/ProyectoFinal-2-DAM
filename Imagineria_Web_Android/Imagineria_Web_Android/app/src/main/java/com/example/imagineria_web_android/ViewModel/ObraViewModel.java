package com.example.imagineria_web_android.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.navigation.PopUpToBuilder;

import com.example.imagineria_web_android.API.CategoriaApi;
import com.example.imagineria_web_android.API.ObraApi;
import com.example.imagineria_web_android.Model.Categoria.Categoria;
import com.example.imagineria_web_android.Model.Categoria.CategoriaResponse;
import com.example.imagineria_web_android.Model.Obras.Obra;
import com.example.imagineria_web_android.Model.Obras.ObrasResponse;
import com.example.imagineria_web_android.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ObraViewModel extends AndroidViewModel {

    private MutableLiveData<List<Obra>> obrasList;

    private MutableLiveData<List<Categoria>> categoriasList;
    private MutableLiveData <Obra> obra;

    public ObraViewModel(@NonNull Application application) {
        super(application);
        obrasList = new MutableLiveData<>();
        obra = new MutableLiveData<>();
        categoriasList = new MutableLiveData<>();
    }

    public LiveData<List<Obra>> getObrasList() {
        return obrasList;
    }

    public LiveData<Obra> getObra(){
        return obra;
    }

    public MutableLiveData<List<Categoria>> getCategoriasList() {
        return categoriasList;
    }

    public void loadObras() {
        ObraApi apiInterface = RetrofitInstance.getRetrofitInstance(getApplication().getApplicationContext()).create(ObraApi.class);
        Call<ObrasResponse> call = apiInterface.getObras();
        call.enqueue(new Callback<ObrasResponse>() {
            @Override
            public void onResponse(Call<ObrasResponse> call, Response<ObrasResponse> response) {
                ObrasResponse obraResponse = response.body();
                if (obraResponse != null && obraResponse.getContent() != null) {
                    obrasList.postValue(obraResponse.getContent());
                }
            }

            @Override
            public void onFailure(Call<ObrasResponse> call, Throwable t) {
                // Manejo de errores
            }
        });
    }

    public void createObra(Obra newObra){
        ObraApi apiInterface = RetrofitInstance.getRetrofitInstance(getApplication().getApplicationContext()).create(ObraApi.class);
        Call<Obra> call = apiInterface.createObra(newObra);
        call.enqueue(new Callback<Obra>() {
            @Override
            public void onResponse(Call<Obra> call, Response<Obra> response) {
                if (response.isSuccessful() && response.body() != null){
                    List<Obra> currentObras = obrasList.getValue();
                    if (currentObras == null){
                        currentObras = new ArrayList<>();
                    }
                    currentObras.add(response.body());
                    obrasList.postValue(currentObras);
                }
            }

            @Override
            public void onFailure(Call<Obra> call, Throwable t) {
                // Manejar el error
            }
        });
    }


    public void loadImagineroById(String id){
        ObraApi apiInterface = RetrofitInstance.getRetrofitInstance(getApplication().getApplicationContext()).create(ObraApi.class);
        Call<Obra> call = apiInterface.getObraById(id);
        call.enqueue(new Callback<Obra>() {
            @Override
            public void onResponse(Call<Obra> call, Response<Obra> response) {
                if (response.isSuccessful() && response.body() != null){
                    obra.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Obra> call, Throwable t) {
                //MENSAJES DE ERRORES O EL MANEJO
            }
        });
    }

    public void updateObra(String id, Obra updateObra){
        ObraApi apiInterface = RetrofitInstance.getRetrofitInstance(getApplication().getApplicationContext()).create(ObraApi.class);
        Call<Obra> call = apiInterface.updateObra(id, updateObra);
        call.enqueue(new Callback<Obra>() {
            @Override
            public void onResponse(Call<Obra> call, Response<Obra> response) {
                if (response.isSuccessful() && response.body() != null){
                    obra.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Obra> call, Throwable t) {

            }
        });
    }

    public void deleteObra(String id){
        ObraApi apiInterface = RetrofitInstance.getRetrofitInstance(getApplication().getApplicationContext()).create(ObraApi.class);
        Call<Void> call = apiInterface.deleteObra(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()){
                    //Aquí puedes hacer algo cuando la eliminación fue exitosa, por ejemplo, actualizar la lista de Imagineros.
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                //Manejo de errores o solamente mensajes
            }
        });
    }

    public void getCategorias() {
        CategoriaApi apiInterface = RetrofitInstance.getRetrofitInstance(getApplication().getApplicationContext()).create(CategoriaApi.class);
        Call<CategoriaResponse> call = apiInterface.getCategoria();
        call.enqueue(new Callback<CategoriaResponse>() {
            @Override
            public void onResponse(Call<CategoriaResponse> call, Response<CategoriaResponse> response) {
                if (response.isSuccessful() && response.body() != null){
                    categoriasList.postValue(response.body().getContent());
                }
            }

            @Override
            public void onFailure(Call<CategoriaResponse> call, Throwable t) {
                // Manejo de errores
            }
        });
    }
}
