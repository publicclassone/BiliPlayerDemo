package com.qianfeng.demo.biliplayer.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qianfeng.demo.biliplayer.R;

/**
 * Created by Administrator on 2015/11/10.
 */
public class FragmentOffenProblem extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_userid, container,false);
    }
}
