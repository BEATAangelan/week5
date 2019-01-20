package com.example.dell.week.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dell.week.R;
import com.example.dell.week.ShowActivity;
import com.example.dell.week.bean.RightBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class GoodsAdapter extends RecyclerView.Adapter<GoodsAdapter.ViewHolder> {
    Context context;
    private List<RightBean.DataBean.ListBean> list;

    public GoodsAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    public void setList(List<RightBean.DataBean.ListBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public GoodsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LinearLayout.inflate(context, R.layout.goods_layout, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GoodsAdapter.ViewHolder viewHolder, int i) {
      viewHolder.text_goods.setText(list.get(i).getName());
        Uri uri = Uri.parse(list.get(i).getIcon());
        viewHolder.image_goods.setImageURI(uri);
        viewHolder.text_goods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ShowActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView image_goods;
        TextView text_goods;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image_goods = itemView.findViewById(R.id.image_goods);
            text_goods = itemView.findViewById(R.id.text_goods);
        }
    }
}
