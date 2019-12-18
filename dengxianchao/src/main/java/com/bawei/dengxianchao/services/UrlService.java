package com.bawei.dengxianchao.services;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface UrlService {

    @GET
    Call<ResponseBody> getinfo(@Url String url);
    @POST
    Call<ResponseBody> postInfo(@Url String url, @FieldMap Map<String,Object> map);
 }
