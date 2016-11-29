package com.example.administrator.quanmingzhibo.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.example.administrator.quanmingzhibo.bean.DetailsBean;
import com.example.administrator.quanmingzhibo.callback.DetailsCallBack;
import com.example.administrator.quanmingzhibo.json.ParseJson;
import com.example.administrator.quanmingzhibo.utils.HttpUtils;

import java.util.List;

/**
 * Created by imp on 2016/11/29.
 */
public class DetailsTask extends AsyncTask<String,Void,List<DetailsBean>> {

    private Context context;
    private DetailsCallBack callBack;
    private ProgressDialog pd;

    public DetailsTask(Context context, DetailsCallBack callBack) {
        this.context = context;
        this.callBack = callBack;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd = new ProgressDialog(context);
        pd.setMessage("正在请求网络，请稍后..");
        pd.show();
    }

    @Override
    protected List<DetailsBean> doInBackground(String... params) {

        String jsonStr = HttpUtils.getStringResult(params[0]);

        return ParseJson.parseJson(jsonStr);
    }

    @Override
    protected void onPostExecute(List<DetailsBean> detailsBeen) {
        super.onPostExecute(detailsBeen);
        pd.dismiss();
        callBack.sendData(detailsBeen);
    }
}
