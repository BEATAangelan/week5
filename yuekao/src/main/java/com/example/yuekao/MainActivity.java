package com.example.yuekao;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.yuekao.bean.Login;
import com.example.yuekao.persenter.IPersentermpl;
import com.example.yuekao.view.IView;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements IView {
    EditText edit_phone,edit_pwd;
    private Button button,qqbutton;
    IPersentermpl iPersentermpl;
    String path="login";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edit_phone = findViewById(R.id.edit_deng);
        edit_pwd = findViewById(R.id.edit_pwd);
        qqbutton = findViewById(R.id.qqdl);
        button = findViewById(R.id.login);
        intnet();
    }
   //网络请求
    private void intnet(){
        iPersentermpl=new IPersentermpl(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String,String> map=new HashMap<>();
                map.put("mobile",edit_phone.getText().toString());
                map.put("password",edit_pwd.getText().toString());
                iPersentermpl.stapostRequest(path,map,Login.class);
//                iPersentermpl.stagetRequest("login?mobile=15714106460&password=123456",Login.class);
            }
        });

    }
    @Override
    public void setonSuccess(Object data) {
        Login bean= (Login) data;
        if(bean.getMsg().equals("登录成功")){
            Toast.makeText(this,"登录成功",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, ShowActivity.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(this,"登陆失败",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void setonFail(String e) {

    }
}
