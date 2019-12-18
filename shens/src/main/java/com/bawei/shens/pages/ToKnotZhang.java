package com.bawei.shens.pages;

import android.content.SharedPreferences;
import android.util.Log;

import com.bawei.shens.R;
import com.bawei.shens.base.BaseActivity;
import com.bawei.shens.base.BasePresenter;
import com.bawei.shens.bean.ShopCarBean;
import com.bawei.shens.myapp.MyApp;
import com.bawei.shens.presenter.MyPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

public class ToKnotZhang extends BaseActivity {

    private List<ShopCarBean.ResultBean.ShoppingCartListBean> list;

    @Override
    protected BasePresenter initPresenter() {
        return new MyPresenter() ;
    }

    @Override
    protected void initData() {


    }


    @Override
    protected void initView() {

    }

    @Override
    protected int initLayout() {
        return R.layout.toknotzhang_layout;
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getData(List<ShopCarBean.ResultBean.ShoppingCartListBean> list_ToNotZhang){
        list = list_ToNotZhang;
        Log.e("list_ToNotZhang", "getData: "+list_ToNotZhang.toString());
    }

    @Override
    public void onSuccessful(Object o) {

    }

    @Override
    public void onError(String error) {

    }
}
