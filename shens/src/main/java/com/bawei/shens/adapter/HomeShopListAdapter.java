package com.bawei.shens.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.shens.R;
import com.bawei.shens.bean.HomeShopBean;

import java.util.List;

public class HomeShopListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private HomeShopBean.ResultBean resultBean;
    private Context context;

    public HomeShopListAdapter(HomeShopBean.ResultBean resultBean, Context context) {
        this.resultBean = resultBean;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0){
            View view = LayoutInflater.from(context).inflate(R.layout.rxxp_card, null);
            RxxpShopHolder holder = new RxxpShopHolder(view);
            return holder;
        }else if (viewType == 1){
            View view = LayoutInflater.from(context).inflate(R.layout.pzsh_card, null);
            PzshShopHolder holder = new PzshShopHolder(view);
            return holder;
        }else if (viewType == 2){
            View view = LayoutInflater.from(context).inflate(R.layout.mlss_card, null);
            MlssShopHolder holder = new MlssShopHolder(view);
            return holder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof RxxpShopHolder){
            ((RxxpShopHolder) holder).recyclerView.setLayoutManager(new LinearLayoutManager(context,RecyclerView.HORIZONTAL,false));
            List<HomeShopBean.ResultBean.RxxpBean.CommodityListBean> commodityList = resultBean.getRxxp().getCommodityList();
            PzshHomeAdapter pzshHomeAdapter = new PzshHomeAdapter(resultBean.getPzsh().getCommodityList(),context);
            ((RxxpShopHolder) holder).recyclerView.setAdapter(pzshHomeAdapter);
        }else if (holder instanceof PzshShopHolder){
            ((PzshShopHolder) holder).recyclerView.setLayoutManager(new LinearLayoutManager(context,RecyclerView.VERTICAL,false));
            PzshHomeAdapter pzshHomeAdapter = new PzshHomeAdapter(resultBean.getPzsh().getCommodityList(),context);
            ((PzshShopHolder) holder).recyclerView.setAdapter(pzshHomeAdapter);
        }else if (holder instanceof MlssShopHolder){
            ((MlssShopHolder) holder).recyclerView.setLayoutManager(new GridLayoutManager(context,2));
            ((MlssShopHolder) holder).recyclerView.setAdapter(new MlssHomeAdapter(resultBean.getMlss().getCommodityList(),context));
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    @Override
    public int getItemViewType(int position) {
        return position%3;
    }
    class PzshShopHolder extends RecyclerView.ViewHolder{
        RecyclerView recyclerView;
        public PzshShopHolder(@NonNull View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.pzsh_card);
        }
    }
    class RxxpShopHolder extends RecyclerView.ViewHolder{
        RecyclerView recyclerView;
        public RxxpShopHolder(@NonNull View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.rxxp_card);
        }
    }
    class MlssShopHolder extends RecyclerView.ViewHolder{
        RecyclerView recyclerView;
        public MlssShopHolder(@NonNull View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.mlss_card);
        }
    }
}
