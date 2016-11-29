package com.example.administrator.quanmingzhibo.http;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.administrator.quanmingzhibo.callback.LanmuCallBack;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Administrator on 2016/11/22.
 */
public class PublicHttp extends AsyncTask<String,Void,byte[]> {

    private LanmuCallBack callBack;
    private Context context;
    public PublicHttp(Context context,LanmuCallBack callBack){
        this.context = context;
        this.callBack = callBack;
    }
    private ProgressDialog pd;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd = new ProgressDialog(context);
        pd.setMessage("正在请求网络，请稍后...");
    }

    @Override
    protected byte[] doInBackground(String... params) {
        ByteArrayOutputStream baos = null;
        try {
            URL url = new URL(params[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            if(connection.getResponseCode()==200){
                InputStream inputStream = connection.getInputStream();
                int len = 0;
                byte[] b = new byte[8*1024];
                baos = new ByteArrayOutputStream();
                while ((len=inputStream.read(b))!=-1){
                    baos.write(b,0,len);
                    baos.flush();
                }
                Log.e("BBBB","====>"+baos.toByteArray());
                return baos.toByteArray();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(baos!=null){
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return baos.toByteArray();
    }

    @Override
    protected void onPostExecute(byte[] bytes) {
        super.onPostExecute(bytes);
        if(bytes!=null){
            Log.e("BBBB","====>"+bytes.length);
            callBack.getData(bytes);
        }
    }
}
