package com.example.imagineria_web_android;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    private static Retrofit retrofit;
    private static OkHttpClient okHttpClient;
    public static final String BASE_URL = "http://10.0.2.2:8080/";
    public static Retrofit getRetrofitInstance(Context context) {
        if (retrofit == null) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .addInterceptor(chain -> {
                        Request original = chain.request();
                        // Obtenemos el token de las SharedPreferences para cada solicitud
                        SharedPreferences sharedPreferences = context.getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
                        String token = sharedPreferences.getString("token", "");
                        Log.d("RetrofitInstance", "token: " + token);

                        Request request = original.newBuilder()
                                .header("Authorization", "Bearer " + token)
                                .method(original.method(), original.body())
                                .build();

                        return chain.proceed(request);
                    })
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static OkHttpClient getOkHttpClient(Context context) {
        if (okHttpClient == null) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .addInterceptor(chain -> {
                        Request original = chain.request();
                        SharedPreferences sharedPreferences = context.getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
                        String token = sharedPreferences.getString("token", "");
                        Log.d("RetrofitInstance", "token: " + token);

                        Request request = original.newBuilder()
                                .header("Authorization", "Bearer " + token)
                                .method(original.method(), original.body())
                                .build();

                        return chain.proceed(request);
                    })
                    .build();
        }
        return okHttpClient;
    }
}
