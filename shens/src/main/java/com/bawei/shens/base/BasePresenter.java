package com.bawei.shens.base;

import com.bawei.shens.icontarct.IContarct;
import com.bawei.shens.model.MyModel;

import java.util.Map;

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
        if (v != null){
            v=null;
        }
    }

    public V getV() {
        return v;
    }

    public abstract void toRequestInfoHeadParms(String url,Class cla);
    public abstract void toRequestInfo(String url,Class cla);
    public abstract void login_Mode(String url,Map<String,String> map, Class cla);
    public abstract void orderInfo(String url, Map map, Class cla);

}
