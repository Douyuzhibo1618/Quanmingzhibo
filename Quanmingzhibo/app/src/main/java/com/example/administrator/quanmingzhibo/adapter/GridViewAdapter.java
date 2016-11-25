package com.example.administrator.quanmingzhibo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.quanmingzhibo.R;
import com.example.administrator.quanmingzhibo.bean.TuiJianBean;
import com.example.administrator.quanmingzhibo.widget.CircleImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by imp on 2016/11/24.
 */
public class GridViewAdapter  extends BaseAdapter{

    private Context context;
    private List<TuiJianBean.RoomBean.ListBean> data;

    public GridViewAdapter(Context context, List<TuiJianBean.RoomBean.ListBean> data) {
        this.context = context;
        this.data = data;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.gridviewitem,parent,false);
            holder = new ViewHolder();
            holder.image = (ImageView)convertView.findViewById(R.id.image);
            holder.touxiang = (CircleImageView)convertView.findViewById(R.id.touxiang);
            holder.author = (TextView)convertView.findViewById(R.id.author);
            holder.summary = (TextView)convertView.findViewById(R.id.summary);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        //赋值
        TuiJianBean.RoomBean.ListBean  bean=  data.get(position);
        String imageurl = bean.getThumb();
        Picasso.with(context).load(imageurl).placeholder(R.mipmap.img_top_loading_jj12).into(holder.image);
        String touxiangurl = bean.getAvatar();
        Picasso.with(context).load(imageurl).placeholder(R.mipmap.img_top_loading_jj12).into(holder.touxiang);
        holder.author.setText(bean.getNick());
        holder.summary.setText(bean.getTitle());
        return convertView;
    }

    class ViewHolder{
        ImageView image;
        CircleImageView touxiang;
        TextView author,summary;

    }
}
