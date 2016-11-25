package com.example.administrator.quanmingzhibo.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.example.administrator.quanmingzhibo.R;
import com.example.administrator.quanmingzhibo.adapter.MyAdapter;
import com.example.administrator.quanmingzhibo.adapter.TuiJianAdapter;
import com.example.administrator.quanmingzhibo.bean.TuiJianBean;
import com.example.administrator.quanmingzhibo.uri.Url;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class TuiJianFragment extends Fragment {

    private ViewPager viewPager;
    private PullToRefreshListView listView;
    private MyAdapter adapter;//ViewPager 的适配器
    private RelativeLayout relativelayout;
    private RadioGroup rg;
    private Timer timer;

    private List<TuiJianBean.RoomBean> listdata;//listView的数据
    private TuiJianAdapter tuijianAdapter;

    public TuiJianFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tui_jian, container, false);
        relativelayout = ((RelativeLayout) view.findViewById(R.id.relativelayout));
        rg = ((RadioGroup) view.findViewById(R.id.rg));
        viewPager = (ViewPager)view.findViewById(R.id.viewpager);
        listView=(PullToRefreshListView)view.findViewById(R.id.listview);


        initRadioButton();  //往RadioGroup内部动态添加小点
        initListener();   //初始化监听事件

        adapter = new MyAdapter(imageurls, getActivity());

        viewPager.setAdapter(adapter);


        timer = new Timer();
		/*
		 * 1.被关闭过的timer  就不能使用他再次开启
		 * 2.TimerTask 用过了，就不能再用！
		 *
		 */
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                getActivity().runOnUiThread(new Runnable() {
                    // 主线程
                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        int currentItem = viewPager.getCurrentItem(); // 得到ViewPager当前显示第几页

                        int count = adapter.getCount(); // 总共有多少条

                        // 0123 4
                        if (currentItem == (count - 1)) {
                            // 如果使用第二个参数，单条显示Fragment数据可能无法加载！
                            viewPager.setCurrentItem(0);
                        } else {
                            currentItem++;
                            viewPager.setCurrentItem(currentItem);
                        }
                    }
                });
            }
        }, 2000, 2000);


        //触摸监听
        viewPager.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                // 获取触摸动作
                int action  = event.getAction();

                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        //按下
                        //停止定时器
                        timer.cancel(); //关闭
                        break;
                    case MotionEvent.ACTION_UP:
                        //抬起
                        //开启定时器
                        timer = new Timer(); //新创建一个对象
                        timer.schedule(new TimerTask() {

                            @Override
                            public void run() {
                                // TODO Auto-generated method stub

                                getActivity().runOnUiThread(new Runnable() {
                                    // 主线程
                                    @Override
                                    public void run() {
                                        // TODO Auto-generated method stub
                                        int currentItem = viewPager.getCurrentItem(); // 得到ViewPager当前显示第几页

                                        int count = adapter.getCount(); // 总共有多少条

                                        // 0123 4
                                        if (currentItem == (count - 1)) {
                                            // 如果使用第二个参数，单条显示Fragment数据可能无法加载！
                                            viewPager.setCurrentItem(0);
                                        } else {
                                            currentItem++;
                                            viewPager.setCurrentItem(currentItem);
                                        }
                                    }
                                });
                            }
                        }, 2000, 2000);

                        break;
                }

                return false;
            }
        });
        //添加头视图
        listView.getRefreshableView().addHeaderView(relativelayout);

        initData();//给listView赋值
        tuijianAdapter = new TuiJianAdapter(getActivity(),listdata);
        listView.setAdapter(tuijianAdapter);

        return view;
    }

    private void initData() {
        //网络请求 数据
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
        });
    }

    private void initListener() {
        // TODO Auto-generated method stub
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            //改变后是第几页
            @Override
            public void onPageSelected(int arg0) {
                // TODO Auto-generated method stub

                ((RadioButton)rg.getChildAt(arg0)).setChecked(true);

            }

            //获取拉取偏移量
            @Override
            public void onPageScrolled(int position, float arg1, int arg2) {
                // TODO Auto-generated method stub

            }

            //状态改变
            @Override
            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub

            }
        });


        //点击RadioButton 把ViewPager切换到当前的页面
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub

                //选中的是第几个RadioButton

                for (int i = 0; i < group.getChildCount(); i++) {
                    //得到子控件
                    RadioButton button = (RadioButton) group.getChildAt(i);

                    if (button.isChecked()) {

                        //把选中的位置，设置为ViewPager显示的位置
                        viewPager.setCurrentItem(i);

                        break;
                    }
                }
            }
        });
    }


    private void initRadioButton() {
        // TODO Auto-generated method stub

        for (int i = 0; i < imageurls.length; i++) {
            //添加点
            RadioButton  button = new  RadioButton(getActivity());
            button.setLayoutParams(new RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT));
            rg.addView(button);
            if (i==0) {
                button.setChecked(true);  //第一个默认选中
            }
        }
    }

    public String imageurls[] = new String[] {
            "http://img.my.csdn.net/uploads/201407/26/1406383299_1976.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406383291_6518.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406383291_8239.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406383290_9329.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406383290_1042.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406383275_3977.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406383265_8550.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406383264_3954.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406383264_4787.jpg" };
}



