package com.bawei.myapplication.model;

import com.bawei.myapplication.myurl.MyUrl;
import com.bawei.myapplication.urlService.ExamService;
import com.bawei.myapplication.utils.ExamUtils;
import com.bawei.myapplication.utils.NetWorkUtils;
import com.bawei.myapplication.utils.OkHttpUtils;
import com.bawei.myapplication.utils.RetrofitUtils;

import okhttp3.OkHttpClient;

public class MyModel {
    public void getData(String url, final OkHttpUtils.DataCallBack callBack){
        NetWorkUtils.getInstance().getShopCar(url, new NetWorkUtils.DataCallBack() {
            @Override
            public void onSuccessful(String s) {
                callBack.onSuccessful(s);
            }
        });

//        ExamUtils.getInstance().getInfo(url, new OkHttpUtils.DataCallBack() {
//            @Override
//            public void onSuccessful(String s) {
//                callBack.onSuccessful(s);
//            }
//
//            @Override
//            public void onError(String s) {
//                callBack.onError(s);
//            }
//        });
//        OkHttpUtils.getInstance().getAnsyData(url, new OkHttpUtils.DataCallBack() {
//            @Override
//            public void onSuccessful(String s) {
//                callBack.onSuccessful(s);
//            }
//
//            @Override
//            public void onError(String s) {
//                callBack.onError(s);
//            }
//        });
//        RetrofitUtils.getInstance().getInfo(MyUrl.COMMDITY, new OkHttpUtils.DataCallBack() {
//            @Override
//            public void onSuccessful(String s) {
//                callBack.onSuccessful(s);
//            }
//
//           @Override
//           public void onError(String s) {
//               callBack.onError(s);
//           }
//        });
//        RetrofitUtils.getInstance().getDataPrams(MyUrl.COMMDITYPRAME,"鞋子", new OkHttpUtils.DataCallBack() {
//            @Override
//            public void onSuccessful(String s) {
//                callBack.onSuccessful(s);
//            }
//
//            @Override
//            public void onError(String s) {
//                callBack.onError(s);
//            }
//        });


    }
}
