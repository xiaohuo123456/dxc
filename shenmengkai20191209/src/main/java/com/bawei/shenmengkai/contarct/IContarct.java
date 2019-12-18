package com.bawei.shenmengkai.contarct;

public interface IContarct {
    public interface ModelCallBack{
        void onSuccessful(Object o);
    }
    public interface ModelEntry{
        void toRequestShopCarData(String url,Class cls,ModelCallBack callBack);
    }
    public interface ViewCallBack{
        void onSuccessful(Object o);
    }
}
