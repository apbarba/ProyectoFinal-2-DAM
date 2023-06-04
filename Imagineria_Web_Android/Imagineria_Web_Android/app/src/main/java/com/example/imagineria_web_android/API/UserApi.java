package com.example.imagineria_web_android.API;

import com.example.imagineria_web_android.Model.Auth.User;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface UserApi {
        @Multipart
        @PUT("user/{id}/avatar")
        Call<User> changeAvatar(@Path("id") String userId, @Part MultipartBody.Part avatar);


}
