package com.bawei.shens.presenter;

import com.bawei.shens.base.BasePresenter;
import com.bawei.shens.icontarct.IContarct;

import java.util.Map;

public class MyPresenter extends BasePresenter{
    @Override
    public void toRequestInfoHeadParms(String url, Class cla) {
        myModel.getShopInfo(url,cla, new IContarct.ModelCallBack() {
            @Override
            public void onSuccessful(Object o) {
                v.onSuccessful(o);
            }

            @Override
            public void onError(String error) {
                getV().onError(error);

            }
        });
    }

    @Override
    public void toRequestInfo(String url, Class cla) {
        myModel.getBannerInfo(url, cla, new IContarct.ModelCallBack() {
            @Override
            public void onSuccessful(Object o) {
                v.onSuccessful(o);
            }

            @Override
            public void onError(String error) {
                getV().onError(error);

            }
        });
    }

    @Override
    public void login_Mode(String url, Map map, Class cla) {
        myModel.login_Mode(url,map, cla, new IContarct.ModelCallBack() {
            @Override
            public void onSuccessful(Object o) {
                v.onSuccessful(o);
            }

            @Override
            public void onError(String error) {
                getV().onError(error);
            }
        });
    }
    @Override
    public void orderInfo(String url, Map map, Class cla) {
        myModel.orderInfo(url,map, cla, new IContarct.ModelCallBack() {
            @Override
            public void onSuccessful(Object o) {
                v.onSuccessful(o);
            }

            @Override
            public void onError(String error) {
                getV().onError(error);
            }
        });
    }

}
