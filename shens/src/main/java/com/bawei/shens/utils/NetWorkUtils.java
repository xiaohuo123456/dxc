package com.bawei.shens.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.bawei.shens.bean.LoginBean;
import com.bawei.shens.myapp.MyApp;
import com.bawei.shens.pages.Login_Activity;
import com.bawei.shens.urls.MyUrl;
import com.bawei.shens.urlservice.UrlService;
import com.google.gson.Gson;
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
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetWorkUtils {
    private OkHttpClient okHttpClient;
    private HttpLoggingInterceptor interceptor;
    private final Retrofit retrofit;
    private final UrlService urlService;

    private NetWorkUtils(){
        interceptor = new HttpLoggingInterceptor();
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10,TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .addInterceptor(new Interceptor() {
                    @NotNull
                    @Override
                    public Response intercept(@NotNull Chain chain) throws IOException {
                        SharedPreferences shens = MyApp.context.getSharedPreferences("shens", Context.MODE_PRIVATE);
                        String status = shens.getString("status", "1001");
                        if (status.equals("0000")){
                            String userId = shens.getString("userId", "");
                            String sessionId = shens.getString("sessionId", "");
                            Request request = chain.request().newBuilder()
                                    .addHeader("userId",userId)
                                    .addHeader("sessionId",sessionId)
                                    .build();
                            return chain.proceed(request);
                        }else {
                            return chain.proceed(chain.request());
                        }
                    }
                })
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl(MyUrl.BASEURL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        urlService = retrofit.create(UrlService.class);
    }
    private static class NetWorkHolder{
        private static NetWorkUtils utils = new NetWorkUtils();
    }
    public static NetWorkUtils getInstance(){
        return NetWorkHolder.utils;
    }
    public void getDataPrams(String url, final Class cla, final DataCallBack callBack){

            urlService.toRequestInfoHeadParms(url).observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(new Consumer<ResponseBody>() {
                        @Override
                        public void accept(ResponseBody responseBody) throws Exception {
                            Gson gson = new Gson();
                            Object o = gson.fromJson(responseBody.string(), cla);
                            callBack.onSuccessful(o);
                        }
                    });
    }
    public void getDataInfo(String url, final Class cla, final DataCallBack callBack){
        urlService.toRequestInfo(url).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        Gson gson = new Gson();
                        Object o = gson.fromJson(responseBody.string(), cla);
                        callBack.onSuccessful(o);
                    }
                });
    }
    public void login_Mode(String url, Map<String,String> map, final Class cla, final DataCallBack callBack){
        urlService.toLogin(url,map).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        Gson gson = new Gson();
                        String string = null;
                        try {
                            string = responseBody.string();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Object o = gson.fromJson(string, cla);
                        callBack.onSuccessful(o);
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

    public void orderInfo(String url, Map<String,Object> map, final Class cla, final DataCallBack callBack){
        urlService.orderInfo(url,map).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {

                        try {
                            Gson gson = new Gson();
                            String string = responseBody.string();
                            Object o = gson.fromJson(string, cla);
                            callBack.onSuccessful(o);
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


    public interface DataCallBack{
        void onSuccessful(Object o);
        void onError(String error);
    }
}
