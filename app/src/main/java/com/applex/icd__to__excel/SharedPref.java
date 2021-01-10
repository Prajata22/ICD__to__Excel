package com.applex.icd__to__excel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class SharedPref {

    private final SharedPreferences preferences;
    private final SharedPreferences.Editor editor;
    private final String ACCESS_TOKEN = "access_token";
    private final String LIST = "list";
    private final Gson gson;

    @SuppressLint("CommitPrefEdits")
    public SharedPref(Context context) {
        preferences = context.getSharedPreferences("com.applex.icd__to__excel", 0);
        editor = preferences.edit();
        gson = new Gson();
    }

    public void setAccessToken(String accessToken) {
        editor.putString(ACCESS_TOKEN, accessToken);
        editor.apply();
    }

    public String getAccessToken() { return preferences.getString(ACCESS_TOKEN, null); }

    public void setMonsterPlaylist(ArrayList<String> arrayList) {
        String playlist = gson.toJson(arrayList);
        editor.putString(LIST, playlist);
        editor.apply();
    }

    public ArrayList<String> getMonsterPlaylist() {
        String response = preferences.getString(LIST, null);
        return gson.fromJson(response, new TypeToken<ArrayList<String>>(){}.getType());
    }
}