package com.qianfeng.demo.biliplayer.fragment;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.qianfeng.demo.biliplayer.Bean.LianZaiDatas;
import com.qianfeng.demo.biliplayer.Bean.WanJieDatas;
import com.qianfeng.demo.biliplayer.R;
import com.qianfeng.demo.biliplayer.activity.MainActivity;
import com.qianfeng.demo.biliplayer.adapter.RecyclerViewAdapter_LianZai;
import com.qianfeng.demo.biliplayer.adapter.RecyclerViewAdapter_WanJie;
import com.qianfeng.demo.biliplayer.util.CommonUtil;
import com.qianfeng.demo.biliplayer.util.URLUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 完结栏的fragment
 */
public class LianZaiFragment extends Fragment {
    private static RecyclerView mRecyclerView;
    private String[] mTitle;
    private List<LianZaiDatas.ListEntity> mListEntity;
    private RecyclerViewAdapter_LianZai mAdapter;
    private ObjectAnimator oa;

    public LianZaiFragment(String[] mTitle) {
        this.mTitle = mTitle;
    }

    private SwipeRefreshLayout refresh_icon;
    private GridLayoutManager mLayoutManager;
    private StaggeredGridLayoutManager mStaggeredLayoutManager;
    private FloatingActionButton fab_refresh_lianzai, fab_change;
    private AnimationDrawable anim;
    private ImageView iv;
    private List<Integer> mHeights;

    private boolean isStagered = true;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // mHeights=new ArrayList<>();


        final View view = inflater.inflate(R.layout.fragment_lianzai, null, false);
        fab_refresh_lianzai = (FloatingActionButton) view.findViewById(R.id.fab_refresh_lianzai);
        refresh_icon= (SwipeRefreshLayout) view.findViewById(R.id.refresh_icon);
        //fab_change= (FloatingActionButton) view.findViewById(R.id.fab_change);
        iv = (ImageView) view.findViewById(R.id.iv_refresh);
        anim = (AnimationDrawable) iv.getBackground();

        mStaggeredLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mLayoutManager = new GridLayoutManager(getContext(), 2);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.mRecyclerView_lianzai);
//        for (int i = 0; i < 20; i++) {
//            mHeights.add(200);
//        }
        mRecyclerView.setLayoutManager(mStaggeredLayoutManager);
        // changedLayout();

        oa = ObjectAnimator.ofFloat(iv, "alpha", 0f, 1f);
        oa.setDuration(3000);

        getDataFromHttp();
        fab_refresh_lianzai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CommonUtil.isNetWork(getContext())) {
                    refresh_icon.setRefreshing(true);
                 //   iv.setVisibility(View.VISIBLE);
//                    anim.start();
//                    oa.start();
                    getDataFromHttp();
                    mAdapter.notifyDataSetChanged();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            Snackbar.make(view, "刷新完成!", Snackbar.LENGTH_SHORT)
                                    .show();

                            iv.setVisibility(View.INVISIBLE);
                            refresh_icon.setRefreshing(false);
                           // anim.stop();
//                        anim= new AnimationDrawable();
//                        anim.start();
                            // fab_refresh_lianzai.setImageResource(R.anim.fab_refresh);
                        }
                    }, 2000);

                } else {
                    iv.setVisibility(View.VISIBLE);
                    anim.start();
                    oa.start();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Snackbar.make(view, "网络连接错误...", Snackbar.LENGTH_SHORT)
                                    .show();
                            iv.setVisibility(View.INVISIBLE);
                            anim.stop();

//                        anim= new AnimationDrawable();
//                        anim.start();
                            // fab_refresh_lianzai.setImageResource(R.anim.fab_refresh);
                        }
                    }, 2000);
                }


            }
        });

        return view;
    }

    /**
     * 改变布局
     */
    private void changedLayout() {
        if (isStagered) {
            fab_change.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int i = 0; i < 20; i++) {
                        mHeights.add((int) (200 + Math.random() * 100));
                    }
                    mRecyclerView.setLayoutManager(mStaggeredLayoutManager);
                    isStagered = false;
                }
            });

        } else {
            fab_change.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int i = 0; i < 20; i++) {
                        mHeights.add(200);
                    }
                    mRecyclerView.setLayoutManager(mLayoutManager);
                    isStagered = true;
                }
            });
        }
    }


    /**
     * 从网络上获取数据
     */
    public void getDataFromHttp() {
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.send(HttpRequest.HttpMethod.GET, URLUtil.URL_LIAN_ZAI, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
                Log.e("---", result + "");

                parserForGson(result);
                //recyclerView监听事件
                mAdapter = new RecyclerViewAdapter_LianZai(mTitle, mListEntity, getContext());
                mAdapter.setOnItemClickListener(new RecyclerViewAdapter_LianZai.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Toast.makeText(getContext(),"click"+position,Toast.LENGTH_SHORT).show();
                       startActivity(new Intent(getContext(), MainActivity.class));
                    }

                });


                // refresh();

                mRecyclerView.setAdapter(mAdapter);


                Log.i("result", result.toString());
            }

            @Override
            public void onFailure(HttpException error, String msg) {
                error.printStackTrace();
                Log.e("---", error + "");
                Toast.makeText(getContext(), "请检查网络连接", Toast.LENGTH_SHORT).show();

            }
        });
    }

    /**
     * Gson解析
     *
     * @param result
     */
    private void parserForGson(String result) {
        LianZaiDatas datas = new Gson().fromJson(result, LianZaiDatas.class);
        mListEntity = datas.getList();
        Log.e("---", mListEntity.size() + "");

    }

}
