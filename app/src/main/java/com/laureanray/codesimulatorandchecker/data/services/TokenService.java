package com.laureanray.codesimulatorandchecker.data.services;

import com.laureanray.codesimulatorandchecker.data.model.Login;
import com.laureanray.codesimulatorandchecker.data.model.Token;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface TokenService {
    @Headers("Authorization: Basic QU5EUk9JRF9DTElFTlQ6U0VDUkVU")
    @POST("/oauth/token")
    @FormUrlEncoded
    Call<Token> getToken(@Field("username") String username,
                         @Field("password") String password,
                         @Field("grant_type") String grantType);
}
