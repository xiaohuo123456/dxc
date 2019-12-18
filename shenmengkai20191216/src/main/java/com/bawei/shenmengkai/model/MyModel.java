package com.bawei.shenmengkai.model;

import com.bawei.shenmengkai.icontart.IContarlt;
import com.bawei.shenmengkai.utils.NetWorkUtil;

import java.util.Map;

public class MyModel {
    public void login(String url, Map<String,Object> map, final IContarlt.ModelCallBack callBack){
        NetWorkUtil.getInstance().login(url, map, new IContarlt.ModelCallBack() {
            @Override
            public void onSuccessful(String str) {
                callBack.onSuccessful(str);
            }

            @Override
            public void onError(String error) {
                callBack.onError(error);
            }
        });
    }
    public void selectOrder(String url, Map<String,Object> map, final IContarlt.ModelCallBack callBack){
        NetWorkUtil.getInstance().selectOrder(url, map, new IContarlt.ModelCallBack() {
            @Override
            public void onSuccessful(String str) {
                callBack.onSuccessful(str);
            }

            @Override
            public void onError(String error) {
                callBack.onError(error);
            }
        });
    }
}
