package com.bawei.shens.frag;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.cardview.widget.CardView;

import com.bawei.shens.R;
import com.bawei.shens.pages.Order_All;
import com.bawei.shens.base.BaseFragment;
import com.bawei.shens.base.BasePresenter;
import com.bawei.shens.presenter.MyPresenter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

public class MyFragment extends BaseFragment {
    private CardView cardView;
    private ImageView imageView;
    private LinearLayout myOrder;
    @Override
    protected BasePresenter initPresenter() {
        return new MyPresenter();
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        Log.e("aaaaa", "initData: "+ "MyFragment");
        cardView.setAlpha(0.5f);
        Glide.with(getActivity())
                .load(R.drawable.head_image)
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(100)))
                .into(imageView);
        myOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Order_All.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initView(View view) {
        cardView = view.findViewById(R.id.my_frag_cardview);
        imageView = view.findViewById(R.id.image_head);
        myOrder = view.findViewById(R.id.my_card_MyOrder_btn);
    }

    @Override
    protected int initLayout() {
        return R.layout.my_fragment_layout;
    }

    @Override
    public void onSuccessful(Object o) {

    }

    @Override
    public void onError(String error) {

    }
}
