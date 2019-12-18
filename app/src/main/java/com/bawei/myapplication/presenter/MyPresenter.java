package com.bawei.myapplication.presenter;

import com.bawei.myapplication.base.BasePresenter;
import com.bawei.myapplication.utils.OkHttpUtils;

public class MyPresenter extends BasePresenter {
    @Override
    public void getData(String url) {
        myModel.getData(url, new OkHttpUtils.DataCallBack() {
            @Override
            public void onSuccessful(String s) {
                getV().onSuccessful(s);
            }

            @Override
            public void onError(String s) {
                getV().onError(s);
            }
        });
    }
}
