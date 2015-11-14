package com.qianfeng.demo.biliplayer.setting;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toolbar;

import com.astuetz.PagerSlidingTabStrip;
import com.qianfeng.demo.biliplayer.R;
import com.qianfeng.demo.biliplayer.adapter.AdapterKeyWord;
import com.qianfeng.demo.biliplayer.fragment.FragmentUserId;

import java.util.ArrayList;
import java.util.List;

public class HideActivity extends AppCompatActivity {

    private PagerSlidingTabStrip tab_layout;
    private ViewPager viewPager;
    String title[] = {"Tab1", "Tab2", "Tab3", "Tab4"};
    List<Fragment> list;
    private FloatingActionButton fab;
    private TabLayout tabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hide);
        fab = ((FloatingActionButton) findViewById(R.id.fab));
        initData();
        initView();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v,"FAB",Snackbar.LENGTH_LONG).setAction("cancle", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }).show();
            }
        });
    }

    private void initData() {
        list=new ArrayList<Fragment>();
        list.add(new FragmentUserId());
        list.add(new FragmentUserId());
        list.add(new FragmentUserId());
        list.add(new FragmentUserId());
    }

    private void initView() {
        viewPager = ((ViewPager) findViewById(R.id.viewpager));
        tabs = ((TabLayout) findViewById(R.id.tabs));
        viewPager.setAdapter(new AdapterKeyWord(getSupportFragmentManager(),title,list));
        tabs.setupWithViewPager(viewPager);
    }

}
