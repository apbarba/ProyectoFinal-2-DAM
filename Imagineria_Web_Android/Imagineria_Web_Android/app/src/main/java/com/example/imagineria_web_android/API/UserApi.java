package com.example.imagineria_web_android.API;

import com.example.imagineria_web_android.Model.Auth.ChangePasswordRequest;
import com.example.imagineria_web_android.Model.Auth.User;

import java.util.Optional;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface UserApi {
        @Multipart
        @POST("user/{id}/changeAvatar")
        Call<Void> changeAvatar(@Path("id") String userId, @Part MultipartBody.Part file);

        @GET("user/{id}/profile")
        Call<Optional<User>> getUserProfile(@Path("id") String userId);


        @PUT("/user/changePassword")
        Call<User> changePassword(@Body ChangePasswordRequest changePasswordRequest);

}
