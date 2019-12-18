package com.bawei.shenmengkai.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.bawei.shenmengkai.bean.GreenDaoJson;
import com.bawei.shenmengkai.contarct.IContarct;
import com.bawei.shenmengkai.dao.GreenDaoJsonDao;
import com.bawei.shenmengkai.myapp.MyApp;
import com.bawei.shenmengkai.urlservice.MyUrlService;
import com.bawei.shenmengkai.urls.MyUrls;
import com.google.gson.Gson;
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

    private final HttpLoggingInterceptor interceptor;
    private final OkHttpClient client;
    private final Retrofit retrofit;
    private final MyUrlService myUrlService;

    private NetWorkUtils(){
        interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10,TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl(MyUrls.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();
        myUrlService = retrofit.create(MyUrlService.class);
    }
    private static class NetWorkHolder{
        private static NetWorkUtils utils = new NetWorkUtils();
    }
    public static NetWorkUtils getInstance(){
        return NetWorkHolder.utils;
    }
    public Boolean getNetWorkStatus(Context context){
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if (info!=null){
            return info.isConnected();
        }
        return false;
    }
    //请求购物车数据
    public void getShopCarInfo(String url , final Class cls, final IContarct.ModelCallBack callBack){
        myUrlService.getShopCarData(url).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<ResponseBody>() {

                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        Gson gson = new Gson();
                        GreenDaoJsonDao jsonDao = MyApp.getDaoSession().getGreenDaoJsonDao();
                        String s = responseBody.string();
                        long insert = jsonDao.insert(new GreenDaoJson(s));
                        if (insert>0){
                            Toast.makeText(MyApp.context,"插入成功",Toast.LENGTH_SHORT).show();
                        }
                        Object o = gson.fromJson(s, cls);
                        callBack.onSuccessful(o);
                    }
                });
    }

}
