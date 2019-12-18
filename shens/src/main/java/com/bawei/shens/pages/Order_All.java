package com.bawei.shens.pages;

import android.util.Log;
import android.widget.Button;

import androidx.recyclerview.widget.RecyclerView;

import com.bawei.shens.R;
import com.bawei.shens.base.BaseActivity;
import com.bawei.shens.base.BasePresenter;
import com.bawei.shens.bean.OrderBean;
import com.bawei.shens.presenter.MyPresenter;
import com.bawei.shens.urls.MyUrl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Order_All extends BaseActivity {
    private RecyclerView recyclerView;
    private Button button;
    @Override
    protected BasePresenter initPresenter() {
        return new MyPresenter();
    }

    @Override
    protected void initData() {
        Map<String,Object> map = new HashMap<>();
        map.put("status",0);
        map.put("page",1);
        map.put("count",5);
        p.orderInfo(MyUrl.MYORDERURL,map, OrderBean.class);
    }

    @Override
    protected void initView() {

    }
    @Override
    protected int initLayout() {
        return R.layout.order_all_layout;
    }

    @Override
    public void onSuccessful(Object o) {
        if (o instanceof OrderBean){
            List<OrderBean.OrderListBean> orderList = ((OrderBean) o).getOrderList();
            Log.e("12312", "onSuccessful: "+orderList.toString());
        }
    }

    @Override
    public void onError(String error) {
        Log.e("12312", "error: "+error);
    }
}
