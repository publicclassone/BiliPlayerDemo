package com.qianfeng.demo.biliplayer.setting;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;


import com.qianfeng.demo.biliplayer.R;
import com.qianfeng.demo.biliplayer.adapter.AdapterKeyWord;
import com.qianfeng.demo.biliplayer.fragment.FragmentKeyWord;
import com.qianfeng.demo.biliplayer.fragment.FragmentUserId;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/11/11.
 */
public class KeyworkActivity extends AppCompatActivity {
    private ViewPager viewPager;
    AdapterKeyWord adapter;
    private String[] title={"关键词","用户ID"};
    private List<Fragment> fragmentlist;

    private Handler mhandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            viewPager.setAdapter(adapter);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keywor);
        initData();
        initView();
    }

    private void initData() {
        fragmentlist=new ArrayList<Fragment>();
        fragmentlist.add(new FragmentKeyWord());
        fragmentlist.add(new FragmentUserId());
    }

    private void initView() {
        viewPager = ((ViewPager) findViewById(R.id.Viewpager));
        new Thread(new Runnable() {
            @Override
            public void run() {
                adapter=new AdapterKeyWord(getSupportFragmentManager(),title,fragmentlist);
                mhandler.sendEmptyMessage(0);
            }
        }).start();;
        // adapter.getData(fragmentlist,title);

    }


}
