package com.qianfeng.demo.biliplayer.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import com.qianfeng.demo.biliplayer.Bean.contractor;
import com.qianfeng.demo.biliplayer.R;
import com.qianfeng.demo.biliplayer.adapter.commentAdapter;

/**
 * Created by zero on 2015/11/12.
 */
public class commentFragment extends Fragment {
    private ListView lv_comment;
    private contractor contractor, container1;
    private List<contractor> list;
    private commentAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.commentfragment, null, false);




        lv_comment = (ListView) view.findViewById(R.id.lv_comment);
        list = new ArrayList<>();

        for (int i = 0; i < 15; i++) {
            contractor = new contractor();
            contractor.setName("nihao");
            contractor.setSorce(1);
            list.add(contractor);
        }


        this.adapter = new commentAdapter(list, getContext());
        commentAdapter adapter= this.adapter;
        lv_comment.setAdapter(adapter);


        return view;

    }

  //  @Override
//    public void onResume() {
//        super.onResume();
//        Bundle bundle = getArguments();
//        String comment = bundle.getString("comment");
//        container1 = new contractor();
//        container1.setName(comment);
//        list.add(container1);
//        adapter.notifyDataSetChanged();
//    }


//    @Override
//    public void onPause() {
//        super.onPause();
//        Bundle bundle = getArguments();
//        String comment = bundle.getString("comment");
//        container1 = new contractor();
//        container1.setName(comment);
//
//        adapter.notifyDataSetChanged();
//    }

}
