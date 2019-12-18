package com.bawei.shens.model;

import com.bawei.shens.icontarct.IContarct;
import com.bawei.shens.utils.NetWorkUtils;

import java.util.Map;

public class MyModel {


    public void getShopInfo(String url , Class cla, final IContarct.ModelCallBack callBack){
        NetWorkUtils.getInstance().getDataPrams(url,cla, new NetWorkUtils.DataCallBack() {
            @Override
            public void onSuccessful(Object o) {
                callBack.onSuccessful(o);
            }

            @Override
            public void onError(String error) {
                callBack.onError(error);
            }
        });
    }
    public void getBannerInfo(String url, Class cla, final IContarct.ModelCallBack callBack){
        NetWorkUtils.getInstance().getDataInfo(url, cla, new NetWorkUtils.DataCallBack() {
            @Override
            public void onSuccessful(Object o) {
                callBack.onSuccessful(o);
            }

            @Override
            public void onError(String error) {
                callBack.onError(error);

            }
        });
    }
    public void login_Mode(String url,Map<String,String> map, Class cla, final IContarct.ModelCallBack callBack){
        NetWorkUtils.getInstance().login_Mode(url, map, cla, new NetWorkUtils.DataCallBack() {
            @Override
            public void onSuccessful(Object o) {
                callBack.onSuccessful(o);
            }

            @Override
            public void onError(String error) {
                callBack.onError(error);

            }
        });
    }
    public void orderInfo(String url,Map<String,Object> map, Class cla, final IContarct.ModelCallBack callBack){
        NetWorkUtils.getInstance().orderInfo(url, map, cla, new NetWorkUtils.DataCallBack() {
            @Override
            public void onSuccessful(Object o) {
                callBack.onSuccessful(o);
            }

            @Override
            public void onError(String error) {
                callBack.onError(error);
            }
        });
    }
}
