package com.bawei.shenmengkai.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bawei.shenmengkai.icontart.IContarlt;

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements IContarlt.ViewCallBack {
    public P p;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initLayout());
        p = initPresenter();
        if (p != null){
            p.onAttchView(this);
        }
        initView();
        initData();
    }

    protected abstract void initData();

    protected abstract void initView();

    protected abstract P initPresenter();

    protected abstract int initLayout();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        p.unAttchView();
    }
}
