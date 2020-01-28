package com.laureanray.codesimulatorandchecker.data.services;

import com.laureanray.codesimulatorandchecker.data.model.Login;
import com.laureanray.codesimulatorandchecker.data.model.Student;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface StudentService {
    @GET("/api/v1/students/find/{username}")
    Call<Student> getUserByUsername(@Path("username") String user);

    @POST("/api/v1/students/login")
    Call<Student> login(@Body Login login);

    @POST("/api/v1/students/register")
    Call<Student> register(@Body Student student);
}
