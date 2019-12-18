package com.bawei.shens.frag;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.shens.R;
import com.bawei.shens.adapter.HomeShopListAdapter;
import com.bawei.shens.base.BaseFragment;
import com.bawei.shens.base.BasePresenter;
import com.bawei.shens.bean.BannerBean;
import com.bawei.shens.bean.HomeShopBean;
import com.bawei.shens.presenter.MyPresenter;
import com.bawei.shens.urls.MyUrl;
import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class Fragment_Home extends BaseFragment {
    private Banner banner;
    private RecyclerView recyclerView;
    @Override
    protected BasePresenter initPresenter() {
        return new MyPresenter();
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        Log.e("aaaaa", "initData: "+ "Fragment_Home");
        p.toRequestInfo(MyUrl.BANNERURL, BannerBean.class);
        p.toRequestInfo(MyUrl.SHOPHOMEURL, HomeShopBean.class);
    }

    @Override
    protected void initView(View view) {
        banner = view.findViewById(R.id.myBanner);
        recyclerView = view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    protected int initLayout() {
        return R.layout.home_fragment;
    }

    @Override
    public void onSuccessful(Object o) {
        if (o instanceof BannerBean){
            List<BannerBean.ResultBean> result = ((BannerBean) o).getResult();
            List<String> list = new ArrayList<>();
            for (int i = 0; i < result.size(); i++) {
                list.add(result.get(i).getImageUrl());
            }
            banner.setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    Glide.with(context)
                            .load(path)
                            .into(imageView);
                }
            }).setImages(list).setDelayTime(2000).start();
        }
        if (o instanceof HomeShopBean){
            HomeShopBean.ResultBean result = ((HomeShopBean) o).getResult();
            HomeShopListAdapter adapter = new HomeShopListAdapter(result,getActivity());
            recyclerView.setAdapter(adapter);
        }
    }

    @Override
    public void onError(String error) {

    }
}
