package com.example.administrator.quanmingzhibo.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.quanmingzhibo.R;
import com.example.administrator.quanmingzhibo.adapter.OtherAdapter;
import com.example.administrator.quanmingzhibo.bean.OtherBean;
import com.example.administrator.quanmingzhibo.uri.Url;
import com.example.administrator.quanmingzhibo.widget.PullToRefreshRecyclerView;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class OtherFragment extends Fragment {

    private PullToRefreshRecyclerView pullToRefreshRecyclerView;
    private RecyclerView mRecyclerView;
    private List<OtherBean.DataBean>  listData;
    private OtherAdapter adapter;
    private String name;

    public OtherFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_other, container, false);
        pullToRefreshRecyclerView= (PullToRefreshRecyclerView)view.findViewById(R.id.pullToRefreshRecyclerView);
      //得到刷新组件
        mRecyclerView = pullToRefreshRecyclerView.getRefreshableView();
        listData = new ArrayList<>();
         name =  getArguments().getString("name");
        initData();

        adapter = new OtherAdapter(getActivity(),listData);
        mRecyclerView.setAdapter(adapter);

        return  view;
    }

    private void initData() {
        RequestParams params = new RequestParams(Url.needurl(name));
        x.http().get(params, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                listData.addAll(new Gson().fromJson(result,OtherBean.class).getData());
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
