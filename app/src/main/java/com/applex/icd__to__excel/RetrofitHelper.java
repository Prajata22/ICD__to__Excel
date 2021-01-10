package com.applex.icd__to__excel;

import android.annotation.SuppressLint;
import android.content.Context;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {

    @SuppressLint("StaticFieldLeak")
    private static RetrofitHelper instance = null;
    private final ICDInterface icdInterface;

    private RetrofitHelper(Context context) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder().addInterceptor(chain -> {
                    Request original = chain.request();
                    Request request = original.newBuilder()
                            .addHeader("Authorization", "Bearer " + new SharedPref(context).getAccessToken())
                            .addHeader("Accept", "application/json")
                            .addHeader("Accept-Language", "en")
                            .addHeader("API-Version", "v2")
                            .build();
                    return chain.proceed(request);
                }).build())
                .build();
        
        icdInterface = retrofit.create(ICDInterface.class);
    }

    public static synchronized RetrofitHelper getInstance(Context context) {
        if (instance == null) {
            instance = new RetrofitHelper(context);
        }
        return instance;
    }

    public ICDInterface getIcdInterface() {
        return icdInterface;
    }
}