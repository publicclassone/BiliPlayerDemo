package com.qianfeng.demo.biliplayer.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2015/11/12.
 */
public class AdapterKeyWord extends FragmentPagerAdapter{

    private  List<Fragment> datafragment;
    private String[] title;
    public AdapterKeyWord(FragmentManager fm,String[] title,List<Fragment> datafragment) {
        super(fm);
        this.title=title;
        this.datafragment=datafragment;
    }

    public void getData(List<Fragment> datafragment,String[] title){
        this.datafragment=datafragment;
        this.title=title;
    }
    @Override
    public Fragment getItem(int i) {
        return datafragment.get(i);
    }

    @Override
    public int getCount() {
        return datafragment.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
