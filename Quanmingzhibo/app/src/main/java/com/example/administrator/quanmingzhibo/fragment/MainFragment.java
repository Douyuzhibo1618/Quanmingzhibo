package com.example.administrator.quanmingzhibo.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.administrator.quanmingzhibo.PinDaoManagerActivity;
import com.example.administrator.quanmingzhibo.R;
import com.example.administrator.quanmingzhibo.adapter.MainViewPagerAdapter;
import com.example.administrator.quanmingzhibo.adapter.MyAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    private TabLayout tabLayout;
    private Button more;
    private ViewPager viewpager;
    private List<Fragment> fragments;
    private List<String> title;
    private List<String> realtitle;
    private MyAdapter adapter;
    private View view;


    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_main, container, false);
//        //控件
       tabLayout = ((TabLayout) view.findViewById(R.id.tablayout));
        viewpager = ((ViewPager) view.findViewById(R.id.viewpager));
       more = ((Button)view.findViewById(R.id.more));

        //初始化数据
        initData();
//
//        //按钮的点击事件
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(getActivity(),PinDaoManagerActivity.class);
                getActivity().startActivity(intent);
            }
        });

        return view;
    }

    private void initData() {
        fragments = new ArrayList<>();
        title = new ArrayList<>();
        realtitle = new ArrayList<>();


        title.add("推荐");
        title.add("颜值控");
        title.add(0,"英雄联盟");
        title.add(1,"全民星秀");
        title.add(2,"炉石传说");
        title.add(3,"守望先锋");
        title.add(4,"二次元区");

        realtitle.add(0,"lol");
        realtitle.add(1,"beauty");
        realtitle.add(2,"heartstone");
        realtitle.add(3,"overwatch");
        realtitle.add(4,"erciyuan");

        for (int i = 0; i < title.size(); i++) {
            tabLayout.addTab(tabLayout.newTab().setText(title.get(i)));
        }

        fragments.add(new TuiJian2Fragment());
        fragments.add(new YanZhiKongFragment());
        for (int i = 0; i < 5; i++) {
            OtherFragment otherFragment = new OtherFragment();
            Bundle bundle = new Bundle();
            bundle.putString("name",realtitle.get(i));
            otherFragment.setArguments(bundle);
            fragments.add(otherFragment);
        }
        viewpager.setAdapter(new MainViewPagerAdapter(getActivity().getSupportFragmentManager(),fragments,title));
        //tabLayout与Viewpager关联
        tabLayout.setupWithViewPager(viewpager);
    }

}
