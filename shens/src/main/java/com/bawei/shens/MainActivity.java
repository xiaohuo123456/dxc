package com.bawei.shens;

import androidx.annotation.LayoutRes;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.bawei.shens.adapter.MyPagerAdapter;
import com.bawei.shens.adapter.MyPagerStatusAdapter;
import com.bawei.shens.base.BaseActivity;
import com.bawei.shens.base.BasePresenter;
import com.bawei.shens.frag.Fragment_Home;
import com.bawei.shens.frag.Fragment_ShopCar;
import com.bawei.shens.frag.MyFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private List<String> list_name = new ArrayList<>();
    private List<Fragment> list_frag = new ArrayList<>();

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        list_frag.add(new Fragment_Home());
        list_frag.add(new Fragment_ShopCar());
        list_frag.add(new MyFragment());
        list_name.add("首页");
        list_name.add("购物车");
        list_name.add("我的");

        viewPager.setOffscreenPageLimit(0);
        MyPagerStatusAdapter myPagerAdapter = new MyPagerStatusAdapter(getSupportFragmentManager(),list_frag,list_name);
        viewPager.setAdapter(myPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.home_selected);
        tabLayout.getTabAt(1).setIcon(R.drawable.shopcar);
        tabLayout.getTabAt(2).setIcon(R.drawable.my);


    }

    @Override
    protected void initView() {
        tabLayout = findViewById(R.id.tab);
        viewPager = findViewById(R.id.viewpager);

    }

    @Override
    protected int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void onSuccessful(Object o) {

    }

    @Override
    public void onError(String error) {

    }
}
