package com.qianfeng.demo.biliplayer.setting;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.TableLayout;

import com.astuetz.PagerSlidingTabStrip;
import com.qianfeng.demo.biliplayer.R;
import com.qianfeng.demo.biliplayer.fragment.FragmentOffenProblem;
import com.qianfeng.demo.biliplayer.fragment.FragmentUserFeedback;
import com.qianfeng.demo.biliplayer.fragment.FragmentUserId;

import java.util.ArrayList;
import java.util.List;


public class FeedbackActivity extends FragmentActivity {

    private String[] data = {"用户反馈", "常见问题"};
    private List<Fragment> viewList;

    // private TabLayout tablayout;
    private ViewPager viewPager;
    private PagerSlidingTabStrip tabstrip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        initData();
        initView();
    }

    private void initData() {
        viewList = new ArrayList();
        viewList.add(new FragmentUserFeedback());
        viewList.add(new FragmentOffenProblem());

    }
    private void initView() {
        tabstrip = ((PagerSlidingTabStrip) findViewById(R.id.tabstrip));
        //取消tab下面的长横线
//        tabstrip.setDrawFullUnderline(false);
        //设置tab的背景色
        tabstrip.setBackgroundColor(this.getResources().getColor(R.color.white));
        //设置当前tab页签的下划线颜色
        tabstrip.setIndicatorColor(this.getResources().getColor(R.color.gray));
        tabstrip.setShouldExpand(true);
//        tabstrip.setTabIndicatorColor(this.getResources().getColor(R.color.blcak));
//        tabstrip.setTextSpacing(200);
        viewPager = ((ViewPager) findViewById(R.id.viewpager));
        viewPager.setAdapter(new MypagerAdapter(getSupportFragmentManager()));
        tabstrip.setViewPager(viewPager);
        // tablayout.setupWithViewPager(viewPager);
    }
    class MypagerAdapter extends FragmentPagerAdapter{


        public MypagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return viewList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return data[position];
        }

        @Override
        public Fragment getItem(int position) {
            return viewList.get(position);
        }
    }

}
