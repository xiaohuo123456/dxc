package com.bawei.myapplication.utils;

import android.util.Log;

import com.bawei.myapplication.myurl.MyUrl;
import com.bawei.myapplication.urlService.MyUrlService;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetWorkUtils {

    private final HttpLoggingInterceptor interceptor;
    private final OkHttpClient client;
    private final Retrofit retrofit;
    private final MyUrlService urlService;
    private DataCallBack mCallBack;

    private NetWorkUtils(){
        interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10,TimeUnit.SECONDS)
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl(MyUrl.BASEURL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        urlService = retrofit.create(MyUrlService.class);
    }
    private static class NetWorkHolder{
        private static NetWorkUtils utils = new NetWorkUtils();
    }
    public static NetWorkUtils getInstance(){
        return NetWorkHolder.utils;
    }

    public void getShopCar(String url, final DataCallBack callBack){
        mCallBack = callBack;
        Map<String,Object> map = new HashMap<>();
        map.put("userId","10922");
        map.put("sessionId","157559325456410922");

        urlService.getShopCar(url,map).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {

                        callBack.onSuccessful(responseBody.string());

                    }
                });
    }

    public interface DataCallBack{
        void onSuccessful(String s);
    }
}
