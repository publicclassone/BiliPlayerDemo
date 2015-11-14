package com.qianfeng.demo.biliplayer.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bartoszlipinski.recyclerviewheader.RecyclerViewHeader;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.qianfeng.demo.biliplayer.Bean.WanJieDatas;
import com.qianfeng.demo.biliplayer.MyRecyclerViewPager;
import com.qianfeng.demo.biliplayer.R;
import com.qianfeng.demo.biliplayer.adapter.RecyclePlayAdapter;
import com.qianfeng.demo.biliplayer.adapter.RecyclerViewAdapter_WanJie;
import com.qianfeng.demo.biliplayer.util.URLUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 完结栏的fragment
 */
public class WanJieFragment extends Fragment {
    private static RecyclerView mRecyclerView;
    private String[] mTitle;
    private List<WanJieDatas.ListEntity> mListEntity;
    private RecyclerViewAdapter_WanJie mAdapter;

    public WanJieFragment(String[] mTitle) {
        this.mTitle = mTitle;
    }
    private SwipeRefreshLayout refresh;
    private  GridLayoutManager mLayoutManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_wanjie, null, false);
        mLayoutManager = new GridLayoutManager(getContext(), 2);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.mRecyclerView);
        refresh= (SwipeRefreshLayout) view.findViewById(R.id.refresh);
        refresh.setColorSchemeResources(R.color.refresh_1, R.color.refresh_2, R.color.refresh_3, R.color.refresh_4);

        mRecyclerView.setLayoutManager(mLayoutManager);
        getDataFromHttp();

        return view;
    }

    /**
     * 下拉刷新
     */
    private void refresh() {
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.i("refresh", "正在刷新...");
                refresh.setRefreshing(true);


                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getDataFromHttp();
                        mAdapter.notifyDataSetChanged();
                        Log.i("refresh", "刷新完成...");
                        refresh.setRefreshing(false);
                    }
                }, 3000);

            }
        });
    }



    /**
     * 从网络上获取数据
     */
    public  void getDataFromHttp(){
        HttpUtils httpUtils=new HttpUtils();
        httpUtils.send(HttpRequest.HttpMethod.GET, URLUtil.URL_WAN_JIE, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
                Log.e("---", result+"");

                parserForGson(result);

                mAdapter = new RecyclerViewAdapter_WanJie(mTitle, mListEntity, getContext());
                refresh();

                mRecyclerView.setAdapter(mAdapter);


                Log.i("result", result.toString());
            }

            @Override
            public void onFailure(HttpException error, String msg) {
                error.printStackTrace();
                Log.e("---", error + "");
                Toast.makeText(getContext(),"请检查网络连接",Toast.LENGTH_SHORT).show();

            }
        });
    }

    /**
     * Gson解析
     * @param result
     */
    private void  parserForGson(String result){
        WanJieDatas datas= new Gson().fromJson(result, WanJieDatas.class);
        mListEntity = datas.getList();
            Log.e("---",mListEntity.size()+"");

    }

}
