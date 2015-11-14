package com.qianfeng.demo.biliplayer.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qianfeng.demo.biliplayer.R;

/**
 * Created by zero on 2015/11/11.
 */
public class VideoFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
           View view =inflater.inflate(R.layout.vediodetil,null);
        return view;
    }
}
