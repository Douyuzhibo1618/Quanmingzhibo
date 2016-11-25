package com.example.administrator.quanmingzhibo.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.quanmingzhibo.R;
import com.example.administrator.quanmingzhibo.adapter.YanZhiKongAdapter;
import com.example.administrator.quanmingzhibo.bean.YanZhiKong;
import com.example.administrator.quanmingzhibo.uri.Url;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;


public class YanZhiKongFragment extends Fragment {


    private PullToRefreshGridView gv;

    private List<YanZhiKong.DataBean> listData;

    private YanZhiKongAdapter adapter;

    public YanZhiKongFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_yan_zhi_kong, container, false);
        gv = (PullToRefreshGridView)view.findViewById(R.id.gridview);
        listData = new ArrayList<YanZhiKong.DataBean>();
        initData();
        adapter = new YanZhiKongAdapter(getActivity(),listData);
        gv.setAdapter(adapter);
        return  view;
    }

    private void initData() {
        RequestParams params = new RequestParams(Url.Yanzhi);
        x.http().get(params, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                listData.addAll(new Gson().fromJson(result,YanZhiKong.class).getData());
                adapter.setData(listData);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

}
