package com.example.myfirstapp;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesUtils {

    public static String KEY = "SESSION";
    public static void saveEmail(String email, Context context) {
        SharedPreferences.Editor editor = context
                .getSharedPreferences(KEY, Activity.MODE_PRIVATE).edit();
        editor.putString("email", email);
        editor.commit();
    }

    public static String getEmail(Context context) {
        SharedPreferences savedSession = context.getSharedPreferences(KEY,
                Activity.MODE_PRIVATE);
        return ((SharedPreferences) savedSession).getString("email", "");
    }
}
