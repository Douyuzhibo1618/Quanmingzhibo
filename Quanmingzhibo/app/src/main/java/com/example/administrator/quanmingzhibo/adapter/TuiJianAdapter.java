package com.example.administrator.quanmingzhibo.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;

import com.example.administrator.quanmingzhibo.DetailsActivity;
import com.example.administrator.quanmingzhibo.R;
import com.example.administrator.quanmingzhibo.bean.TuiJianBean;

import java.util.List;

/**
 * Created by imp on 2016/11/24.
 */
public class TuiJianAdapter extends BaseAdapter{

    private Context context;
    private List<TuiJianBean.RoomBean> data;
    private List<Integer> idlist;


    public TuiJianAdapter(Context context, List<TuiJianBean.RoomBean> data,List<Integer> idlist) {
        this.context = context;
        this.data = data;
        this.idlist = idlist;
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
        final List<TuiJianBean.RoomBean.ListBean> list = data.get(position).getList();
   //     Log.e("-------------",list.get(1).getAvatar());
      //  list.get(position).setCoin(position);
        int id = idlist.get(position);
        if(id == 29){
            holder.gv.setAdapter(new GridView2Adapter(context,list));
        }else{
            holder.gv.setAdapter(new GridViewAdapter(context,list));
        }


        holder.gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               int uid = list.get(position).getUid();
                Intent intent = new Intent();
                intent.putExtra("uid",uid);
                intent.setClass(context, DetailsActivity.class);
                context.startActivity(intent);
            }
        });

        return convertView;
    }

    class ViewHolder{
        GridView gv;
    }

}
