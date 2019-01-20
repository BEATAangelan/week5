package com.example.dell.week.model;

import com.example.dell.week.utils.MCalBack;

import java.util.Map;

public interface IModel {
    //get请求
    void startgetRequest(String url, Class clazz, MCalBack calBack);
    //post请求
    void startpostRequest(String url, Map<String,String> map, Class clazz, MCalBack calBack);
}
