package com.example.imagineria_web_android.ViewModel;

import android.app.Application;
import android.net.Uri;

import androidx.lifecycle.AndroidViewModel;

import com.example.imagineria_web_android.API.UserApi;
import com.example.imagineria_web_android.Model.Auth.User;
import com.example.imagineria_web_android.RetrofitInstance;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileViewModel extends AndroidViewModel {
    private UserApi userService;

    public ProfileViewModel(Application application) {
        super(application);
        userService = RetrofitInstance.getRetrofitInstance(application.getApplicationContext()).create(UserApi.class);
    }

    public void changeAvatar(String userId, Uri imageUri) {
        File file = new File(imageUri.getPath());
        RequestBody requestBody = RequestBody.create(
                MediaType.parse(getApplication().getContentResolver().getType(imageUri)),
                file
        );
        MultipartBody.Part body = MultipartBody.Part.createFormData(
                "avatar",
                file.getName(),
                requestBody
        );

        userService.changeAvatar(userId, body).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                // Actualiza el usuario con los nuevos datos
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                // Manejar el error
            }
        });
    }
}


