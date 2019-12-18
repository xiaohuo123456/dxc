package com.bawei.shenmengkai.base;

import com.bawei.shenmengkai.contarct.IContarct;
import com.bawei.shenmengkai.model.MyModel;

public abstract class BasePresenter<V extends IContarct.ViewCallBack> {
    public V v;
    public MyModel myModel;

    public BasePresenter(){
        myModel = new MyModel();
    }

    public void onAttchView(V v){
        this.v = v;
    }

    public void unAttchView(){
        if (this.v != null){
            this.v = null;
        }
    }

    public V getV() {
        return v;
    }

    public abstract void toRequestShopCarData(String url,Class cls);
}
