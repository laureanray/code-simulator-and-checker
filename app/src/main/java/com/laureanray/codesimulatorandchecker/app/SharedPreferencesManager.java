package com.laureanray.codesimulatorandchecker.app;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesManager {
    private static final String APP_SETTINGS = "APP_SETTINGS";


    // properties
    private static final String IS_LOGGED_IN = "IS_LOGGED_IN";
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
}
