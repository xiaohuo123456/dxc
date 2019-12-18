package com.bawei.shens.frag.order_frag;

import android.os.Bundle;
import android.view.View;

import com.bawei.shens.R;
import com.bawei.shens.base.BaseFragment;
import com.bawei.shens.base.BasePresenter;
import com.bawei.shens.bean.OrderBean;
import com.bawei.shens.presenter.MyPresenter;
import com.bawei.shens.urls.MyUrl;

import java.util.HashMap;
import java.util.Map;

public class ToBePaid_Fragment extends BaseFragment {
    @Override
    protected BasePresenter initPresenter() {
        return new MyPresenter();
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        Map<String,Object> map = new HashMap<>();
        map.put("status",1);
        map.put("page",1);
        map.put("count",5);
        p.orderInfo(MyUrl.MYORDERURL,map, OrderBean.class);
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected int initLayout() {
        return R.layout.order_all_layout;
    }

    @Override
    public void onSuccessful(Object o) {

    }

    @Override
    public void onError(String error) {

    }
}
