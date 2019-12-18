package com.bawei.shenmengkai.model;

import com.bawei.shenmengkai.contarct.IContarct;
import com.bawei.shenmengkai.utils.NetWorkUtils;

public class MyModel implements IContarct.ModelEntry {

    @Override
    public void toRequestShopCarData(String url, Class cls, final IContarct.ModelCallBack callBack) {
        NetWorkUtils.getInstance().getShopCarInfo(url, cls, new IContarct.ModelCallBack() {
            @Override
            public void onSuccessful(Object o) {
                callBack.onSuccessful(o);
            }
        });
    }
}
