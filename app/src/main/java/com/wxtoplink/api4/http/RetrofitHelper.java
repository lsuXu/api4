package com.wxtoplink.api4.http;


import android.util.Log;

import com.wxtoplink.api4.API4Manager;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 12852 on 2018/7/24.
 */

public class RetrofitHelper {

    private Retrofit retrofit ;

    private API4Services api4Services ;

    private static final int DEFAULT_TIMEOUT = 60;

    private static final RetrofitHelper instance = new RetrofitHelper();

    private RetrofitHelper(){

        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        httpClientBuilder.readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        httpClientBuilder.writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        httpClientBuilder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request newRequest = chain.request().newBuilder()
                        //对所有请求添加请求头
//                        .header("Content-Type", "application/json;charset=UTF-8")
                        .build();
                return chain.proceed(newRequest);
            }
        });

        retrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(API4Manager.getInstance().getDefaultData().getBaseUrl())
                .build();

        api4Services = retrofit.create(API4Services.class);
    }

    public static RetrofitHelper getInstance(){
        return instance ;
    }

    public Retrofit getRetrofit(){
        return retrofit ;
    }

    public API4Services getApi4Services(){
        return api4Services;
    }

}
