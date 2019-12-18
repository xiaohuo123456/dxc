package com.bawei.shens.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class MyPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> flist;
    private List<String> slist;


    public MyPagerAdapter(@NonNull FragmentManager fm,List<Fragment> flist,List<String> slist) {
        super(fm);
        this.flist = flist;
        this.slist = slist;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return flist.get(position);
    }

    @Override
    public int getCount() {
        return flist.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return slist.get(position);
    }
}
