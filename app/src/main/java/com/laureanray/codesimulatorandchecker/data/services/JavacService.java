package com.laureanray.codesimulatorandchecker.data.services;

import com.laureanray.codesimulatorandchecker.data.model.CompileTask;
import com.laureanray.codesimulatorandchecker.data.model.Login;
import com.laureanray.codesimulatorandchecker.data.model.Student;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface JavacService {
    @POST("/api/v1/javac/compile")
    Call<CompileTask> compile(@Body CompileTask compileTask);
}
