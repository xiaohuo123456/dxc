package com.bawei.myapplication.utils;

import android.os.Handler;
import android.os.Message;


import androidx.annotation.NonNull;

import com.bawei.myapplication.myurl.MyUrl;
import com.bawei.myapplication.urlService.MyUrlService;

import java.io.IOException;

import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RetrofitUtils {


    private final Retrofit retrofit;
    private final MyUrlService myUrlService;

    private RetrofitUtils(){
        retrofit = new Retrofit.Builder()
                .baseUrl(MyUrl.BASEURL)
                .build();
        myUrlService = retrofit.create(MyUrlService.class);
    }
    private static class RetrofitHolder{
        private static RetrofitUtils utils = new RetrofitUtils();
    }
    public static RetrofitUtils getInstance(){
        return RetrofitHolder.utils;
    }
    private OkHttpUtils.DataCallBack mCallBack;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 1:
                    mCallBack.onSuccessful((String) msg.obj);
                    break;
                case 2:
                    mCallBack.onError((String) msg.obj);
                    break;
            }
        }
    };
    public void getInfo(String url,OkHttpUtils.DataCallBack callBack){
        mCallBack = callBack;
        myUrlService.getInfo(url).enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, final Response<ResponseBody> response) {

                        try {
                            handler.sendMessage(handler.obtainMessage(1,response.body().string()));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, final Throwable t) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        handler.sendMessage(handler.obtainMessage(2,t.getMessage()));
                    }
                }).start();
            }
        });
    }
    public void getDataPrams(String url,String things,OkHttpUtils.DataCallBack callBack){
        mCallBack = callBack;
        Map<String,Object> map = new HashMap<>();
        map.put("keyword", things);
        myUrlService.getInfo1(url,map).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    handler.sendMessage(handler.obtainMessage(1,response.body().string()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                handler.sendMessage(handler.obtainMessage(2,t.getMessage()));
            }
        });
    }

}
