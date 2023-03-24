package com.example.imagineria_web_android.Retrofit;

import com.example.imagineria_web_android.Interface.UserApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static  final String BASE_URL = " http://localhost:8080/";
    private static RetrofitClient mInstance;
    private Retrofit retrofit;

    private RetrofitClient () {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    public static synchronized RetrofitClient getInstance() {
        if (mInstance == null) {
            mInstance = new RetrofitClient();
        }
        return mInstance;
    }


    public UserApi getAPI () {
        return retrofit.create(UserApi.class);
    }
}
