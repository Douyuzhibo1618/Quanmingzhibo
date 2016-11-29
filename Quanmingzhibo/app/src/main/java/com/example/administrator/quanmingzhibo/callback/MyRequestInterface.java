package com.example.administrator.quanmingzhibo.callback;

import com.example.administrator.quanmingzhibo.bean.TuiJianBean;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by lhx(150904029) on 2016/11/28.
 */
public interface MyRequestInterface {

    /* //精彩推荐。。颜值控等（6个）
    public static String Tuijian = "http://www.quanmin.tv/json/app/index/recommend/list-android.json?11211748&v=2.2.4&os=1&ver=4";
*/

   @POST(value = "json/app/index/recommend/list-android.json?11211748")
    public Call<TuiJianBean.RoomBean> getData(
           @Query("v") String v,
           @Query("os") String os,
           @Query("ver") String ver);
}
