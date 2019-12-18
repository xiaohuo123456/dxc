package com.bawei.shenmengkai;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.IconCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.shenmengkai.adapter.MyAdapter;
import com.bawei.shenmengkai.base.BaseActivity;
import com.bawei.shenmengkai.base.BasePresenter;
import com.bawei.shenmengkai.bean.GreenDaoJson;
import com.bawei.shenmengkai.bean.MyBean;
import com.bawei.shenmengkai.contarct.IContarct;
import com.bawei.shenmengkai.dao.GreenDaoJsonDao;
import com.bawei.shenmengkai.myapp.MyApp;
import com.bawei.shenmengkai.presenter.MyPresenter;
import com.bawei.shenmengkai.urls.MyUrls;
import com.bawei.shenmengkai.utils.NetWorkUtils;
import com.google.gson.Gson;

import java.util.List;

public class MainActivity extends BaseActivity implements IContarct.ViewCallBack {
    private RecyclerView recyclerView;
    private MyAdapter myAdapter;
    private TextView price,count;

    @Override
    protected BasePresenter initPresenter() {
        return new MyPresenter();
    }

    @Override
    protected void initData() {
        if (NetWorkUtils.getInstance().getNetWorkStatus(MainActivity.this)){
            p.toRequestShopCarData(MyUrls.SHOPCARURL,MyBean.class);

        }else {
            GreenDaoJsonDao dao = MyApp.getDaoSession().getGreenDaoJsonDao();
            String s = dao.loadAll().toString();
            Gson gson = new Gson();
            MyBean myBean = gson.fromJson(s, MyBean.class);
            List<MyBean.OrderDataBean> orderData = myBean.getOrderData();
            recyclerView.setAdapter(new MyAdapter(orderData,this));
            Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void initView() {
        if (p!=null){
            p.onAttchView(this);
        }
        recyclerView = findViewById(R.id.BusInss_Recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        price = findViewById(R.id.price_All);
        count = findViewById(R.id.count_All);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void onSuccessful(Object o) {
        if (o instanceof MyBean){
            List<MyBean.OrderDataBean> data = ((MyBean) o).getOrderData();
            myAdapter = new MyAdapter(data,MainActivity.this);
            recyclerView.setAdapter(myAdapter);
            myAdapter.getClickCallBack(new MyAdapter.ClickCallBack() {
                @Override
                public void BusInssCheck(int busIndex) {
                    myAdapter.setBusInssCheck(busIndex);
                    count.setText("总数量"+myAdapter.getAllCount());
                    price.setText("总价格"+myAdapter.getAllPrice());
                    myAdapter.notifyDataSetChanged();
                }

                @Override
                public void SmallCheck(int busIndex, int smallIndex) {
                    myAdapter.setShopInssCheck(busIndex,smallIndex);
                    myAdapter.notifyDataSetChanged();
                    count.setText("总数量"+myAdapter.getAllCount());
                    price.setText("总价格"+myAdapter.getAllPrice());

                }

                @Override
                public void CountChange(int busIndex, int smallIndex, int number) {
                    myAdapter.setCountChange(busIndex,smallIndex,number);
                    myAdapter.notifyDataSetChanged();
                    price.setText("总价格"+myAdapter.getAllPrice());
                    count.setText("总数量"+myAdapter.getAllCount());

                }
            });
        }
    }
}
