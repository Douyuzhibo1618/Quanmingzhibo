package com.example.administrator.quanmingzhibo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.GridView;
import android.widget.TextView;

import com.example.administrator.quanmingzhibo.adapter.ChangYongAdapter;
import com.example.administrator.quanmingzhibo.adapter.QiTaAdapter;

import java.util.ArrayList;
import java.util.List;

public class PinDaoManagerActivity extends AppCompatActivity {

    private TextView manager;
    private Toolbar toolbar;
    // 控件
    private GridView changyong,qita;
    // 数据源
    private List<String> changyonglist;
    private List<String> qitalist;
    //适配器
    private ChangYongAdapter changyongAdapter;
    private QiTaAdapter qitaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_dao_manager);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        manager = (TextView)findViewById(R.id.manager);
        changyong = (GridView)findViewById(R.id.changyongpindao);
        qita = (GridView)findViewById(R.id.qitapindao);
        //设置返回按钮
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        changyonglist = new ArrayList<String>();
        qitalist = new ArrayList<String>();


        changyongAdapter = new ChangYongAdapter(PinDaoManagerActivity.this,changyonglist);
        changyong.setAdapter(changyongAdapter);

        qitaAdapter = new QiTaAdapter(qitalist,PinDaoManagerActivity.this);
        qita.setAdapter(qitaAdapter);


    }


}
