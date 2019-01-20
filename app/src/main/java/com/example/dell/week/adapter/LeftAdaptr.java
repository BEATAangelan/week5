package com.example.dell.week.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dell.week.R;
import com.example.dell.week.bean.LeftBean;

import java.util.ArrayList;
import java.util.List;

public class LeftAdaptr extends RecyclerView.Adapter<LeftAdaptr.ViewHolder> {
    Context context;
    List<LeftBean.DataBean> list;

    public LeftAdaptr(Context context) {
        this.context = context;
        list=new ArrayList<>();
    }

    public void setList(List<LeftBean.DataBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public LeftAdaptr.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LinearLayout.inflate(context, R.layout.left_layout, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
     viewHolder.text.setText(list.get(i).getName());
     viewHolder.text.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             if(mLeftListen!=null) {
                 mLeftListen.getDataa(i, list.get(i).getCid());
             }
         }
     });
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView text;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.left_title);
        }
    }
    private LeftListener mLeftListen;
    public interface LeftListener{
        void getDataa(int position,int cid);
    }
    public void setOnClickisten(LeftListener lftListen){
        mLeftListen=lftListen;
    }
}
