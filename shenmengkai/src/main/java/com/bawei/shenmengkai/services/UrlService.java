package com.bawei.shenmengkai.services;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.FieldMap;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface UrlService {
    @GET
    Observable<ResponseBody> getInfo(@Url String url);
    @GET
    Observable<ResponseBody> getInfoPrams(@Url String url ,@QueryMap Map<String,Object> map);
    @POST
    Observable<ResponseBody> postInfo(@Url String url, @FieldMap Map<String,Object> map);

}
