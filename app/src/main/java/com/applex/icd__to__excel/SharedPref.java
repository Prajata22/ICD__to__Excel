package com.applex.icd__to__excel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref {

    private final SharedPreferences preferences;
    private final SharedPreferences.Editor editor;
    private final String ACCESS_TOKEN = "access_token";

    @SuppressLint("CommitPrefEdits")
    public SharedPref(Context context) {
        preferences = context.getSharedPreferences("com.applex.icd__to__excel", 0);
        editor = preferences.edit();
    }

    public void setAccessToken(String accessToken) {
        editor.putString(ACCESS_TOKEN, accessToken);
        editor.apply();
    }

    public String getAccessToken() { return preferences.getString(ACCESS_TOKEN, null); }
}