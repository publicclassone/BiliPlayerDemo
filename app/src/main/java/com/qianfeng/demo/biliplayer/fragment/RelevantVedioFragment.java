package com.qianfeng.demo.biliplayer.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.qianfeng.demo.biliplayer.Bean.contractor;
import com.qianfeng.demo.biliplayer.R;
import com.qianfeng.demo.biliplayer.adapter.RelevantAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zero on 2015/11/11.
 */
public class RelevantVedioFragment extends Fragment {
    private ListView lv_Relevant;
    private contractor contractor;
    private List<contractor> list;
    private SwipeRefreshLayout sr_layout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.relevantvediofragment, null);
        list = new ArrayList<>();

        for (int i = 0; i < 15; i++) {
            contractor = new contractor();
            contractor.setName("nihao");
            contractor.setSorce(1);
            list.add(contractor);
        }

        lv_Relevant = (ListView) view.findViewById(R.id.lv_Relevant);
        //下拉刷新
        sr_layout = (SwipeRefreshLayout) view.findViewById(R.id.sr_layout);
        sr_layout.setColorSchemeResources(android.R.color.holo_blue_light,
                android.R.color.holo_red_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_green_light);
        sr_layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        sr_layout.setRefreshing(false);
                    }
                }, 4000);

            }
        });


        lv_Relevant.setAdapter(new RelevantAdapter(list, getContext()));

        return view;
    }
}
