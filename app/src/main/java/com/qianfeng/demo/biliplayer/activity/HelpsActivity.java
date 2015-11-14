package com.qianfeng.demo.biliplayer.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.qianfeng.demo.biliplayer.R;

public class HelpsActivity extends AppCompatActivity {

    private ListView lv;
    String[] s = {"检查更新", "CPU信息", "网络诊断", "反馈", "开放源代码许可", "加入我们", "关于"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helps);

        initToolbar();
        initlistview();
    }

    private void initlistview() {
        lv = (ListView) findViewById(R.id.lv_help_detil);
        lv.setAdapter(new helpingAdapter(s));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Toast.makeText(getBaseContext(), "版本已经是最新", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Intent intent = new Intent(getBaseContext(), cpuMessage.class);
                        startActivity(intent);
                        break;
                    case 2:
                        Intent intent2 = new Intent(getBaseContext(), NetWorkActivity.class);
                        startActivity(intent2);
                        break;

                    case 6:
                        Intent intent6 = new Intent(getBaseContext(), NetWorkActivity.class);
                        startActivity(intent6);
                        break;
                }

            }
        });


    }

    private void initToolbar() {
        Toolbar bar = (Toolbar) findViewById(R.id.toolbar_helping);
        bar.setTitle("帮助");
        setSupportActionBar(bar);
    }

    class helpingAdapter extends BaseAdapter {

        private String s[];

        public helpingAdapter(String[] s) {
            this.s = s;
        }

        @Override
        public int getCount() {
            return s.length;
        }

        @Override
        public Object getItem(int position) {
            return s[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Viewholder holder = null;
            if (convertView == null) {
                convertView = View.inflate(getBaseContext(), R.layout.helping, null);
                holder = new Viewholder();
                holder.helping = (TextView) convertView.findViewById(R.id.tv_helping);
                convertView.setTag(holder);
            } else {
                holder = (Viewholder) convertView.getTag();
            }
            holder.helping.setText(s[position]);


            return convertView;
        }

        class Viewholder {

            TextView helping;
        }
    }
}



