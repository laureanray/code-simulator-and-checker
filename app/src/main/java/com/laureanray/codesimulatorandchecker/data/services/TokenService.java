package com.laureanray.codesimulatorandchecker.data.services;

import com.laureanray.codesimulatorandchecker.data.model.Login;
import com.laureanray.codesimulatorandchecker.data.model.Token;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface TokenService {
    @Headers("Authorization: Basic QU5EUk9JRF9DTElFTlQ6U0VDUkVU")
    @POST("/api/v1/oauth/token")
    Call<Token> getToken(@Body Login login);
}
