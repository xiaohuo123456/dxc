package com.bawei.shenmengkai.base;

import com.bawei.shenmengkai.icontart.IContarlt;
import com.bawei.shenmengkai.model.MyModel;

import java.util.Map;

public abstract class BasePresenter<V extends IContarlt.ViewCallBack> {
    public V v;
    public MyModel myModel;
    public BasePresenter(){
        myModel = new MyModel();
    }
    public V getV() {
        return v;
    }

    public void onAttchView(V v){
        this.v = v;
    }
    public void unAttchView(){
        if (this.v!=null){
            v = null;
        }
    }

    public abstract void login(String url, Map<String,Object> map);
    public abstract void selectOrder(String url, Map<String,Object> map);
}
