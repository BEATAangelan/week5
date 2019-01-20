package com.example.dell.week.myview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.dell.week.R;

public class SearchView extends LinearLayout {
    Context context;
    Button button_miao,bar_s;
    EditText editSearch;
    public SearchView(Context context) {
        super(context);
        initView();
    }

    public SearchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public SearchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }
    public void initView(){
        View view = View.inflate(context, R.layout.item, null);
        editSearch = view.findViewById(R.id.editSearch);
        button_miao =view.findViewById(R.id.button_miao);
        bar_s = view.findViewById(R.id.button_search);
        bar_s.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                monClickCallBack.getdata(editSearch.getText().toString());
            }
        });
        addView(view);
    }
    //定义一个接口回调
    public void setOnClickCallBack(OnClickCallBack onClickCallBack){
        monClickCallBack=onClickCallBack;
    }

   OnClickCallBack  monClickCallBack;
    public interface OnClickCallBack{
        void getdata(String str);
    }
}
