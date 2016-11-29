package com.example.administrator.quanmingzhibo.fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.administrator.quanmingzhibo.R;
import com.example.administrator.quanmingzhibo.adapter.WodeListviewAdapter;

import java.util.ArrayList;
import java.util.List;


public class WodeFragment extends Fragment {
    //
    View view;
    LinearLayout linearLayout;
    List<String> strings=new ArrayList<>();
    List<Integer> icons=new ArrayList<>();
    public WodeFragment() {

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_wode, container, false);
        ListView lv= (ListView) view.findViewById(R.id.wode_lv);
        linearLayout= (LinearLayout) view.findViewById(R.id.linert);
        initData();
        lv.setAdapter(new WodeListviewAdapter(icons,strings,getActivity()));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:

                        break;
                    case 1:
                        Intent inten=new Intent();


                        break;
                    case 2:

                        break;
                    case 3:

                        break;
                    case 4:

                        break;
                    case 5:

                        break;
                }
            }
        });

        return view;
    }

    private void initData() {
        strings.add("房间管理");
        strings.add("我的关注");
        strings.add("观看历史");
        strings.add("开播提醒");
        strings.add("种子任务");
        strings.add("游戏中心");

        icons.add(R.mipmap.benzhouqianli);
        icons.add(R.mipmap.bierenjiade);
        icons.add(R.mipmap.duzheliucun);
        icons.add(R.mipmap.fenlei);
        icons.add(R.mipmap.manhuazhongxin);
        icons.add(R.mipmap.nvsheng);

    }

}
