package com.example.administrator.quanmingzhibo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

import com.example.administrator.quanmingzhibo.R;
import com.example.administrator.quanmingzhibo.bean.TuiJianBean;

import java.util.List;

/**
 * Created by imp on 2016/11/24.
 */
public class TuiJianAdapter extends BaseAdapter{

    private Context context;
    private List<TuiJianBean.RoomBean> data;
    private GridViewAdapter adapter;

    public TuiJianAdapter(Context context, List<TuiJianBean.RoomBean> data) {
        this.context = context;
        this.data = data;
    }

    public void setData(List<TuiJianBean.RoomBean> data){
        this.data = data;
        notifyDataSetChanged();

    }
    @Override
    public int getCount() {
        return data !=null?data.size():0;
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
        ViewHolder holder  = null;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.tuijianlistitem,parent,false);
            holder  =  new ViewHolder();
            holder.gv = (GridView) convertView.findViewById(R.id.gv);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        //赋值
        List<TuiJianBean.RoomBean.ListBean> list = data.get(position).getList();


        holder.gv.setAdapter(new GridViewAdapter(context,list));


        return convertView;
    }

    class ViewHolder{
        GridView gv;
    }

}
