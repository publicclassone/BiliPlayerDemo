package com.qianfeng.demo.biliplayer.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qianfeng.demo.biliplayer.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/11/12.
 */
public class FragmentUserId extends Fragment {
    private RecyclerView recycler;
    List<String> data;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initData();
        View view = inflater.inflate(R.layout.fragment_userid, container, false);
        recycler = ((RecyclerView) view.findViewById(R.id.feed_listview));
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler.setAdapter(new Myadapter());
        return view;
    }

    private void initData() {
        data = new ArrayList<String>();
        for (int i = 0; i < 50; i++) {
            data.add("item" + i);
        }
    }

    class Myadapter extends  RecyclerView.Adapter{

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            TextView textview=new TextView(getContext());
            return new Viewholder(textview);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            Viewholder view= (Viewholder) holder;
            view.text.setText(data.get(position));
            view.text.setTextSize(20);
        }

        @Override
        public int getItemCount() {
            return data.size();
        }
        class Viewholder extends  RecyclerView.ViewHolder{
            private TextView text;
            public Viewholder(View itemView) {
                super(itemView);
                this.text= (TextView) itemView;
            }
        }
    }

}
