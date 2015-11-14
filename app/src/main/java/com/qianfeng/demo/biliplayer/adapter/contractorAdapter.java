package com.qianfeng.demo.biliplayer.adapter;

import android.content.Context;
import android.media.Image;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.qianfeng.demo.biliplayer.Bean.contractor;
import com.qianfeng.demo.biliplayer.R;

import java.util.List;

/**
 * Created by zero on 2015/11/10.
 */
public class contractorAdapter extends BaseAdapter {
    private List<contractor> list;
    private Context context;

    public contractorAdapter(List<contractor> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        viewHolder holder=null;
        if (convertView==null){
            convertView=View.inflate(context, R.layout.contractor,null);
            holder=new viewHolder();
            holder.iv= ((ImageView) convertView.findViewById(R.id.iv_head));
            holder.tv= ((TextView) convertView.findViewById(R.id.tv_detil));
            convertView.setTag(holder);
        }else {
            holder= (viewHolder) convertView.getTag();
        }
           holder.iv.setImageResource(R.drawable.a);
           holder.tv.setText("nihao");

        return convertView;
    }
    class viewHolder{
        ImageView iv;
        TextView tv;
    }
}
