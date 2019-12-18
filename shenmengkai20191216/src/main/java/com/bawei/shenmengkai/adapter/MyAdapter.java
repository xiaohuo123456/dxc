package com.bawei.shenmengkai.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.shenmengkai.R;
import com.bawei.shenmengkai.bean.ShopBean;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<ShopBean.ResultBean.MlssBean.CommodityListBeanXX> listBeanXXES;
    private Context context;

    public MyAdapter(List<ShopBean.ResultBean.MlssBean.CommodityListBeanXX> listBeanXXES, Context context) {
        this.listBeanXXES = listBeanXXES;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_item, null);
        Holder holder = new Holder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof Holder){
            Glide.with(context).load(listBeanXXES.get(position).getMasterPic())
                    .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                    .into(((Holder) holder).imageView);
            ((Holder) holder).textView.setText(listBeanXXES.get(position).getCommodityName());
            ((Holder) holder).button.setBackgroundColor(Color.YELLOW);
        }
    }

    @Override
    public int getItemCount() {
        return listBeanXXES.size();
    }
    class Holder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView;
        private Button button;
        public Holder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_shop);
            textView = itemView.findViewById(R.id.name_shop);
            button = itemView.findViewById(R.id.bottom_btn);
        }

    }
}
