package com.bawei.shens.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bawei.shens.icontarct.IContarct;

public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements IContarct.ViewCallBack {
    public P p;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(initLayout(),container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        p = initPresenter();
        initView(view);
        if (p!=null){
            p.onAttchView(this);
        }
    }

    protected abstract P initPresenter();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData(savedInstanceState);
    }

    protected abstract void initData(Bundle savedInstanceState);

    protected abstract void initView(View view);

    protected abstract int initLayout();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (p!=null){
            p = null;
        }
    }
}
