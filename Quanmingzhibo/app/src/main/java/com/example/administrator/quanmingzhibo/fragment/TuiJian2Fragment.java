package com.example.administrator.quanmingzhibo.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

import com.example.administrator.quanmingzhibo.R;
import com.example.administrator.quanmingzhibo.adapter.TuiJianAdapter;
import com.example.administrator.quanmingzhibo.bean.TuiJianBean;
import com.example.administrator.quanmingzhibo.uri.Url;
import com.example.administrator.quanmingzhibo.widget.GlideImageLoader;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TuiJian2Fragment extends Fragment {

    private PullToRefreshListView listView;
    private View view, headView;
    private Banner banner;
    private List<Integer> imageurls = new ArrayList<>();//banner   的数据源
    private List<TuiJianBean.RoomBean> listdata = new ArrayList<>();//listView的数据
    private TuiJianAdapter tuijianAdapter;

    private List<Integer>  idlist = new ArrayList<>();


    public TuiJian2Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_tui_jian2, container, false);

        //控件
        headView = LayoutInflater.from(getActivity()).inflate(R.layout.tuijianahead,null);
        headView.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT,200));
        banner = (Banner) headView.findViewById(R.id.banner);
        listView=(com.handmark.pulltorefresh.library.PullToRefreshListView)view.findViewById(R.id.tuijian_listView);

        //控件填值
        initbanner();
        initData();//给listView赋值

     // 添加头视图
       listView.getRefreshableView().addHeaderView(headView);
        tuijianAdapter = new TuiJianAdapter(getActivity(),listdata,idlist);
        listView.setAdapter(tuijianAdapter);



        return  view;
    }

    private void initData() {

        //网络请求 数据
        RequestParams params = new RequestParams(Url.Tuijian);
        Log.e("info","+++"+params);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("INFO","onSuccess");
                TuiJianBean tuiJianBean = new Gson().fromJson(result, TuiJianBean.class);
                List<TuiJianBean.RoomBean> room = tuiJianBean.getRoom();

                listdata.addAll(room);
               // listdata.addAll(new Gson().fromJson(result,TuiJianBean.class).getRoom());
                for (int i = 0; i < room.size(); i++) {
                    int id = room.get(i).getId();
                    idlist.add(id);
                }
                tuijianAdapter.setData(listdata);
                Log.e("==========",result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
            Log.e("INFO","+++"+ex.toString());
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.e("INFO","+++"+cex.toString());

            }

            @Override
            public void onFinished() {
                Log.e("INFO","onFinished");

            }
        });
//
//
//         /* //精彩推荐。。颜值控等（6个）
//    public static String Tuijian = "http://www.quanmin.tv/json/app/index/recommend/list-android.json?11211748&v=2.2.4&os=1&ver=4";
//*/
//
//        Retrofit retrofit = new Retrofit.Builder()
//                                        .baseUrl("http://www.quanmin.tv/")
//                                        .addConverterFactory(GsonConverterFactory.create())
//                                        .build();
//        MyRequestInterface myRequestInterface = retrofit.create(MyRequestInterface.class);
//        Call<TuiJianBean.RoomBean> call = myRequestInterface.getData("2.2.4","1","4");
//       call.enqueue(new Callback<TuiJianBean.RoomBean>() {
//            @Override
//            public void onResponse(Call<TuiJianBean.RoomBean> call, Response<TuiJianBean.RoomBean> response) {
//
//            }
//
//            @Override
//            public void onFailure(Call<TuiJianBean.RoomBean> call, Throwable t) {
//
//            }
//        });
    }

    private void initbanner() {

        //banner 的数据源
        imageurls.add(R.mipmap.aa);
        imageurls.add(R.mipmap.bb);
        imageurls.add(R.mipmap.cc);
        imageurls.add(R.mipmap.dd);
        imageurls.add(R.mipmap.ee);

        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(imageurls);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(2000);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner.start();

    }


}
