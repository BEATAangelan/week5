package com.example.dell.week.model;

import com.example.dell.week.utils.MCalBack;
import com.example.dell.week.utils.RetrofitManger;
import com.google.gson.Gson;

import java.util.Map;

public class IModelmpl implements IModel{

    @Override
    public void startgetRequest(String url, final Class clazz, final MCalBack calBack) {
        RetrofitManger.getRetrofitManger().getRequest(url, clazz, new RetrofitManger.HttpListen() {
            @Override
            public void onSueecess(String data) {
                try {
                    Object o = new Gson().fromJson(data, clazz);
                    if (calBack != null) {
                        calBack.setdata(o);
                    }
                }
                catch (Exception e){
                        e.printStackTrace();
                        if(calBack != null){
                            calBack.onFail(e.getMessage());
                        }
                    }
            }

            @Override
            public void onFail(String e) {
                calBack.onFail(e);
            }
        });
    }

    @Override
    public void startpostRequest(String url, Map<String, String> map, final Class clazz, final MCalBack calBack) {
        RetrofitManger.getRetrofitManger().postRequest(url, map,clazz, new RetrofitManger.HttpListen() {
            @Override
            public void onSueecess(String data) {
                try {
                    Object o = new Gson().fromJson(data, clazz);
                    if (calBack != null) {
                        calBack.setdata(o);
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                    if(calBack != null){
                        calBack.onFail(e.getMessage());
                    }
                }
            }

            @Override
            public void onFail(String e) {
                calBack.onFail(e);
            }
        });
    }
}
