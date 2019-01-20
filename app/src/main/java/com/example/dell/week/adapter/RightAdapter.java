package com.example.dell.week.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dell.week.R;
import com.example.dell.week.bean.RightBean;

import java.util.ArrayList;
import java.util.List;

public class RightAdapter extends RecyclerView.Adapter<RightAdapter.ViewHolder> {
    private Context context;
    private List<RightBean.DataBean> list;

    public RightAdapter(Context context) {
        this.context = context;
        list=new ArrayList<>();
    }

    public void setList(List<RightBean.DataBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RightAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LinearLayout.inflate(context, R.layout.right_layout, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RightAdapter.ViewHolder viewHolder, int i) {
       viewHolder.right_text.setText(list.get(i).getName());
        GoodsAdapter goodsAdapter = new GoodsAdapter(context);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        viewHolder.goods_recycler.setLayoutManager(gridLayoutManager);
        viewHolder.goods_recycler.setAdapter(goodsAdapter);
        goodsAdapter.setList(list.get(i).getList());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RecyclerView goods_recycler;
        TextView right_text;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            goods_recycler = itemView.findViewById(R.id.goods_recycler);
            right_text = itemView.findViewById(R.id.right_text);
        }
    }
}
