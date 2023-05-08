package com.example.imagineria_web_android.ViewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.imagineria_web_android.API.AuthAPI;
import com.example.imagineria_web_android.Model.LoginRequest;
import com.example.imagineria_web_android.Model.LoginResponse;
import com.example.imagineria_web_android.Repository.AuthRepository;
import com.example.imagineria_web_android.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends ViewModel {

    private AuthRepository authRepository;
    private AuthAPI authAPI;

    public LoginViewModel() {
        authAPI = RetrofitInstance.getRetrofitInstance().create(AuthAPI.class);
    }

    public LiveData<LoginResponse> loginUser(String username, String password) {
        MutableLiveData<LoginResponse> loginResponseMutableLiveData = new MutableLiveData<>();
        authAPI.loginUser(new LoginRequest(username, password)).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    loginResponseMutableLiveData.setValue(response.body());
                } else {
                    Log.d("LoginViewModel", "Error en el inicio de sesión: " + response.errorBody());
                    loginResponseMutableLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.d("LoginViewModel", "Error en el inicio de sesión: " + t.getMessage());
                loginResponseMutableLiveData.setValue(null);
            }
        });

        return loginResponseMutableLiveData;
    }
}
