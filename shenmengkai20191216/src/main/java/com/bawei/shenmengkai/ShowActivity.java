package com.bawei.shenmengkai;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.shenmengkai.adapter.MyAdapter;
import com.bawei.shenmengkai.adapter.MyAdapter2;
import com.bawei.shenmengkai.base.BaseActivity;
import com.bawei.shenmengkai.base.BasePresenter;
import com.bawei.shenmengkai.bean.ShopBean;
import com.bawei.shenmengkai.bean.UserBean;
import com.bawei.shenmengkai.presenter.MyPresenter;
import com.bawei.shenmengkai.urls.MyUrl;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShowActivity extends BaseActivity {
    private RadioGroup radioGroup;
    private UserBean bean1;
    private ImageView head_pic;
    private TextView name;
    private String str1;
    private RecyclerView recyclerView;
    private Button button;


    @Override
    protected void initData() {
        Intent intent = getIntent();
        String pic = intent.getStringExtra("head_pic");
        String userId = intent.getStringExtra("userId");
        String sessionId = intent.getStringExtra("sessionId");
        String namestr = intent.getStringExtra("name");
        Glide.with(this).load(pic)
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(100)))
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(this.head_pic);
        name.setText(namestr);
        final Map<String,Object> map = new HashMap<>();
        map.put("status","0");
        map.put("page","1");
        map.put("count","5");
        Map<String,Object> headmap = new HashMap<>();
        map.put("userId",userId);
        map.put("sessionId",sessionId);
        p.selectOrder(MyUrl.SECLECTURL,map);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.all:
                        Gson gson = new Gson();
                        ShopBean shopBean = gson.fromJson(str1, ShopBean.class);
                        List<ShopBean.ResultBean.MlssBean.CommodityListBeanXX> commodityList = shopBean.getResult().getMlss().getCommodityList();
                        MyAdapter myAdapter = new MyAdapter(commodityList,ShowActivity.this);
                        recyclerView.setAdapter(myAdapter);
                        myAdapter.notifyDataSetChanged();
                        break;
                    case R.id.daipay:
                        Gson gson1 = new Gson();
                        ShopBean shopBean1 = gson1.fromJson(str1, ShopBean.class);
                        List<ShopBean.ResultBean.PzshBean.CommodityListBeanX> commodityLists = shopBean1.getResult().getPzsh().getCommodityList();
                        MyAdapter2 myAdapter2 = new MyAdapter2(commodityLists,ShowActivity.this);
                        recyclerView.setAdapter(myAdapter2);
                        myAdapter2.notifyDataSetChanged();
                        break;
                }

            }
        });
    }

    @Override
    protected void initView() {
        head_pic = findViewById(R.id.head_pic);
        name = findViewById(R.id.name);
        radioGroup = findViewById(R.id.radio_group);
        recyclerView = findViewById(R.id.recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected BasePresenter initPresenter() {
        return new MyPresenter();
    }

    @Override
    protected int initLayout() {
        return R.layout.show_activity;
    }

    @Override
    public void onSuccessful(String str) {
        str1 = str;
    }

    @Override
    public void onError(String error) {
        Toast.makeText(this,error,Toast.LENGTH_SHORT).show();
        Log.e("Aaa", "onError: "+error );
    }




}
