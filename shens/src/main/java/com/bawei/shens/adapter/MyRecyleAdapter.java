package com.bawei.shens.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.shens.R;
import com.bawei.shens.bean.ShopCarBean;
import com.bawei.shens.view.AddAndRemoveView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

public class MyRecyleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static List<ShopCarBean.ResultBean> list;
    private Context context;

    public MyRecyleAdapter(List<ShopCarBean.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.business_item, null);
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
                    mCallBack.bigCheckClick(position);
                }
            });
            ((BusInssHolder) holder).recyclerView.setLayoutManager(new LinearLayoutManager(context));
            for (int i = 0; i < list.size(); i++) {
                BusInssHolder.CommodityAdapter commodityAdapter = new BusInssHolder.CommodityAdapter(list.get(position).getShoppingCartList(),context,position);
                ((BusInssHolder) holder).recyclerView.setAdapter(commodityAdapter);
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
        static class BusInssHolder extends RecyclerView.ViewHolder{
        CheckBox checkBox;
        RecyclerView recyclerView;
        public BusInssHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.business_check);
            recyclerView = itemView.findViewById(R.id.business_recycle);
        }
        private static class  CommodityAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
            private List<ShopCarBean.ResultBean.ShoppingCartListBean> slist;
            private Context context;
            private int bigIndex;

            public CommodityAdapter(List<ShopCarBean.ResultBean.ShoppingCartListBean> list, Context context,int bigIndex) {
                this.slist = list;
                this.context = context;
                this.bigIndex = bigIndex;
                ;
            }
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(context).inflate(R.layout.commodity_layout, null);
                CommodityHolder holder = new CommodityHolder(view);
                return holder;
            }

            @Override
            public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
                if (holder instanceof CommodityHolder){
                    Glide.with(context).load(slist.get(position).getPic())
                            .placeholder(R.drawable.ic_launcher_background)
                            .error(R.drawable.ic_launcher_background)
                            .apply(RequestOptions.bitmapTransform(new RoundedCorners(30)))
                            .into(((CommodityHolder) holder).imageView);
                    ((CommodityHolder) holder).name.setText(slist.get(position).getCommodityName());
                    ((CommodityHolder) holder).price.setText(slist.get(position).getPrice()+"");
                    ((CommodityHolder) holder).myview.setNumber(slist.get(position).getCount());
                    ((CommodityHolder) holder).checkBox.setChecked(slist.get(position).getStatus());
                    ((CommodityHolder) holder).checkBox.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Boolean status = slist.get(position).getStatus();
                            mCallBack.smallCheckClick(bigIndex,position,status);
                        }
                    });
                    ((CommodityHolder) holder).myview.getClickCallBack(new AddAndRemoveView.ClickCallBack() {
                        @Override
                        public void getItemCount(int number) {
                            mCallBack.setSmallCheckCount(bigIndex,position,number);
                        }
                    });
                }
            }
            @Override
            public int getItemCount() {
                return slist.size();
            }
            class CommodityHolder extends RecyclerView.ViewHolder{
                ImageView imageView;
                TextView name,price;
                AddAndRemoveView myview;
                CheckBox checkBox;
                public CommodityHolder(@NonNull View itemView) {
                    super(itemView);
                    checkBox = itemView.findViewById(R.id.commodity_check);
                    imageView = itemView.findViewById(R.id.commodity_image);
                    name = itemView.findViewById(R.id.commodity_name);
                    price = itemView.findViewById(R.id.commodity_price);
                    myview = itemView.findViewById(R.id.commodity_myview);
                }
            }
        }

    }
    public interface ShopCallBack{
        void bigCheckClick(int bigindex);
        void smallCheckClick(int busIndex,int commpityIndex,boolean status);
        void setSmallCheckCount(int busIndex,int smallIndex,int count);

    }
    public static ShopCallBack mCallBack;
    public static void setShopCallBack(ShopCallBack callBack){
        mCallBack = callBack;
    }
    public void busInssStatus(int busInssindex){
        ShopCarBean.ResultBean resultBean = list.get(busInssindex);
        Boolean status = resultBean.getStatus();
        resultBean.setStatus(!status);
        for (int i = 0; i < resultBean.getShoppingCartList().size(); i++) {
            resultBean.getShoppingCartList().get(i).setStatus(!status);
        }

    }

    public void CommodityStatus(int bitIndex,int smallIndex,boolean status){
        boolean flag = true;
        list.get(bitIndex).getShoppingCartList().get(smallIndex).setStatus(!status);
        for (int i = 0; i < list.get(bitIndex).getShoppingCartList().size(); i++) {
            if (!list.get(bitIndex).getShoppingCartList().get(i).getStatus()){
                flag = false;
            }
        }
        list.get(bitIndex).setStatus(flag);
    }

    public void setShopCount(int bitIndex,int smallIndex,int count){
        list.get(bitIndex).getShoppingCartList().get(smallIndex).setCount(count);
    }
    public int CalculatedQantity(){
        int sum = 0;
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.get(i).getShoppingCartList().size(); j++) {
                if (list.get(i).getShoppingCartList().get(j).getStatus()){
                    sum+=list.get(i).getShoppingCartList().get(j).getCount();
                }
            }
        }
        return sum;
    }
    public int CalculatedTotalPrice(){
        int sum = 0;
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.get(i).getShoppingCartList().size(); j++) {
                if (list.get(i).getShoppingCartList().get(j).getStatus()){
                    sum+=(list.get(i).getShoppingCartList().get(j).getCount()*list.get(i).getShoppingCartList().get(j).getPrice());
                }
            }
        }
        return sum;
    }
    public List<ShopCarBean.ResultBean.ShoppingCartListBean> getDataForKnot(){
        List<ShopCarBean.ResultBean.ShoppingCartListBean> list_ForKnot = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.get(i).getShoppingCartList().size(); j++) {
                if (list.get(i).getShoppingCartList().get(j).getStatus()){
                    list_ForKnot.add(list.get(i).getShoppingCartList().get(j));
                }
            }
        }
        return list_ForKnot;
    }
}
