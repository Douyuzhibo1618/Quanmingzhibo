package com.example.administrator.quanmingzhibo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.administrator.quanmingzhibo.adapter.SecondRecycleAdapter;
import com.example.administrator.quanmingzhibo.bean.SecondBean;
import com.example.administrator.quanmingzhibo.callback.LanmuCallBack;
import com.example.administrator.quanmingzhibo.http.PublicHttp;
import com.example.administrator.quanmingzhibo.json.SecondJson;
import com.example.administrator.quanmingzhibo.uri.Url;

import java.util.List;

public class SecondActivity extends AppCompatActivity {
    private List<SecondBean>list;
    private RecyclerView recyclerView;
    private Toolbar toolbar;
    private SecondRecycleAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        recyclerView = (RecyclerView) findViewById(R.id.second_recycle);
        toolbar = (Toolbar) findViewById(R.id.second_toolbar);
        String slug = getIntent().getStringExtra("slug");
        String name = getIntent().getStringExtra("name");
        toolbar.setTitle(name);
        setSupportActionBar(toolbar);


        //获得地址
        String path = Url.getLanmuPath(slug);
        //数据
        new PublicHttp(new LanmuCallBack() {
            @Override
            public void getData(byte[] bytes) {
                //数据
                String jsonStr = new String(bytes);
                list = SecondJson.getData(jsonStr);
                //adapter
                adapter = new SecondRecycleAdapter(SecondActivity.this,list);
                recyclerView.setAdapter(adapter);
                //管理者
                GridLayoutManager manager = new GridLayoutManager(SecondActivity.this,2);
                manager.setOrientation(GridLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(manager);
            }
        }).execute(path);

    }
}
