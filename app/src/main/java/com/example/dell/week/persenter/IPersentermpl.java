package com.example.dell.week.persenter;

import android.app.AlertDialog;

import com.example.dell.week.model.IModelmpl;
import com.example.dell.week.utils.MCalBack;
import com.example.dell.week.view.IView;

import java.util.Map;

public class IPersentermpl implements IPersenter {
    IModelmpl miModelmpl;
    IView miView;
    public IPersentermpl(IView iView){
        miModelmpl=new IModelmpl();
        miView=iView;
    }
    @Override
    public void stagetRequest(String url, Class clazz) {
        miModelmpl.startgetRequest(url, clazz, new MCalBack() {
            @Override
            public void setdata(Object data) {
                miView.setonSuccess(data);
            }

            @Override
            public void onFail(String error) {
               miView.setonFail(error);
            }
        });
    }

    @Override
    public void stapostRequest(String url, Map<String, String> map, Class clazz) {
        miModelmpl.startpostRequest(url,map,clazz, new MCalBack() {
            @Override
            public void setdata(Object data) {
                miView.setonSuccess(data);
            }

            @Override
            public void onFail(String error) {
                miView.setonFail(error);
            }
        });
    }
}
