package com.bawei.shenmengkai.presenter;

import com.bawei.shenmengkai.base.BasePresenter;
import com.bawei.shenmengkai.contarct.IContarct;

public class MyPresenter extends BasePresenter {
    @Override
    public void toRequestShopCarData(String url, Class cls) {
        myModel.toRequestShopCarData(url, cls, new IContarct.ModelCallBack() {
            @Override
            public void onSuccessful(Object o) {
                getV().onSuccessful(o);
            }
        });
    }
}
