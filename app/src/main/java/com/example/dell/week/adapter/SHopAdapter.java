package com.example.dell.week.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dell.week.R;
import com.example.dell.week.bean.ShopBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

public class SHopAdapter extends RecyclerView.Adapter<SHopAdapter.ViewHolder> {
    Context context;
    List<ShopBean.DataBean> list;

    public SHopAdapter(Context context) {
        this.context = context;
        list=new ArrayList<>();
    }

    public void setList(List<ShopBean.DataBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SHopAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LinearLayout.inflate(context, R.layout.item_layout, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SHopAdapter.ViewHolder viewHolder, final int i) {
        final String url = list.get(i).getImages().split("\\|")[0].replace("https", "http");
        viewHolder.ti.setText(list.get(i).getTitle());
        viewHolder.price.setText(list.get(i).getPrice()+"");
        Uri uri = Uri.parse(url);
        viewHolder.simpleDraweeView.setImageURI(uri);
        viewHolder.simpleDraweeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(monClickListener!=null){
                    monClickListener.getdata(list.get(i).getPid(),list.get(i).getTitle(),list.get(i).getPrice()+"",url);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView ti,price;
        SimpleDraweeView simpleDraweeView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            simpleDraweeView = itemView.findViewById(R.id.img);
            ti = itemView.findViewById(R.id.ti);
            price = itemView.findViewById(R.id.price);
        }
    }
    OnClickListener monClickListener;
    public interface OnClickListener{
        void getdata(int pid,String title,String price,String image);
    }
    public void setOnClickL(OnClickListener onClickListener){
        monClickListener=onClickListener;
    }
}
