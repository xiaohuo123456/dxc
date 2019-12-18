package com.bawei.shenmengkai.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.MainThread;

import com.bawei.shenmengkai.R;
import com.bawei.shenmengkai.icontart.IContarlt;
import com.bawei.shenmengkai.myapp.MyApp;
import com.bawei.shenmengkai.urls.MyUrl;
import com.bawei.shenmengkai.urlservice.UrlService;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetWorkUtil {

    private final HttpLoggingInterceptor interceptor;
    private final UrlService urlService;

    private NetWorkUtil(){
        interceptor = new HttpLoggingInterceptor();
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10,TimeUnit.SECONDS)
                .addInterceptor(new Interceptor() {
                    @NotNull
                    @Override
                    public Response intercept(@NotNull Chain chain) throws IOException {
                        SharedPreferences shenmengkai = MyApp.context.getSharedPreferences("shenmengkai", Context.MODE_PRIVATE);
                        String userId = shenmengkai.getString("userId", "");
                        String sessionId = shenmengkai.getString("sessionId", "");
                        if (userId.isEmpty()&&sessionId.isEmpty()){
                            return chain.proceed(chain.request());
                        }else {
                            Request request = chain.request().newBuilder()
                                    .addHeader("userId",userId)
                                    .addHeader("sessionId",sessionId)
                                    .build();
                            Log.e("sss", "intercept: "+request );
                            return chain.proceed(request);
                        }
                    }
                }).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MyUrl.BASEURL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        urlService = retrofit.create(UrlService.class);
    }
    public static class NetWorlHolder{
        public static NetWorkUtil util = new NetWorkUtil();
    }
    public static NetWorkUtil getInstance(){
        return NetWorlHolder.util;
    }
    public void login(String url, Map<String,Object> map, final IContarlt.ModelCallBack callBack){
        urlService.login(url,map).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            callBack.onSuccessful(responseBody.string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public void selectOrder(String url, Map<String,Object> map, final IContarlt.ModelCallBack callBack){
        urlService.selectOrder(url).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            callBack.onSuccessful(responseBody.string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
