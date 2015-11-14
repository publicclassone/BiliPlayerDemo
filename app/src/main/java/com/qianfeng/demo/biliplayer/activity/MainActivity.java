package com.qianfeng.demo.biliplayer.activity;


import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import android.widget.TextView;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;
import com.qianfeng.demo.biliplayer.R;
import com.qianfeng.demo.biliplayer.fragment.RankingFragment;
import com.qianfeng.demo.biliplayer.fragment.RelevantVedioFragment;
import com.qianfeng.demo.biliplayer.fragment.VideoFragment;
import com.qianfeng.demo.biliplayer.fragment.commentFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager = null;
    private RadioGroup rg, RG_two;
    // private PagerTabStrip tabStrip=null;
    private PagerSlidingTabStrip tabs;
    private List<Fragment> list;
    private List<String> titlelist;
    private Toolbar bar;
    private RadioButton rb, quality;
    private AlertDialog dialog;
    private EditText et_comment;
    private Fragment fragment4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initActionBar();
        initFragment();
        initViewPagerTab();
        initRadioButtonListener();

    }

    private void initRadioButtonListener() {
        rg = (RadioGroup) findViewById(R.id.RG);

        final RadioButton rb_comment = (RadioButton) findViewById(R.id.rb_comment);
        final RadioButton share = (RadioButton) findViewById(R.id.rb_share);
        final RadioButton download = (RadioButton) findViewById(R.id.rb_download);
        final RadioButton rb_save = (RadioButton) findViewById(R.id.rb_save);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == rb_comment.getId()) {
                    initAlertdialog();
                } else if (checkedId == share.getId()) {
                    share();
                } else if (checkedId == download.getId()) {
                    download();
                } else if (checkedId == rb_save.getId()) {
                    Toast.makeText(getBaseContext(), "帮你收藏好了呦 (●'◡'●)", Toast.LENGTH_SHORT).show();
                }


            }
        });


    }

    private void download() {
        //隐藏第一个radiobutton 显示第二个radiobutton
        viewPager.setCurrentItem(1);
        rg.setVisibility(View.GONE);
        rg.setClickable(false);
        RG_two = (RadioGroup) findViewById(R.id.RG_two);
        RG_two.setVisibility(View.VISIBLE);
        RG_two.setClickable(true);

        RadioButton offline = (RadioButton) findViewById(R.id.rb_offline);
        quality = (RadioButton) findViewById(R.id.rb_quality);

        //离线列表页面
        offline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), offlineActivity.class);
                startActivity(intent);
            }
        });
        quality.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //创建popmenu
                createpopmenu();
            }
        });


    }

    private void createpopmenu() {
        PopupMenu popupMenu = new PopupMenu(this, quality);
        getMenuInflater().inflate(R.menu.popmenu, popupMenu.getMenu());
        popupMenu.show();

    }

    private void share() {


    }

    private void initAlertdialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final View alertdialog = View.inflate(this, R.layout.alertdialog, null);
        builder.setView(alertdialog);
        dialog = builder.create();
        builder.show();
        TextView tv = (TextView) alertdialog.findViewById(R.id.tv_ok);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_comment = (EditText) alertdialog.findViewById(R.id.et_comment);
                String comment = et_comment.getText().toString().trim();
//                Bundle bundle = new Bundle();
//                bundle.putString("comment", comment);
//
//                fragment4.setArguments(bundle);

                Toast.makeText(getBaseContext(), "ss", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent();
//                intent.setAction("edit_comment");
//
//                Log.e("Mainactivity", "====" + comment + "");
//                intent.putExtra("comment", comment);
//                sendBroadcast(intent);

//                startActivity(intent);
                dialog.dismiss();
            }
        });
    }

    private void initFragment() {
        list = new ArrayList<>();
        Fragment fragment = new RankingFragment();
        Fragment fragment2 = new VideoFragment();
        Fragment fragment3 = new RelevantVedioFragment();
        fragment4 = new commentFragment();


        list.add(fragment);
        list.add(fragment2);
        list.add(fragment3);
        list.add(fragment4);


    }

    private void initViewPagerTab() {
        titlelist = new ArrayList<>();
        titlelist.add("承包商排行");
        titlelist.add("视频详情");
        titlelist.add("相关视频");
        titlelist.add("评论");

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabs = (PagerSlidingTabStrip) findViewById(R.id.tabstrip);
        tabs.setIndicatorColor(Color.RED);
        tabs.setUnderlineHeight(2);
        tabs.setIndicatorHeight(2);
        tabs.setDividerColor(Color.WHITE);


//        tabStrip = (PagerTabStrip) findViewById(R.id.tabstrip);
//        tabStrip.setDrawFullUnderline(false);
//        tabStrip.setTabIndicatorColor(Color.RED);
//        tabStrip.setTextSpacing(200);


        viewPager.setAdapter(new MypagerFragmentAdapter(getSupportFragmentManager()));
        viewPager.setCurrentItem(0);
        tabs.setViewPager(viewPager);
    }

    private void initActionBar() {
        bar = ((Toolbar) findViewById(R.id.toolbar));
        setSupportActionBar(bar);
        bar.setNavigationIcon(R.drawable.ic_arrow_back_black);


        //ActionBar bar = getSupportActionBar();
        // bar.setDisplayHomeAsUpEnabled(true);
        // bar.setTitle("Bilibili");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView view = (SearchView) item.getActionView();
        view.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_helping) {

            Intent intent = new Intent(this, HelpsActivity.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.action_testing) {

            Intent intent = new Intent(this, PersonalDataActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    class MypagerFragmentAdapter extends FragmentPagerAdapter {

        public MypagerFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return (titlelist.size() > position) ? titlelist.get(position) : "";
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }


    }
}
