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
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by imp on 2016/11/24.
 */
public class GridView2Adapter extends BaseAdapter{


    private Context context;
    private List<TuiJianBean.RoomBean.ListBean> data;


    public GridView2Adapter(Context context, List<TuiJianBean.RoomBean.ListBean> data) {
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
        ViewHolder holder2 = null;
        if(convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.gridview2_item, parent, false);
                holder2 = new ViewHolder();
                holder2.image = (ImageView) convertView.findViewById(R.id.image_gv2);
                holder2.author = (TextView) convertView.findViewById(R.id.text_gv2);
                holder2.coin = (TextView)convertView.findViewById(R.id.coin_gv2);
                convertView.setTag(holder2);
        }else{
                holder2 = (ViewHolder)convertView.getTag();
        }
            TuiJianBean.RoomBean.ListBean  bean=  data.get(position);
            String imageurl = bean.getThumb();
            Picasso.with(context).load(imageurl).placeholder(R.mipmap.img_top_loading_jj12).into(holder2.image);
            holder2.author.setText(bean.getNick());
            holder2.coin.setText(bean.getCoin()+"");
        return  convertView;
    }

    class ViewHolder{
        ImageView image;
        TextView author,coin;
    }

}
