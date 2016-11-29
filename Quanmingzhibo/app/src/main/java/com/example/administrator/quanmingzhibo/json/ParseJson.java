package com.example.administrator.quanmingzhibo.json;

import com.example.administrator.quanmingzhibo.bean.DetailsBean;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by imp on 2016/11/29.
 */
public class ParseJson {
    public static List<DetailsBean> parseJson(String jsonStr) {
        List<DetailsBean> data = null;

            try {
                if(jsonStr != null) {
                    data = new ArrayList<>();
                    JSONObject jsonObject = new JSONObject(jsonStr);
                    String author = jsonObject.getString("announcement");
                    String title = jsonObject.getString("title");
                    JSONObject jsonObjece2 = jsonObject.getJSONObject("live");
                    JSONObject jsonObjece3 = jsonObjece2.getJSONObject("ws");
                    JSONObject jsonObject4 = jsonObjece3.getJSONObject("hls");
                    JSONObject jsonObjext5 = jsonObject4.getJSONObject("0");
                    String url = jsonObjext5.getString("src");
                    data.add(new DetailsBean(author,title,url));
                    return  data;

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        return data;
    }
}
