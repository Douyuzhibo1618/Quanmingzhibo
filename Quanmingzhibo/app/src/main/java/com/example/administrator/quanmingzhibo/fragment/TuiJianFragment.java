package com.example.administrator.quanmingzhibo.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.quanmingzhibo.R;
import com.example.administrator.quanmingzhibo.adapter.TuiJianAdapter;
import com.example.administrator.quanmingzhibo.bean.TuiJianBean;
import com.example.administrator.quanmingzhibo.widget.GlideImageLoader;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 */
public class TuiJianFragment extends Fragment {

  //  private ViewPager viewPager;
   // private MyAdapter adapter;//ViewPager 的适配器

   private PullToRefreshListView listView;

   private Banner banner;
   // private int[] imageurls = {R.mipmap.aa,R.mipmap.bb,R.mipmap.cc,R.mipmap.dd,R.mipmap.ee};

    private  List<Integer> imageurls = new ArrayList<>();
    private List<TuiJianBean.RoomBean> listdata = new ArrayList<>();//listView的数据
    private TuiJianAdapter tuijianAdapter;
    private View headview,view;

    public TuiJianFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_tui_jian, container, false);

       headview = LayoutInflater.from(getActivity()).inflate(R.layout.tuijianahead,null);
        banner = (Banner) headview.findViewById(R.id.banner);
       // viewPager = (ViewPager)view.findViewById(R.id.viewpager);
         listView=(PullToRefreshListView)view.findViewById(R.id.listview);

     //   adapter = new MyAdapter(imageurls, getActivity());

     //  viewPager.setAdapter(adapter);

        imageurls.add(R.mipmap.aa);
        imageurls.add(R.mipmap.bb);
        imageurls.add(R.mipmap.cc);
        imageurls.add(R.mipmap.dd);
        imageurls.add(R.mipmap.ee);


        initBanner();
        //添加头视图
        listView.getRefreshableView().addHeaderView(banner);

         initData();//给listView赋值
       // tuijianAdapter = new TuiJianAdapter(getActivity(),listdata);
        listView.setAdapter(tuijianAdapter);

        return view;
    }

    private void initBanner() {


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

        banner.setDelayTime(1500);

        //设置指示器位置（当banner模式中有指示器时）

        banner.setIndicatorGravity(BannerConfig.CENTER);

        //banner设置方法全部调用完毕时最后调用

        banner.start();

    }

    private void initData() {
       /* //网络请求 数据
        RequestParams params = new RequestParams(Url.Tuijian);
        x.http().get(params, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                listdata.addAll(new Gson().fromJson(result,TuiJianBean.class).getRoom());
                tuijianAdapter.setData(listdata);
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
        });*/

        Retrofit retrofit =  new Retrofit.Builder()
                                .baseUrl("")
                                    .build();
    }


}



