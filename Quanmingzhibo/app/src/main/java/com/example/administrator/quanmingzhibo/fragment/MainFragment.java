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


    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        //控件
        tabLayout = ((TabLayout) view.findViewById(R.id.tablayout));
        viewpager = ((ViewPager) view.findViewById(R.id.viewpager));
        more = ((Button)view.findViewById(R.id.more));
        //
        fragments = new ArrayList<Fragment>();
        title = new ArrayList<String>();
        realtitle = new ArrayList<>();
        //初始化数据
        initData();
        viewpager.setAdapter(new MainViewPagerAdapter(getChildFragmentManager(),fragments,title));
        //tabLayout与Viewpager关联
        tabLayout.setupWithViewPager(viewpager);

        //按钮的点击事件
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
        title.add("推荐");
        title.add("全部");
        title.add("英雄联盟");
        title.add("全民星秀");
        title.add("炉石传说");
        title.add("守望先锋");
        title.add("二次元区");

        realtitle.add("all");
        realtitle.add("lol");
        realtitle.add("beauty");
        realtitle.add("heartstone");
        realtitle.add("overwatch");
        realtitle.add("erciyuan");

        fragments.add(new TuiJianFragment());
        fragments.add(new YanZhiKongFragment());
        for (int i = 0; i < 6; i++) {
            OtherFragment otherFragment = new OtherFragment();
            Bundle bundle = new Bundle();
            bundle.putString("name",realtitle.get(i));
            otherFragment.setArguments(bundle);
            fragments.add(otherFragment);

        }
    }

}
