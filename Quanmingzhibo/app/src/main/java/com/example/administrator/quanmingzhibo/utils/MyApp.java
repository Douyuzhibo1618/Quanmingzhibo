package com.example.administrator.quanmingzhibo.utils;

import android.app.Application;

import org.xutils.x;

/**
 * Created by imp on 2016/11/28.
 */
public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
    }
}
