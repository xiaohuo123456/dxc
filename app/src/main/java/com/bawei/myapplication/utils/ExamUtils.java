package com.bawei.myapplication.utils;

import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import com.bawei.myapplication.myurl.ExamUrl;
import com.bawei.myapplication.urlService.ExamService;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ExamUtils {

    private final Retrofit retrofit;
    private final ExamService examService;
    private OkHttpUtils.DataCallBack mCallBack;

    private ExamUtils(){
        retrofit = new Retrofit.Builder()
                .baseUrl(ExamUrl.BASEEXAM)
                .build();
        examService = retrofit.create(ExamService.class);
    }
    private static class ExamHolder{
        private static ExamUtils utils = new ExamUtils();
    }
    public static ExamUtils getInstance(){
        return ExamHolder.utils;
    }
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
        examService.getInfo(url).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, final Response<ResponseBody> response) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            handler.sendMessage(handler.obtainMessage(1,response.body().string()));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
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
}
