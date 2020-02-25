package com.laureanray.codesimulatorandchecker.app;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OkHttpClientInstance {
    private static OkHttpClient client;
    private static final String BASE_URL = "http://192.168.100.47:3001";
    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    public static OkHttpClient getOkHttpClientInstance(String token) {
        if (client == null) {
            httpClient.addInterceptor(chain -> {
                Request original = chain.request();

                Request request = original.newBuilder()
                        .header("Authorization", "Bearer ".concat(token))
                        .method(original.method(), original.body())
                        .build();

                return chain.proceed(request);
            });

            client = httpClient.build();
        }

        return client;
    }
}
