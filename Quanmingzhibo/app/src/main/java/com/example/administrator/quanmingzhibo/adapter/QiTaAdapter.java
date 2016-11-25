package com.example.administrator.quanmingzhibo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.example.administrator.quanmingzhibo.R;

import java.util.List;

/**
 * Created by imp on 2016/11/22.
 */
public class QiTaAdapter extends BaseAdapter {

    private List<String>  data;
    private Context context;

    public QiTaAdapter(List<String> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data != null ?data.size():0;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.pindaoitem,parent,false);
            holder = new ViewHolder();
            holder.button = (Button)convertView.findViewById(R.id.button);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }

        holder.button.setText(data.get(position));

        return convertView;
    }

    class ViewHolder{
        Button button;
    }
}
