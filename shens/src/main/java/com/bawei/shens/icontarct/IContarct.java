package com.bawei.shens.icontarct;

public interface IContarct {
    public interface ModelCallBack{
        void onSuccessful(Object o);
        void onError(String error);
    }
    public interface ViewCallBack{
        void onSuccessful(Object o);
        void onError(String error);
    }
}
