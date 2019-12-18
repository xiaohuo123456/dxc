package com.bawei.myapplication;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.myapplication.app.MyApp;
import com.bawei.myapplication.bao2.MyBeanDao;
import com.bawei.myapplication.base.BaseActivity;
import com.bawei.myapplication.base.BasePresenter;
import com.bawei.myapplication.bean.ExamBannerBean;
import com.bawei.myapplication.bean.MyBean;
import com.bawei.myapplication.icontralt.IContralt;
import com.bawei.myapplication.myurl.ExamUrl;
import com.bawei.myapplication.myurl.MyUrl;
import com.bawei.myapplication.presenter.MyPresenter;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements IContralt.ViewCallBack {

    private MyBeanDao myBeanDao;
    private RecyclerView recyle;
    private Banner banner;
    private List<String> bannerList = new ArrayList<>();

    @Override
    protected BasePresenter initPresenter() {
        return new MyPresenter();
    }

    @Override
    protected void initData() {
//        p.getData(ExamUrl.EXAMBANNER);
        p.getData(MyUrl.SHOPCARURL);
        Toast.makeText(this, "aaa", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void initView() {
        if (p != null) {
            p.onAttchView(this);
        }
        banner = findViewById(R.id.banner_exam);
        myBeanDao = MyApp.getSession().getMyBeanDao();
        recyle = findViewById(R.id.recyle);
        LinearLayoutManager manager = new LinearLayoutManager(this);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void onSuccessful(String s) {
        Log.e("aaa", "accept: "+s);
//        setBanner(s);

    }

    @Override
    public void onError(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    public void setBanner(String s){
        MyBean myBean = new MyBean(s);
        long count = myBeanDao.insert(myBean);
        if (count > 0) {
            Toast.makeText(this, "插入成功", Toast.LENGTH_SHORT).show();
        }
        String s1 = myBeanDao.loadAll().toString();
        Log.e("aaa", "setBanner: "+s1 );
        List<MyBean> list = myBeanDao.loadAll();
//        Toast.makeText(this, list.get(1).getJsonStr(), Toast.LENGTH_SHORT).show();
        String str = list.get(1).getJsonStr();
//        Gson gson = new Gson();
//        ShopBean shopBean = gson.fromJson(str, ShopBean.class);
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
        Gson gson = new Gson();
        ExamBannerBean examBannerBean = gson.fromJson(s, ExamBannerBean.class);
        List<ExamBannerBean.ResultBean> result = examBannerBean.getResult();

        for (int i = 0; i < result.size(); i++) {
            bannerList.add(result.get(i).getImageUrl());
        }
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load(path).into(imageView);
            }
        }).setImages(bannerList).start();
    }
}
