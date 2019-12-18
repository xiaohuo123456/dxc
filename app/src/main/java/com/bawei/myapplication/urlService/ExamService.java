package com.bawei.myapplication.urlService;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ExamService {
    @GET
    Call<ResponseBody> getInfo(@Url String url);
}
