package com.example.imagineria_web_android.API;

import com.example.imagineria_web_android.Model.Auth.ChangePasswordRequest;
import com.example.imagineria_web_android.Model.Auth.User;

import java.util.Optional;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface UserApi {
        @Multipart
        @PUT("user/{id}/avatar")
        Call<User> changeAvatar(@Path("id") String userId, @Part MultipartBody.Part avatar);

        @GET("user/{id}/profile")
        Call<Optional<User>> getUserProfile(@Path("id") String userId);


        @PUT("/user/changePassword")
        Call<User> changePassword(@Body ChangePasswordRequest changePasswordRequest);

}
