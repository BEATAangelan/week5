package com.example.dell.week;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.week.bean.Add;
import com.example.dell.week.bean.ShopBean;
import com.example.dell.week.persenter.IPersentermpl;
import com.example.dell.week.view.IView;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tv.danmaku.ijk.media.player.IjkMediaPlayer;
import tv.danmaku.ijk.media.widget.media.AndroidMediaController;
import tv.danmaku.ijk.media.widget.media.IjkVideoView;

public class CarActivity extends AppCompatActivity implements IView {
    //查询购物车
   String cha="getProducts";
    //添加购物车
    String path="addCart";
    private int pid;
    TextView jia,biao;
    private Button button,kan;
    SimpleDraweeView simpleDraweeView;
    IPersentermpl iPersentermpl;
    private String title,price,image;
    private IjkVideoView video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);
        final Intent intent = getIntent();
        title = intent.getStringExtra("title");
        price = intent.getStringExtra("price");
        image = intent.getStringExtra("image");
        pid = intent.getIntExtra("pid", 0);
        jia=findViewById(R.id.jia);
        biao = findViewById(R.id.biao);
        kan = findViewById(R.id.kan);
        simpleDraweeView = findViewById(R.id.xiang);
        iPersentermpl = new IPersentermpl(this);
        button = findViewById(R.id.add);
        jia.setText(price);
        biao.setText(title);
        Uri uri = Uri.parse(image);
        simpleDraweeView.setImageURI(uri);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String,String> map=new HashMap<>();
                map.put("pid",pid+"");
                map.put("uid",71+"");
                iPersentermpl.stapostRequest(path,map,Add.class);
            }
        });
        kan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(CarActivity.this, ShowCar.class);
                startActivity(intent);
            }
        });
        video = (IjkVideoView) findViewById(R.id.video);
        IjkMediaPlayer.loadLibrariesOnce(null);
        IjkMediaPlayer.native_profileBegin("libijkplayer.so");

        AndroidMediaController controller=new AndroidMediaController(this,false);
        video.setMediaController(controller);
        String url="http://ips.ifeng.com/video19.ifeng.com/video09/2014/06/16/1989823-102-086-0009.mp4";
        video.setVideoURI(Uri.parse(url));
    }

    @Override
    public void setonSuccess(Object data) {
        Add bean= (Add) data;
        if(bean.getCode().equals("0")){
            Toast.makeText(this,bean.getMsg(),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void setonFail(String e) {

    }
}
