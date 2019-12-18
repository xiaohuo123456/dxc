package com.bawei.shenmengkai.urlservice;

import java.util.Map;

import io.reactivex.Observable;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface UrlService {
    @POST
    Observable<ResponseBody> login(@Url String url, @QueryMap Map<String,Object> map);
    @GET
    Observable<ResponseBody> selectOrder(@Url String url);
}
