package com.qianfeng.demo.biliplayer.adapter;

import android.content.Context;
import android.text.Layout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

import com.qianfeng.demo.biliplayer.R;

import java.util.zip.Inflater;

/**
 * Created by Administrator on 2015/11/11.
 */
public class AdapterTanmu extends BaseAdapter {
    private Context context;
    private String[] data;
    private  int spString;

    public AdapterTanmu(String[] data, Context context,int spString) {
        this.data = data;
        this.context = context;
        this.spString=spString;
    }

    @Override
    public int getCount() {
        return data.length;
    }

    @Override
    public Object getItem(int position) {
        return data[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder vv;
        if (convertView == null) {
            vv = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.tanmu_yinqing_item, null);
            vv.textView = (TextView) convertView.findViewById(R.id.content);
            vv.button = (CheckBox) convertView.findViewById(R.id.yinqing_check);
            convertView.setTag(vv);
        } else {
            vv = ((ViewHolder) convertView.getTag());
        }

//        convertView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                boolean checked = vv.button.isChecked();
//                if (checked) {
//                    vv.button.setChecked(false);
//                } else {
//                    vv.button.setChecked(true);
//                }
//            }
//        });
        vv.textView.setText(data[position]);
        if (TextUtils.equals(spString+"",position+"")){
            vv.button.setChecked(true);
        }else{
            vv.button.setChecked(false);
        }
        return convertView;
    }

     public static class ViewHolder {
        TextView textView;
        CheckBox button;
    }

}
