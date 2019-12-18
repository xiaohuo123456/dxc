package com.bawei.shenmengkai.icontart;

public interface IContarlt {
    public interface ModelCallBack{
        void onSuccessful(String str);
        void onError(String error);
    }
    public interface ViewCallBack{
        void onSuccessful(String str);
        void onError(String error);
    }
}
