package com.example.administrator.quanmingzhibo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by imp on 2016/11/22.
 */
public class MainViewPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> data;
    private List<String> title;

    public MainViewPagerAdapter(FragmentManager fm, List<Fragment> data, List<String> title) {
        super(fm);
        this.data = data;
        this.title = title;
    }

    public MainViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return data.get(position);
    }

    @Override
    public int getCount() {
        return data != null ?data.size():0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title.get(position);
    }
}
