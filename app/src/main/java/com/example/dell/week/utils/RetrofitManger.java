package com.example.dell.week.utils;

import android.util.Log;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RetrofitManger {
    private static volatile RetrofitManger retrofitManger;
    private static String BASE_URL="http://www.zhaoapi.cn/product/";
    private BaseApis baseApis;

    //单例
    public static synchronized RetrofitManger getRetrofitManger(){
        if(retrofitManger==null){
            retrofitManger=new RetrofitManger();
        }
            return retrofitManger;
    }
    private RetrofitManger(){
       /* HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);*/
        // 新建日志拦截器
        HttpLoggingInterceptor.Level level = HttpLoggingInterceptor.Level.BODY;
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.i("http日志拦截器打印的信息:", "log: " + message);
            }
        });
        httpLoggingInterceptor.setLevel(level);
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        OkHttpClient okHttpClient = builder.connectTimeout(5000, TimeUnit.SECONDS)
                .readTimeout(5000, TimeUnit.SECONDS)
                .writeTimeout(5000, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        baseApis = retrofit.create(BaseApis.class);
    }
    //get请求
    public RetrofitManger getRequest(String url,Class clazz,HttpListen httpListen){
        baseApis.get(url)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(getobserver(httpListen));
        return retrofitManger;
    }
    //post请求
    public RetrofitManger postRequest(String url, Map<String,String> map,Class clazz,HttpListen httpListen){
        baseApis.post(url,map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(getobserver(httpListen));
        return retrofitManger;
    }
    //观察者模式
    private Observer getobserver(final HttpListen httpListen){
        Observer<ResponseBody> observer = new Observer<ResponseBody>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                if(httpListen!=null){
                    httpListen.onFail(String.valueOf(e));
                }
            }

            @Override
            public void onNext(ResponseBody responseBody) {

                  try {
                      String s = responseBody.string();
                      if(httpListen!=null){
                          httpListen.onSueecess(s);
                      }
                  } catch (IOException e) {
                      e.printStackTrace();
                  }

            }
        };
        return observer;
    }
   //接口
   public interface HttpListen{
        void onSueecess(String data);
        void onFail(String e);
    }
}
