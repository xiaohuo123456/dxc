package com.bawei.shenmengkai.urlservice;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface MyUrlService {
    @GET
    Observable<ResponseBody> getShopCarData(@Url String url);
}
