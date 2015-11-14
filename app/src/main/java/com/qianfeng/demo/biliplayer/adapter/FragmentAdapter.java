package com.qianfeng.demo.biliplayer.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 *
 */
public class FragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;
    private String[] mTitle;
    public FragmentAdapter(FragmentManager fm,List<Fragment> fragments,String[] mTitle) {
        super(fm);
        this.fragments=fragments;
        this.mTitle=mTitle;
    }


    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitle[position];
    }

}
