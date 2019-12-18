package com.bawei.shens.urlservice;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface UrlService {
    @GET
    Observable<ResponseBody> toRequestInfoHeadParms(@Url String url);

    @GET
    Observable<ResponseBody> toRequestInfo(@Url String url);

    @POST
    Observable<ResponseBody> toLogin(@Url String url, @QueryMap Map<String,String> map);

    @GET
    Observable<ResponseBody> orderInfo(@Url String url,@QueryMap Map<String,Object> map);

}
