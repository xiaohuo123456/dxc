package com.bawei.shenmengkai.utils;

import com.bawei.shenmengkai.services.UrlService;
import com.bawei.shenmengkai.urls.MyUrls;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetWorkUtils {
    private OkHttpClient okHttpClient;
    private HttpLoggingInterceptor interceptor;
    private final Retrofit retrofit;
    private final UrlService urlService;
    private DataCallBack mCallBack;

    private NetWorkUtils(){
        interceptor = new HttpLoggingInterceptor();
        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10,TimeUnit.SECONDS)
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl(MyUrls.BASEURL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        urlService = retrofit.create(UrlService.class);

    }
    private static class NetWorkHolder{
        private static NetWorkUtils utils = new NetWorkUtils();
    }
    public static NetWorkUtils getInstance(){
        return NetWorkHolder.utils;
    }
    public void getData(String url, final DataCallBack callBack){
        mCallBack = callBack;
        urlService.getInfo(url).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
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
