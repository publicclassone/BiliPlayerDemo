package com.qianfeng.demo.biliplayer.adapter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.qianfeng.demo.biliplayer.Bean.contractor;
import com.qianfeng.demo.biliplayer.R;

import java.util.List;


/**
 * Created by zero on 2015/11/12.
 */
public class commentAdapter extends BaseAdapter {
    private List<contractor> list;
    private Context context;




    public commentAdapter(List<contractor> list, Context context) {
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

        Message message=new Message();


        viewHolder holder = null;

        if (convertView == null) {
            convertView = View.inflate(context, R.layout.comment, null);
            holder = new viewHolder();

            holder.tv_floor = ((TextView) convertView.findViewById(R.id.tv_floor));
            holder.tv_comment= (TextView) convertView.findViewById(R.id.tv_comment);
            convertView.setTag(holder);
        } else {
            holder = (viewHolder) convertView.getTag();
        }
        holder.tv_floor.setText("#" + position);
        holder.tv_comment.setText(list.get(position).getName());

        return convertView;
    }

    class viewHolder {

        TextView tv_floor,tv_comment;
    }

    public static class editBroatcast extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

           String  comment = intent.getStringExtra("comment");
            Log.e("Mainactivity","==="+comment);

        }
    }

}

