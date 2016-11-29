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
        ViewHolder holder1 = null;
        if(convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.gridview_item, parent, false);
                holder1 = new ViewHolder();
                holder1.image = (ImageView) convertView.findViewById(R.id.image_gv);
                holder1.touxiang = (CircleImageView) convertView.findViewById(R.id.image_gvc);
                holder1.author = (TextView) convertView.findViewById(R.id.text1);
                holder1.summary = (TextView) convertView.findViewById(R.id.text2);
                convertView.setTag(holder1);

        }else{
                holder1 = (ViewHolder)convertView.getTag();
        }


            TuiJianBean.RoomBean.ListBean  bean=  data.get(position);
            String imageurl = bean.getThumb();
            Picasso.with(context).load(imageurl).placeholder(R.mipmap.img_top_loading_jj12).into(holder1.image);
            String touxiangurl = bean.getAvatar();
            Picasso.with(context).load(touxiangurl).placeholder(R.mipmap.img_top_loading_jj12).error(R.mipmap.img_top_loading_jj12).into(holder1.touxiang);
            holder1.author.setText(bean.getNick());
            holder1.summary.setText(bean.getTitle());

        return  convertView;
    }

    class ViewHolder{
        ImageView image;
        CircleImageView touxiang;
        TextView author,summary;
    }

}
