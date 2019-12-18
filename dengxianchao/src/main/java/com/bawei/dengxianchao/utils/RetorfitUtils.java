package com.bawei.dengxianchao.utils;

import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import com.bawei.dengxianchao.services.UrlService;
import com.bawei.dengxianchao.url.MyUrl;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RetorfitUtils {

    private final Retrofit retrofit;
    private final UrlService service;
    private DataCallBack mCallBack;

    private RetorfitUtils(){
        retrofit = new Retrofit.Builder()
                .baseUrl(MyUrl.BASEURL)
                .build();
        service = retrofit.create(UrlService.class);
    }
    private static class RetorfitHolder{
        private static RetorfitUtils utils = new RetorfitUtils();
    }
    public static RetorfitUtils getInstance(){
        return RetorfitHolder.utils;
    }
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 1:
                    mCallBack.onSuccessful(msg.obj.toString());
                    break;
                case 2:
                    mCallBack.onError(msg.obj.toString());
                    break;
            }
        }
    };
    public void getJson(String url,DataCallBack callBack){
        mCallBack = callBack;
        service.getinfo(url).enqueue(new Callback<ResponseBody>() {
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
    public interface DataCallBack{
        void onSuccessful(String s);
        void onError(String s);
    }
}
