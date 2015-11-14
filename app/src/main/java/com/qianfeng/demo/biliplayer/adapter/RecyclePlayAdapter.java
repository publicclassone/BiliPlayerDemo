package com.qianfeng.demo.biliplayer.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.qianfeng.demo.biliplayer.util.URLUtil;

import java.util.List;

/**
 * Created by Administrator on 2015/11/12.
 */
public class RecyclePlayAdapter extends PagerAdapter {
    private Context context;
    private List<ImageView> imageViewList;

    public RecyclePlayAdapter(Context context, List<ImageView> imageViewList) {
        this.context = context;
        this.imageViewList = imageViewList;
    }


    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(imageViewList.get(position%imageViewList.size()));
        return imageViewList.get(position%imageViewList.size());
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
       container.removeView((View) object);
    }


}
