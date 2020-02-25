package com.laureanray.codesimulatorandchecker.data.services;

import android.content.Context;

import com.laureanray.codesimulatorandchecker.app.OkHttpClientInstance;
import com.laureanray.codesimulatorandchecker.app.RetrofitAuthClientInstance;
import com.laureanray.codesimulatorandchecker.app.SharedPreferencesManager;

public class StudentServiceInstance {
    private static StudentService studentService;

    public static StudentService getStudentService(Context context) {
        if(studentService == null) {
            studentService = RetrofitAuthClientInstance.getRetrofitInstance(
                    OkHttpClientInstance.getOkHttpClientInstance(SharedPreferencesManager.getTokenValue(context))
            ).create(StudentService.class);
        }
        return studentService;
    }
}
