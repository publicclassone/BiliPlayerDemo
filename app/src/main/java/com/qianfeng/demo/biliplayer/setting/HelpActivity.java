package com.qianfeng.demo.biliplayer.setting;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.qianfeng.demo.biliplayer.R;

import static android.widget.Toast.LENGTH_SHORT;

public class HelpActivity extends BaseActivity {

    private ListView help_listview;
    private String[] data = {"检查更新", "CPU信息", "网络诊断", "反馈", "开放源代码许可", "加入我们", "关于"};
    private int versionCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        versionCode = getcode();
        initView();

    }

    private int getcode() {
        PackageManager packageManager = getPackageManager();
        PackageInfo packageInfo = null;
        try {
            packageInfo = packageManager.getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        int versionCode = packageInfo.versionCode;
        return versionCode;
    }


    private void initView() {
        help_listview = ((ListView) findViewById(R.id.help_listview));
        Myadapter adapter = new Myadapter();
        help_listview.setAdapter(adapter);
        help_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Toast.makeText(HelpActivity.this, "暂无更新", LENGTH_SHORT).show();
                        break;
                    case 1:
                        OpenActivity(HelpActivity.this,CPUinfoActivity.class);
                        break;
                    case 2:
                        OpenActivity(HelpActivity.this,NetworkActivity.class );
                        break;
                    case 3:
                        Intent feedbackintent = new Intent(HelpActivity.this,FeedbackActivity.class);
                        startActivity(feedbackintent);
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    case 6:
                        Intent intent = new Intent(HelpActivity.this, AboutActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        });
    }


    class Myadapter extends BaseAdapter {

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
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.help_item, null);
            }
            TextView textView = (TextView) convertView.findViewById(R.id.help_item);
            if (position == 0) {
                textView.setBackgroundResource(R.drawable.app_list_corner_round_top);
                textView.setText(data[position]);
            } else if (position == data.length - 1) {
                textView.setBackgroundResource(R.drawable.shape_bottom_corner_line);
                textView.setText(data[position]);
            } else {
                textView.setText(data[position]);
                textView.setBackgroundResource(R.drawable.shape_no_corner_without_bottom);
            }
            return convertView;
        }
    }
}
