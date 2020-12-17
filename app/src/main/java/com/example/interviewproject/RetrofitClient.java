package com.example.interviewproject;

import android.util.Log;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import static okhttp3.logging.HttpLoggingInterceptor.Level.BODY;

public class RetrofitClient {

    public static ApiClient buildHTTPClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(getClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(ApiClient.class);

    }

    //Create OKHttp Client used by retrofit
    private static OkHttpClient getClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(provideHttpLoggingInterceptor())
                .build();
    }


    //Attach logging intercept to print Logs in LogCat

    private static HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor =
                new HttpLoggingInterceptor((new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(String message) {
                        Log.d("HTTP", message);
                    }
                }));
        httpLoggingInterceptor.setLevel(BODY);
        return httpLoggingInterceptor;

    }
}
