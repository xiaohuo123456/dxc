package com.bawei.myapplication.utils;

import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class OkHttpUtils {
    private OkHttpClient okHttpClient;
    private HttpLoggingInterceptor interceptor;
    private DataCallBack mcallBack;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 1:
                    mcallBack.onError((String) msg.obj);
                    break;
                case 2:
                    mcallBack.onSuccessful((String) msg.obj);
                    break;
            }
        }
    };
    private OkHttpUtils(){
        interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10,TimeUnit.SECONDS)
                .build();
    }
    private static class OkHttpHolder{
        private static OkHttpUtils utils = new OkHttpUtils();
    }
    public static OkHttpUtils getInstance(){
        return OkHttpHolder.utils;
    }
    public void getAnsyData(String url,DataCallBack callBack){
        mcallBack = callBack;
        Request request = new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull final IOException e) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        handler.sendMessage(handler.obtainMessage(1,e.getMessage()));
                    }
                }).start();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull final Response response) throws IOException {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            handler.sendMessage(handler.obtainMessage(2,response.body().string()));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }).start();
            }
        });
    }
    public interface DataCallBack{
        void onSuccessful(String s);
        void onError(String s);
    }
}
