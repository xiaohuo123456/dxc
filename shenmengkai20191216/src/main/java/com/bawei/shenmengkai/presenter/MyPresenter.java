package com.bawei.shenmengkai.presenter;

import com.bawei.shenmengkai.base.BasePresenter;
import com.bawei.shenmengkai.icontart.IContarlt;

import java.util.Map;

public class MyPresenter extends BasePresenter {
    @Override
    public void login(String url, Map map) {
        myModel.login(url, map, new IContarlt.ModelCallBack() {
            @Override
            public void onSuccessful(String str) {
                getV().onSuccessful(str);
            }

            @Override
            public void onError(String error) {
                getV().onError(error);
            }
        });
    }

    @Override
    public void selectOrder(String url, Map map) {
        myModel.selectOrder(url, map, new IContarlt.ModelCallBack() {
            @Override
            public void onSuccessful(String str) {
                getV().onSuccessful(str);
            }

            @Override
            public void onError(String error) {
                getV().onError(error);
            }
        });
    }
}
