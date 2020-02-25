package com.laureanray.codesimulatorandchecker.app;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitAuthClientInstance {
    private static Retrofit retrofit;
    // TODO: Make this reusable
    private static final String BASE_URL = "http://192.168.100.47:3001";

    public static Retrofit getRetrofitInstance(OkHttpClient client) {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}
