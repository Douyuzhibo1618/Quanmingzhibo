package com.example.administrator.quanmingzhibo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.quanmingzhibo.R;
import com.example.administrator.quanmingzhibo.bean.OtherBean;
import com.example.administrator.quanmingzhibo.widget.CircleImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by imp on 2016/11/25.
 */
public class OtherAdapter extends RecyclerView.Adapter<OtherAdapter.MyViewHolder> {
    private Context context;
    private List<OtherBean.DataBean> data;

    public OtherAdapter(Context context, List<OtherBean.DataBean> data) {
        this.context = context;
        this.data = data;
    }

    public void setData(List<OtherBean.DataBean> data){
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.otheritem,parent,false));

        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String imageURL= data.get(position).getThumb();
        Picasso.with(context).load(imageURL).placeholder(R.mipmap.img_top_loading_jj12).into(holder.image);
        String touxiangUrl = data.get(position).getAvatar();
        Picasso.with(context).load(imageURL).placeholder(R.mipmap.img_top_loading_jj12).into(holder.touxiang);
        holder.title.setText(data.get(position).getNick());
        holder.summary.setText(data.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return data!=null ? data.size():0;
    }

    public static class  MyViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        CircleImageView touxiang;
        TextView title,summary;

        public MyViewHolder(View itemView) {
            super(itemView);
            image = (ImageView)itemView.findViewById(R.id.second_item_image);
            touxiang = (CircleImageView)itemView.findViewById(R.id.second_item_touxiang);
            title = (TextView)itemView.findViewById(R.id.second_item_nick);
            summary = (TextView)itemView.findViewById(R.id.second_item_title);

        }
    }

}
