package com.example.administrator.quanmingzhibo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.quanmingzhibo.R;
import com.example.administrator.quanmingzhibo.bean.YanZhiKong;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by imp on 2016/11/25.
 */
public class YanZhiKongAdapter extends BaseAdapter {

    private Context context;
    private List<YanZhiKong.DataBean> data;

    public YanZhiKongAdapter(Context context, List<YanZhiKong.DataBean> data) {
        this.context = context;
        this.data = data;
    }

    public void setData(List<YanZhiKong.DataBean> data){
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data!=null?data.size():0;
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.yanzhikongitem,parent,false);
            holder = new ViewHolder();
            holder.image = (ImageView)convertView.findViewById(R.id.imagef);
            holder.title = (TextView)convertView.findViewById(R.id.textf);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }

        //控件赋值
        YanZhiKong.DataBean bean = data.get(position);
        String imageurl = bean.getThumb();
        if (imageurl != null&&imageurl.length()>0) {
            Picasso.with(context).load(imageurl).placeholder(R.mipmap.img_top_loading_jj12).into(holder.image);
        }

        holder.title.setText(bean.getTitle());

        return convertView;
    }

    class ViewHolder{
        ImageView image;
        TextView title;
    }
}
