package com.bawei.myapplication.base;

import com.bawei.myapplication.icontralt.IContralt;
import com.bawei.myapplication.model.MyModel;

public abstract class BasePresenter<V extends IContralt.ViewCallBack> {
    public V v;
    public  MyModel myModel;

    public V getV() {
        return v;
    }

    public void onAttchView(V v){
        this.v = v;
    }

    public void unAttchView(){
        if (v!=null){
            v = null;
        }
    }

    public BasePresenter(){
        myModel = new MyModel();
    }

    public abstract void getData(String url);
}
