package com.qianfeng.demo.biliplayer.activity;


import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.qianfeng.demo.biliplayer.R;


public class offlineActivity extends AppCompatActivity {

    Toolbar bar;

    private ImageView iv_refresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline);
        inittoolbar();
        initspace();
        initAnimation();

    }

    private void initAnimation() {

        iv_refresh = (ImageView) findViewById(R.id.iv_refresh);

        iv_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getBaseContext(), "sss", Toast.LENGTH_SHORT).show();
                RotateAnimation animation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                animation.setDuration(2000);
                iv_refresh.setAnimation(animation);

            }
        });

    }


    private void initspace() {
        TextView tv = ((TextView) findViewById(R.id.tv_memory));

        Long freeSpace = Environment.getExternalStorageDirectory().getFreeSpace();
        String free = android.text.format.Formatter.formatFileSize(this, freeSpace);

        Long totalSpace = Environment.getExternalStorageDirectory().getTotalSpace();
        String totla = android.text.format.Formatter.formatFileSize(this, totalSpace);
        tv.setText("主存储: " + totla + "/" + "可用存储: " + free);

    }


    private void inittoolbar() {
        bar = ((Toolbar) findViewById(R.id.toolbar_offline));
        bar.setTitle("缓存列表");
        setSupportActionBar(bar);

        bar.setNavigationIcon(R.drawable.ic_arrow_back_black);
        bar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


}
