package com.example.dell.week;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.dell.week.adapter.SHopAdapter;
import com.example.dell.week.bean.ShopBean;
import com.example.dell.week.persenter.IPersentermpl;
import com.example.dell.week.view.IView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShowActivity extends AppCompatActivity implements IView {
    private RecyclerView recycler_z;
    private SHopAdapter adapter;
    IPersentermpl iPersentermpl;
    String path="getProducts";
    private List<ShopBean.DataBean> data1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        recycler_z = findViewById(R.id.recycler_z);
        iPersentermpl=new IPersentermpl(this);
        init();
    }
    private void init(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        adapter = new SHopAdapter(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        Map<String,String> map=new HashMap<>();
        map.put("pscid",40+"");
        iPersentermpl.stapostRequest(path,map,ShopBean.class);
        recycler_z.setAdapter(adapter);
        recycler_z.setLayoutManager(layoutManager);
        adapter.setOnClickL(new SHopAdapter.OnClickListener() {
            @Override
            public void getdata(int pid, String title, String price, String image) {
                Intent intent = new Intent(ShowActivity.this, CarActivity.class);
                intent.putExtra("title",title);
                intent.putExtra("image",image);
                intent.putExtra("price",price);
                intent.putExtra("pid",pid);
                startActivity(intent);
            }
        });
    }

    @Override
    public void setonSuccess(Object data) {
        ShopBean bean= (ShopBean) data;
        data1 = bean.getData();
        adapter.setList(data1);
    }

    @Override
    public void setonFail(String e) {

    }
}
