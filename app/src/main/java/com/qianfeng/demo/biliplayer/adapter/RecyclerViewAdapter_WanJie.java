package com.qianfeng.demo.biliplayer.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;
import com.qianfeng.demo.biliplayer.Bean.WanJieDatas;
import com.qianfeng.demo.biliplayer.R;

import java.util.List;

/**
 * 完结fragment里面recyclerView的适配器
 */
public class RecyclerViewAdapter_WanJie extends RecyclerView.Adapter<RecyclerViewAdapter_WanJie.mViewHolder> {
    private String[] mTitle;
    private Context context;

    List<WanJieDatas.ListEntity> mListEntity;

    public RecyclerViewAdapter_WanJie(String[] mTitle, List<WanJieDatas.ListEntity> mListEntity,Context context) {
        this.mTitle = mTitle;
        this.context = context;
        this.mListEntity=mListEntity;
    }

    @Override
    public mViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mViewHolder holder = new mViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.item_recyclerview, parent, false));
        return holder;
    }

    @Override
    public int getItemCount() {
        return mListEntity.size();
    }

    @Override
    public void onBindViewHolder(mViewHolder holder, int position) {

        WanJieDatas.ListEntity mData = mListEntity.get(position);
        holder.tv_recycler.setText(mData.getTitle() + "");
        BitmapUtils bitmapUtils=new BitmapUtils(context);

       // holder.iv_recycler.setBackgroundResource(R.drawable.ic_bili_logo);
        bitmapUtils.display(holder.iv_recycler, mData.getPic());

    }

    class mViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_recycler;
        TextView tv_recycler;
        public mViewHolder(View itemView) {
            super(itemView);
            iv_recycler= (ImageView) itemView.findViewById(R.id.iv_recycle);
            tv_recycler= (TextView) itemView.findViewById(R.id.tv_title);
        }
    }






}