package com.bawei.myapplication.urlService;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface MyUrlService {
    @GET
    Call<ResponseBody> getInfo(@Url String url);
    @GET
    Call<ResponseBody> getInfo1(@Url String url , @QueryMap Map<String,Object> map);
    @GET
    Observable<ResponseBody> getShopCar(@Url String url);
    @GET
    Observable<ResponseBody> getShopCar(@Url String url , @HeaderMap Map<String,Object> map);
}
