package com.bawei.shens.frag;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.FontsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.shens.R;
import com.bawei.shens.adapter.MyRecyleAdapter;
import com.bawei.shens.base.BaseFragment;
import com.bawei.shens.base.BasePresenter;
import com.bawei.shens.bean.ShopCarBean;
import com.bawei.shens.myapp.MyApp;
import com.bawei.shens.pages.ToKnotZhang;
import com.bawei.shens.presenter.MyPresenter;
import com.bawei.shens.urls.MyUrl;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Fragment_ShopCar extends BaseFragment {
    private RecyclerView recyclerView;
    private MyRecyleAdapter myRecyleAdapter;
    private TextView count_text,price_text;
    private Button button;


    @Override
    protected BasePresenter initPresenter() {
        return new MyPresenter();
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        p.toRequestInfoHeadParms(MyUrl.SHOPCARURL, ShopCarBean.class);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<ShopCarBean.ResultBean.ShoppingCartListBean> forKnot = myRecyleAdapter.getDataForKnot();
                EventBus.getDefault().post(forKnot);
                Log.e("list_ToNotZhang01", "getData: "+forKnot.toString() );
                Intent intent = new Intent(getActivity(), ToKnotZhang.class);
                startActivityForResult(intent,200);
            }
        });

    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getData(List<ShopCarBean.ResultBean.ShoppingCartListBean> list_ToNotZhang){

    }

    @Override
    protected void initView(View view) {
        recyclerView = view.findViewById(R.id.recycler_shopCar);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        count_text = view.findViewById(R.id.count_All);
        price_text = view.findViewById(R.id.price_All);
        button = view.findViewById(R.id.toKnotZhang);
        EventBus.getDefault().register(this);
    }

    @Override
    protected int initLayout() {
        return R.layout.shopcar_fragment;
    }

    @Override
    public void onSuccessful(Object o) {
        if (o instanceof ShopCarBean){
            final List<ShopCarBean.ResultBean> busIness_list = ((ShopCarBean) o).getResult();
            myRecyleAdapter = new MyRecyleAdapter(busIness_list,getActivity());
            recyclerView.setAdapter(myRecyleAdapter);
            myRecyleAdapter.setShopCallBack(new MyRecyleAdapter.ShopCallBack() {
                @Override
                public void bigCheckClick(int bigindex) {
                    myRecyleAdapter.busInssStatus(bigindex);
                    count_text.setText(myRecyleAdapter.CalculatedQantity()+"");
                    price_text.setText(myRecyleAdapter.CalculatedTotalPrice()+"");
                    myRecyleAdapter.notifyDataSetChanged();
                }

                @Override
                public void smallCheckClick(int busIndex, int commpityIndex,boolean status) {
                    myRecyleAdapter.CommodityStatus(busIndex,commpityIndex,status);
                    count_text.setText(myRecyleAdapter.CalculatedQantity()+"");
                    price_text.setText(myRecyleAdapter.CalculatedTotalPrice()+"");
                    myRecyleAdapter.notifyDataSetChanged();
                }

                @Override
                public void setSmallCheckCount(int busIndex, int smallIndex, int count) {
                    myRecyleAdapter.setShopCount(busIndex,smallIndex,count);
                    count_text.setText(myRecyleAdapter.CalculatedQantity()+"");
                    price_text.setText(myRecyleAdapter.CalculatedTotalPrice()+"");
                    Log.e("aaaA", "setSmallCheckCount: "+count );
                    myRecyleAdapter.notifyDataSetChanged();
                }
            });
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    public void onError(String error) {

    }
}
