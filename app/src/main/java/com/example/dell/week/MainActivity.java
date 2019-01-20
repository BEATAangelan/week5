package com.example.dell.week;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.dell.week.adapter.LeftAdaptr;
import com.example.dell.week.adapter.RightAdapter;
import com.example.dell.week.bean.LeftBean;
import com.example.dell.week.bean.RightBean;
import com.example.dell.week.persenter.IPersentermpl;
import com.example.dell.week.view.IView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements IView {
    private RecyclerView left_recycler,right_recycler;
    private LeftAdaptr leftAdaptr;
    private RightAdapter rightAdapter;
    private String path="getCatagory";
    private String rightpath="getProductCatagory?cid=%d";
    IPersentermpl iPersentermpl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化控件
        initView();
        //初始化list和adapter
        initListAndAdapter();
        //初始化OPresenter
        initPresenter();
        //adapter点击事件
        adapterOnClickListener();
    }

    //adapter点击事件
    private void adapterOnClickListener() {

        leftAdaptr.setOnClickisten(new LeftAdaptr.LeftListener() {
            @Override
            public void getDataa(int position, int cid) {
                getData(cid+"");
            }
        });

    }
    //初始化list和adapter
    private void initPresenter() {
        iPersentermpl=new IPersentermpl(this);
        iPersentermpl.stagetRequest(path,LeftBean.class);

    }

    //初始化list和adapter
    private void initListAndAdapter() {
        leftAdaptr=new LeftAdaptr(this);
        rightAdapter = new RightAdapter(this);

        left_recycler.setAdapter(leftAdaptr);
        right_recycler.setAdapter(rightAdapter);
    }

    //初始化控件
    private void initView() {
        right_recycler = findViewById(R.id.right_recycler);
        left_recycler = findViewById(R.id.left_recycler);

        /*LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);*/
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(this);
        linearLayoutManager2.setOrientation(LinearLayoutManager.VERTICAL);

        left_recycler.setLayoutManager(layoutManager);
        right_recycler.setLayoutManager(linearLayoutManager2);


    }

    private void getData(String cid) {
        HashMap<String,String>map = new HashMap<>();
        map.put("cid",cid);
        iPersentermpl.stapostRequest(rightpath,map,RightBean.class);
    }

    @Override
    public void setonSuccess(Object data) {
        if(data instanceof LeftBean){
            /*LeftBean bean= (LeftBean) data;
            List<LeftBean.DataBean> data1 = bean.getData();
            leftAdaptr.setList(data1);*/
            LeftBean leftBean = (LeftBean) data;
            String msg = leftBean.getMsg();
            Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
            List<LeftBean.DataBean> data1 = leftBean.getData();
            leftAdaptr.setList(data1);
        }
        if(data instanceof RightBean){
            RightBean bean= (RightBean) data;
            rightAdapter.setList(bean.getData());
        }
    }

    @Override
    public void setonFail(String e) {
        Log.i("TAG","aaaa");
        Toast.makeText(this,e,Toast.LENGTH_SHORT).show();
    }
}
