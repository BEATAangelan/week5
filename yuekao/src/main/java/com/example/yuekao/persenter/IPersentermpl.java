package com.example.yuekao.persenter;

import com.example.yuekao.model.IModelmpl;
import com.example.yuekao.utils.MCalBack;
import com.example.yuekao.view.IView;

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
