package com.laureanray.codesimulatorandchecker.app;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.laureanray.codesimulatorandchecker.data.model.Student;

public class SharedPreferencesManager {
    private static final String APP_SETTINGS = "APP_SETTINGS";
    private static final Gson gson = new Gson();

    // properties
    private static final String IS_LOGGED_IN = "IS_LOGGED_IN";
    private static final String IS_STUDENT = "IS_STUDENT";
    private static final String STUDENT = "STUDENT";
    private static final String INSTRUCTOR = "INSTRUCTOR";
    private static final String TOKEN = "TOKEN";
    // other properties...


    private SharedPreferencesManager() {}

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(APP_SETTINGS, Context.MODE_PRIVATE);
    }


    public static boolean getIsLoggedInValue(Context context) {
        return getSharedPreferences(context).getBoolean(IS_LOGGED_IN , false);
    }

    public static void setIsLoggedInValue(Context context, boolean newValue) {
        final SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putBoolean(IS_LOGGED_IN , newValue);
        editor.commit();
    }

    public static Student getStudentValue(Context context) {
        String json = getSharedPreferences(context).getString(STUDENT, null);
        return gson.fromJson(json, Student.class);
    }

    public static void setStudentValue(Context context, Student student) {
        final SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(STUDENT, gson.toJson(student));
        editor.commit();
    }

    public static boolean getIsStudentValue(Context context) {
        return getSharedPreferences(context).getBoolean(IS_STUDENT, false);
    }

    public static void setIsStudentValue(Context context, boolean isStudent) {
        final SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putBoolean(IS_STUDENT, isStudent);
        editor.commit();
    }

    public static String getTokenValue(Context context) {
        return getSharedPreferences(context).getString(TOKEN, null);
    }

    public static void setTokenValue(Context context, String token) {
        final SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(TOKEN, token);
        editor.commit();
    }

}
