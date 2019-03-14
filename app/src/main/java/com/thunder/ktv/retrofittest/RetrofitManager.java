package com.thunder.ktv.retrofittest;

import com.thunder.ktv.retrofittest.net.HttpApi;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {


    public static final String url = "http://www.kuaidi100.com/";

    private volatile static RetrofitManager singleton;

    private HttpApi httpService;

    private static final int TIMEOUT = 15;
    public RetrofitManager() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(TIMEOUT, TimeUnit.SECONDS);

        Retrofit retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(url)
                .build();
        httpService = retrofit.create(HttpApi.class);
    }

    public static RetrofitManager getInstance() {
        if (singleton == null) {
            synchronized (RetrofitManager.class) {
                if (singleton == null) {
                    singleton = new RetrofitManager();
                }
            }
        }
        return singleton;
    }

    public static HttpApi getService(){
        return getInstance().httpService;
    }
}
