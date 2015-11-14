package com.qianfeng.demo.biliplayer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.qianfeng.demo.biliplayer.R;
import com.qianfeng.demo.biliplayer.adapter.FragmentAdapter;
import com.qianfeng.demo.biliplayer.adapter.RecyclePlayAdapter;
import com.qianfeng.demo.biliplayer.fragment.LianZaiFragment;
import com.qianfeng.demo.biliplayer.fragment.WanJieFragment;
import com.qianfeng.demo.biliplayer.setting.SettingActivity;

import java.util.ArrayList;
import java.util.List;

public class HomePageActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
   private String[] mTitle = new String[]{"连载","完结","排行","分类","杂谈"};
   // private String[] mTitle = new String[]{"连载","完结"};
    private List<Fragment> fragments;

    @ViewInject(R.id.viewpager)
    private ViewPager mViewPager;

    @ViewInject(R.id.tl)
    private TabLayout mTabLayout;

    @ViewInject(R.id.nav_view)
    private NavigationView navigationView;

    @ViewInject(R.id.drawer_layout)
    private DrawerLayout drawer;

    private ImageButton btn_iv_lonin;
    @ViewInject(R.id.viewpager_collapsing)
    private ViewPager viewPager_collapsing;
    private ArrayList<ImageView> imageViewList;
    private int img[] = {R.drawable.testa, R.drawable.testb, R.drawable.testc, R.drawable.testd};

    @ViewInject(R.id.point_group_collapsing)
    private LinearLayout point_group_collapsing;

    @ViewInject(R.id.toolbar_layout)
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private int lastPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ViewUtils.inject(this);
        initDatas();
        isRunning = true;
        handler.sendEmptyMessageDelayed(0, 200);
        viewPager_collapsing.setAdapter(new RecyclePlayAdapter(this, imageViewList));
        viewPager_collapsing.addOnPageChangeListener(new MpageChangeListener());
        mCollapsingToolbarLayout.setTitle("");
        initFragment();
        initTabLayout();
        initToggle();
    }



    /**
     * viewpager滑动事件监听
     */
    class MpageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            position = position % imageViewList.size();
            if (position<=imageViewList.size()){
                point_group_collapsing.getChildAt(position).setEnabled(true);
                point_group_collapsing.getChildAt(lastPosition).setEnabled(false);
                lastPosition = position;
            }else {
                position = position % imageViewList.size();
            }


        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    /**
     * 判断是否自动滚动
     */
    private boolean isRunning = false;

    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {

            //让viewPager 滑动到下一页
            viewPager_collapsing.setCurrentItem(viewPager_collapsing.getCurrentItem() + 1);
            if (isRunning) {
                handler.sendEmptyMessageDelayed(0, 2000);
            }
        }
    };


    private void initDatas() {
        imageViewList = new ArrayList<>();
        for (int i = 0; i < img.length; i++) {
            //添加图片
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(img[i]);
            imageViewList.add(imageView);

            //添加指示点

            ImageView point = new ImageView(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.rightMargin = 20;
            point.setLayoutParams(layoutParams);
            point.setBackgroundResource(R.drawable.point_bg);
            if (i == 0) {
                point.setEnabled(true);

            } else {
                point.setEnabled(false);

            }
            point_group_collapsing.addView(point);

        }
    }

    /**
     * 初始化fragment
     */
    private void initFragment() {
        fragments=new ArrayList<>();
        Fragment fragment1=new LianZaiFragment(mTitle);
        Fragment fragment2=new WanJieFragment(mTitle);
        Fragment fragment3=new WanJieFragment(mTitle);
        Fragment fragment4=new WanJieFragment(mTitle);
        Fragment fragment5=new WanJieFragment(mTitle);
        fragments.add(fragment1);
        fragments.add(fragment2);
        fragments.add(fragment3);
        fragments.add(fragment4);
        fragments.add(fragment5);
    }


    /**
     * 初始化tabLayout
     */
    private void initTabLayout() {

        FragmentAdapter fragmentAdapter=new FragmentAdapter(getSupportFragmentManager(),fragments,mTitle);
        mTabLayout.setTabsFromPagerAdapter(fragmentAdapter);
        mViewPager.setAdapter(fragmentAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }


    /**
     * 初始化侧边栏
     */
    private void initToggle() {
        //navigationView的headerView上面的控件点击事件
        View view=View.inflate(this,R.layout.nav_header_home_page,null);
        btn_iv_lonin = (ImageButton) view.findViewById(R.id.btn_iv_login);
        btn_iv_lonin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("btn_iv_login", "onclick");
                startActivity(new Intent(getBaseContext(), LoginActivity.class));
            }
        });


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView.addHeaderView(view);
        navigationView.setNavigationItemSelectedListener(this);



    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);

        } else {

            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_page, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setQueryHint("正在搜索");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();


        if (id == R.id.action_search) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * 侧边栏点击事件
     *
     * @param item
     * @return
     */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.sliding_shouye:

                break;
            case R.id.sliding_help:

                break;
            case R.id.sliding_history:

                break;
            case R.id.sliding_shoucang:

                break;
            case R.id.sliding_share:

                break;
            case R.id.sliding_setting:
                startActivity(new Intent(this, SettingActivity.class));
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }




}
