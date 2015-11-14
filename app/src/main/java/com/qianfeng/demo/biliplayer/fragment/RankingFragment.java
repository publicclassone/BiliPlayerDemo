package com.qianfeng.demo.biliplayer.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.qianfeng.demo.biliplayer.R;
import com.qianfeng.demo.biliplayer.Bean.contractor;
import com.qianfeng.demo.biliplayer.adapter.contractorAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by zero on 2015/11/10.
 */
public class RankingFragment extends Fragment {
    private ListView lv;
    private contractor contractor;
    private List<contractor> list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.rankingfragment, null, false);
        lv = (ListView) view.findViewById(R.id.lv_contractor);
        list = new ArrayList<>();

        for (int i = 0; i < 15; i++) {
            contractor = new contractor();
            contractor.setName("nihao");
            contractor.setSorce(1);
            list.add(contractor);
        }


        lv.setAdapter(new contractorAdapter(list, getContext()));
        return view;


    }
}