package com.example.administrator.quanmingzhibo;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.administrator.quanmingzhibo.bean.DetailsBean;
import com.example.administrator.quanmingzhibo.uri.Url;
import com.example.administrator.quanmingzhibo.widget.CircleImageView;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.VideoView;

public class DetailsActivity extends AppCompatActivity {

    private VideoView player;
    private CircleImageView touxiang;
    private TextView author;
    private TextView summary;

    private List<DetailsBean> listData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        touxiang = ((CircleImageView) findViewById(R.id.details_touxiang));
        author = ((TextView) findViewById(R.id.details_author));
        summary = ((TextView) findViewById(R.id.details_summary));

        int uid = getIntent().getIntExtra("uid",1868601);
        String realUid = uid+"";


        listData = new ArrayList<>();

//        new DetailsTask(DetailsActivity.this, new DetailsCallBack() {
//            @Override
//            public void sendData(List<DetailsBean> data) {
//                listData.addAll(data);
//                if(Vitamio.isInitialized(DetailsActivity.this)){
//                    player = ((VideoView) findViewById(R.id.details_player));
//                    player.setVideoURI(listData.get());
//                }
//            }
//        }).execute();

        RequestParams parama = new RequestParams(Url.getZhiboPath(realUid));
        Log.e("info","-----------------parama---------"+parama);
        x.http().get(parama, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                Log.e("info","+++++++++onSuccess+++++++++++++++++++");
                DetailsBean detailsBean = new Gson().fromJson(result, DetailsBean.class);

                listData.add(detailsBean);
                if (Vitamio.isInitialized(DetailsActivity.this)){
                    player = ((VideoView) findViewById(R.id.details_player));

                    String playUrl = detailsBean.getUri();
                    Log.e("info",playUrl);
                    player.setVideoURI(Uri.parse(playUrl));
                   player.start();
                }
                author.setText(detailsBean.getAuthor());

                Log.e("info","++++++++++++++"+detailsBean.getAuthor());
                summary.setText(detailsBean.getSummary());
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("info","+++++++++onError+++++++++++++++++++");

            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.e("info","+++++++++onCancelled+++++++++++++++++++");

            }

            @Override
            public void onFinished() {

            }
        });
    }
}
