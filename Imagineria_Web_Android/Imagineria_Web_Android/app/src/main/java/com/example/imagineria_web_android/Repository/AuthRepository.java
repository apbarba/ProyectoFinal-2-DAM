package com.example.imagineria_web_android.Repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.imagineria_web_android.API.AuthAPI;
import com.example.imagineria_web_android.Model.LoginRequest;
import com.example.imagineria_web_android.Model.LoginResponse;
import com.example.imagineria_web_android.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthRepository {

    private AuthAPI authAPI;

    public AuthRepository() {
        authAPI = RetrofitInstance.getRetrofitInstance().create(AuthAPI.class);
    }

    public LiveData<LoginResponse> loginUser(LoginRequest loginRequest) {
        MutableLiveData<LoginResponse> loginResponse = new MutableLiveData<>();

        authAPI.loginUser(loginRequest).enqueue(new Callback<LoginResponse>() {

            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                loginResponse.setValue(response.body());
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                loginResponse.setValue(null);
            }
        });

        return loginResponse;
    }
}
