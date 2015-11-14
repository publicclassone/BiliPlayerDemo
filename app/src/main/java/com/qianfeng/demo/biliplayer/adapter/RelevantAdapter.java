package com.qianfeng.demo.biliplayer.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.qianfeng.demo.biliplayer.Bean.contractor;
import com.qianfeng.demo.biliplayer.R;

import java.util.List;

/**
 * Created by zero on 2015/11/11.
 */
public class RelevantAdapter extends BaseAdapter {
    private List<contractor> list;
    private Context context;

    public RelevantAdapter(List<contractor> list, Context context) {
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
        Viewholder holder = null;
        if (convertView==null){
            convertView=View.inflate(context, R.layout.relevant, null);
            holder=new Viewholder();
            holder.iv_vedio = (ImageView) convertView.findViewById(R.id.iv_vedio);
            holder.tv_vedio = ((TextView) convertView.findViewById(R.id.tv_vedio));

            holder.tv_vedio_count = ((TextView) convertView.findViewById(R.id.tv_vedio_count));
            holder.tv_vedio_comment = ((TextView) convertView.findViewById(R.id.tv_vedio_comment));
            convertView.setTag(holder);
        }
        else{
            holder= (Viewholder) convertView.getTag();
        }
            holder.tv_vedio.setText("140W");
            holder.tv_vedio_count.setText("140");
            holder.tv_vedio_comment.setText("绯弹的亚里亚AA OP专辑team AA");

        return convertView;
    }
    class Viewholder{
        ImageView iv_vedio;
        TextView tv_vedio,tv_vedio_count,tv_vedio_comment;
    }
}
