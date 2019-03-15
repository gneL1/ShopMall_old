package com.example.shopmall.okhttputilstest;

import android.app.Application;

import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

public class MyApplication extends Application {
    //MyApplication类需要在Manifest文件中配置才会生效

//    @Override
//    public void onCreate() {
//        super.onCreate();
//        //初始化OkHttpUtils
//        initOkHttpClient();
//    }
//
//    private void initOkHttpClient(){
//        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .connectTimeout(10000L, TimeUnit.MILLISECONDS)//链接超时
//                .readTimeout(10000L,TimeUnit.MILLISECONDS)//读取超时
//                .build();//其它配置
//
//        OkHttpUtils.initClient(okHttpClient);
//    }
}
