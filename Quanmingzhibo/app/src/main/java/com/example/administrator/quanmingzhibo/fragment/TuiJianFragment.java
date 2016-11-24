package com.example.administrator.quanmingzhibo.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.quanmingzhibo.R;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

/**
 * A simple {@link Fragment} subclass.
 */
public class TuiJianFragment extends Fragment {
    private ViewPager viewpager;
    private PullToRefreshListView listView;

    public TuiJianFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tui_jian, container, false);
        viewpager = (ViewPager)view.findViewById(R.id.viewpager);
        listView=(PullToRefreshListView)view.findViewById(R.id.listview);
         listView.getRefreshableView().addHeaderView(viewpager);

    }

}
