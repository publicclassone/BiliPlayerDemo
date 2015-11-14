package com.qianfeng.demo.biliplayer.setting;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.qianfeng.demo.biliplayer.R;

/**
 * Created by Administrator on 2015/11/10.
 */
public class AboutActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }
}
