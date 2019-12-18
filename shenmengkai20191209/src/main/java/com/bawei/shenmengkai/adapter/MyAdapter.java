package com.bawei.shenmengkai.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.shenmengkai.R;
import com.bawei.shenmengkai.bean.MyBean;
import com.bawei.shenmengkai.myview.MyView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;
/*
* 日期：2019：12：09
* 姓名：沈梦凯
* 功能：适配器
* */
public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<MyBean.OrderDataBean> list;
    private Context context;

    public MyAdapter(List<MyBean.OrderDataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.businss_layout_item, parent,false);
        BusInssHolder holder = new BusInssHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof BusInssHolder){
            ((BusInssHolder) holder).checkBox.setChecked(list.get(position).getStatus());
            ((BusInssHolder) holder).checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickCallBack.BusInssCheck(position);
                }
            });
            ((BusInssHolder) holder).textView.setText(list.get(position).getShopName());
            ((BusInssHolder) holder).recyclerView.setLayoutManager(new LinearLayoutManager(context));
            ((BusInssHolder) holder).recyclerView.setAdapter(new ShopAdapter(list.get(position).getCartlist(),context,position));
            Log.e("TAG", "onBindViewHolder: "+position);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    class BusInssHolder extends RecyclerView.ViewHolder{
        private CheckBox checkBox;
        private TextView textView;
        private RecyclerView recyclerView;
        public BusInssHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.check_BusInss);
            textView = itemView.findViewById(R.id.name_BusInss);
            recyclerView = itemView.findViewById(R.id.Shopitem_Recycler);
        }
    }
    public class ShopAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
        private List<MyBean.OrderDataBean.CartlistBean> slist;
        private Context context;
        private int index;


        public ShopAdapter(List<MyBean.OrderDataBean.CartlistBean> slist, Context context,int index) {
            this.slist = slist;
            this.context = context;
            this.index = index;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.shop_layout_item, parent,false);
            ShopHolder holder = new ShopHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
            if (holder instanceof ShopHolder){
                ((ShopHolder) holder).checkBox.setChecked(slist.get(position).getStatus());
                ((ShopHolder) holder).checkBox.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clickCallBack.SmallCheck(index,position);
                        Log.e("TAG", "onClick: "+index);
                    }
                });
                ((ShopHolder) holder).name.setText(list.get(index).getCartlist().get(position).getShopName());
                ((ShopHolder) holder).message.setText(slist.get(position).getProductName());
                Glide.with(context).load(slist.get(position).getDefaultPic())
                        .placeholder(R.drawable.ic_launcher_background)
                        .error(R.drawable.ic_launcher_background)
                        .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                        .into(((ShopHolder) holder).imageView);

                ((ShopHolder) holder).price.setText(slist.get(position).getPrice()+"");
                ((ShopHolder) holder).myView.setCount(slist.get(position).getCount());
                ((ShopHolder) holder).myView.setClickCallBack(new MyView.ClickCallBack() {
                    @Override
                    public void setCount(int number) {
                        Log.e("TAG", "setCount: "+index+position);
                        clickCallBack.CountChange(index,position,number);
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            return slist.size();
        }
    }
    class ShopHolder extends RecyclerView.ViewHolder{
        private CheckBox checkBox;
        private TextView name,message,price;
        private MyView myView;
        private ImageView imageView;
        public ShopHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.shop_check);
            name = itemView.findViewById(R.id.shop_name);
            message = itemView.findViewById(R.id.shop_message);
            imageView = itemView.findViewById(R.id.shop_image);
            price = itemView.findViewById(R.id.shop_price);
            myView = itemView.findViewById(R.id.addandremove);
        }
    }
    public interface ClickCallBack{
        void BusInssCheck(int busIndex);
        void SmallCheck(int busIndex,int smallIndex);
        void CountChange(int busIndex,int smallIndex,int number);
    }
    private ClickCallBack clickCallBack;

    public void getClickCallBack(ClickCallBack callBack){
        clickCallBack = callBack;
    }
    public void setBusInssCheck(int busIndex){
        Boolean status = list.get(busIndex).getStatus();
        list.get(busIndex).setStatus(!status);
        for (int i = 0; i < list.get(busIndex).getCartlist().size(); i++) {
            list.get(busIndex).getCartlist().get(i).setStatus(!status);
        }
    }
    public void setShopInssCheck(int busIndex,int smallIndex){
        Boolean flag = true;
        Boolean status = list.get(busIndex).getCartlist().get(smallIndex).getStatus();
        list.get(busIndex).getCartlist().get(smallIndex).setStatus(!status);
        for (int i = 0; i < list.get(busIndex).getCartlist().size(); i++) {
            if (!list.get(busIndex).getCartlist().get(i).getStatus()){
                flag = false;
            }
        }
        list.get(busIndex).setStatus(flag);
    }
    public void setCountChange(int busIndex, int smallIndex, int number) {
        Log.e("TAG", "setCountChange: "+busIndex );
        list.get(busIndex).getCartlist().get(smallIndex).setCount(number);
    }
    //计算总数
    public int getAllCount(){
        int sum = 0;
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.get(i).getCartlist().size(); j++) {
                if (list.get(i).getCartlist().get(j).getStatus()){
                    sum += list.get(i).getCartlist().get(j).getCount();
                }
            }
        }
        return sum;
    }
    //计算总价
    public int getAllPrice(){
        int sum = 0;
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.get(i).getCartlist().size(); j++) {
                if (list.get(i).getCartlist().get(j).getStatus()){
                    sum += list.get(i).getCartlist().get(j).getCount()*list.get(i).getCartlist().get(j).getPrice();
                }
            }
        }
        return sum;
    }

}
