package com.qianfeng.demo.biliplayer.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;
import com.qianfeng.demo.biliplayer.Bean.LianZaiDatas;
import com.qianfeng.demo.biliplayer.Bean.WanJieDatas;
import com.qianfeng.demo.biliplayer.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 完结fragment里面recyclerView的适配器
 */
public class RecyclerViewAdapter_LianZai extends RecyclerView.Adapter<RecyclerViewAdapter_LianZai.mViewHolder> {
    private String[] mTitle;
    private Context context;
    private List<Integer> mHeights;
    List<LianZaiDatas.ListEntity> mListEntity;

    public RecyclerViewAdapter_LianZai(String[] mTitle, List<LianZaiDatas.ListEntity> mListEntity, Context context) {
        this.mTitle = mTitle;
        this.context = context;
        this.mListEntity=mListEntity;
        mHeights=new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mHeights.add((int) (200+Math.random()*100));
        }

    }

    public interface  OnItemClickListener{
        void onItemClick(View view,int position);
       // void onLongItemClick(View view,int postion);
    }

    private OnItemClickListener mOnItemClickListener;
    public void setOnItemClickListener(OnItemClickListener listener){
        this.mOnItemClickListener=listener;
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
    public void onBindViewHolder(final mViewHolder holder, final int position) {
        ViewGroup.LayoutParams params = holder.itemView.getLayoutParams();

        params.height=mHeights.get(position);//随机高度

        LianZaiDatas.ListEntity mData = mListEntity.get(position);
        holder.tv_recycler.setText(mData.getTitle() + "");
        BitmapUtils bitmapUtils=new BitmapUtils(context);

       // holder.iv_recycler.setBackgroundResource(R.drawable.ic_bili_logo);
        bitmapUtils.display(holder.iv_recycler, mData.getPic());

        if (mOnItemClickListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(holder.iv_recycler,position);
                }
            });
        }



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