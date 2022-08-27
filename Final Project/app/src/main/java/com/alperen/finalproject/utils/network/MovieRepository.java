package com.alperen.finalproject.utils.network;


import com.alperen.finalproject.utils.Constants;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Alperen on 26.08.2022.
 */
public class MovieRepository {
    private static Retrofit instance = null;

    public static Retrofit getInstance() {
        if (instance == null) {

            // Add language query in url
            OkHttpClient client = buildClient();

            instance = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            return instance;
        }
        return instance;
    }

    private static OkHttpClient buildClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    Request request = chain.request();
                    HttpUrl url = request.url().newBuilder().addQueryParameter("language","tr-TR").build();
                    request = request.newBuilder().url(url).build();
                    return chain.proceed(request);
                })
                .build();
    }
}
