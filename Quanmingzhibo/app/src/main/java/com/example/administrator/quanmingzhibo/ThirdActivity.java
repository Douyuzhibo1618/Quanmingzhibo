package com.example.administrator.quanmingzhibo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.VideoView;

import com.example.administrator.quanmingzhibo.uri.Url;

import cn.com.video.venvy.param.JjVideoView;

public class ThirdActivity extends AppCompatActivity {
    private String key = "HkvWDMVGx";
    private JjVideoView videoView;
    private VideoView videoView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        videoView = (JjVideoView) findViewById(R.id.jjvideoView);
//        videoView1 = (VideoView) findViewById(R.id.videoView);
        String uid = getIntent().getStringExtra("uid");
        String path = Url.getZhiboPath(uid);
        //1设置key
        videoView.setVideoJjAppKey(key);
        //设置报名
        videoView.setVideoJjPageName("com.example.administrator.quanmingzhibo");
        //设置类型
        videoView.setVideoJjType(3);
        //设置路径
        videoView.setResourceVideo(path);

//        videoView1.setVideoPath(path);
//        videoView1.setMediaController(new MediaController(this));
//        videoView1.start();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
