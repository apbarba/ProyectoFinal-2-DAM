package com.example.imagineria_web_android.ViewModel;

import android.app.Application;
import android.net.Uri;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.imagineria_web_android.API.UserApi;
import com.example.imagineria_web_android.Model.Auth.ChangePasswordRequest;
import com.example.imagineria_web_android.Model.Auth.User;
import com.example.imagineria_web_android.RetrofitInstance;

import java.io.File;
import java.util.Optional;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

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

    public LiveData<Optional<User>> getUserProfile(String userId) {
        MutableLiveData<Optional<User>> userProfileLiveData = new MutableLiveData<>();

        if (userService != null) {
            userService.getUserProfile(userId).enqueue(new Callback<Optional<User>>() {
                @Override
                public void onResponse(Call<Optional<User>> call, Response<Optional<User>> response) {
                    if (response.isSuccessful()) {
                        Optional<User> userOptional = response.body();
                        userProfileLiveData.setValue(userOptional);
                    } else {
                        userProfileLiveData.setValue(Optional.empty());
                    }
                }

                @Override
                public void onFailure(Call<Optional<User>> call, Throwable t) {
                    userProfileLiveData.setValue(Optional.empty());
                }
            });
        } else {
            // Manejar el caso de userService nulo
            userProfileLiveData.setValue(Optional.empty());
        }

        return userProfileLiveData;
    }

    public LiveData<User> changePassword(String oldPassword, String newPassword, String verifyPassword) {
        MutableLiveData<User> userLiveData = new MutableLiveData<>();

        ChangePasswordRequest changePasswordRequest = new ChangePasswordRequest(oldPassword, newPassword, verifyPassword);
        userService.changePassword(changePasswordRequest).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    User user = response.body();
                    userLiveData.setValue(user);
                } else {
                    // La solicitud no fue exitosa
                    // Manejar el caso de error, por ejemplo, mostrar un mensaje de error al usuario
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                // Ocurrió un error en la comunicación con el servidor
                // Manejar el caso de error, por ejemplo, mostrar un mensaje de error al usuario
            }
        });

        return userLiveData;
    }

}


