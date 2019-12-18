package com.bawei.shens.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.shens.R;
import com.bawei.shens.bean.HomeShopBean;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class PzshHomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<HomeShopBean.ResultBean.PzshBean.CommodityListBeanX> list;
    private Context context;

    public PzshHomeAdapter(List<HomeShopBean.ResultBean.PzshBean.CommodityListBeanX> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.pzsh_item_layout, null);
        PzshHolder holder = new PzshHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof PzshHolder){
            Glide.with(context).load(list.get(position).getMasterPic())
                    .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                    .error(R.drawable.ic_launcher_background)
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(((PzshHolder) holder).imageView);
            ((PzshHolder) holder).name.setText(list.get(position).getCommodityName());
            ((PzshHolder) holder).price.setText(list.get(position).getPrice()+"");
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class PzshHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name,price;
        public PzshHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.pzsh_image);
            name = itemView.findViewById(R.id.pzsh_name);
            price = itemView.findViewById(R.id.pzsh_price);
        }
    }
}
