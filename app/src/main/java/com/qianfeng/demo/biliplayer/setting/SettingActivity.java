package com.qianfeng.demo.biliplayer.setting;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.provider.CalendarContract;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.qianfeng.demo.biliplayer.R;
import com.qianfeng.demo.biliplayer.util.DataCleanManager;
import com.qianfeng.demo.biliplayer.util.SharedPreferrenceHelper;
import com.qianfeng.demo.biliplayer.util.Util;
import com.qianfeng.demo.biliplayer.view.SettingClickView;
import com.qianfeng.demo.biliplayer.view.SettingItemView;

public class SettingActivity extends BaseActivity {


    Handler mhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Toast.makeText(SettingActivity.this, "清理完毕", Toast.LENGTH_SHORT).show();
            ;
            cleancache.setClickable(true);
            cleancache.setFocusable(true);
        }
    };

    final String[] itemsplay = new String[]{"自动选择：根据机型智能适配", "流畅优先：节省流量", "高清优先：需要更多流量，以及更高端的机型"};
    final String[] itemsfinish = new String[]{"播完暂停：停止播放等待操作",
            "洗脑循环：自动循环播放当前视频",
            "自动换P：自动播放下一P视频",
            "自动换P(循环)：自动播放下一P视频，列表结束后从头开始",
            "播完退出：退出播放界面"};
    private SettingClickView scv_qingxi_select;
    private SettingClickView svc_playfinish_select;
    private SharedPreferences sp;
    private SettingItemView full_screen;
    private SettingItemView play_animation;
    private SettingItemView receiver_message;
    private SettingItemView flash_speed;
    private TextView cleancache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        sp = getSharedPreferences("config", MODE_PRIVATE);
//        actionbar.setDisplayHomeAsUpEnabled(true);
        initView();
        initQingXiselece();
        initPlayFinish();
        initfull();
        initplayanimation();
        initmessage();
        initspeed();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void initspeed() {
        boolean fullplay = sp.getBoolean("flashspeed", false);
        if (fullplay) {
            flash_speed.setChecked(true);
        } else {
            flash_speed.setChecked(false);
        }
        flash_speed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flash_speed.isChecked()) {
                    flash_speed.setChecked(false);
                    sp.edit().putBoolean("flashspeed", false).commit();
                } else {
                    flash_speed.setChecked(true);
                    sp.edit().putBoolean("flashspeed", true).commit();
                }
            }
        });
    }

    private void initmessage() {
        boolean fullplay = sp.getBoolean("receivemessage", false);
        if (fullplay) {
            receiver_message.setChecked(true);
        } else {
            receiver_message.setChecked(false);
        }
        receiver_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (receiver_message.isChecked()) {
                    receiver_message.setChecked(false);
                    sp.edit().putBoolean("receivemessage", false).commit();
                } else {
                    receiver_message.setChecked(true);
                    sp.edit().putBoolean("receivemessage", true).commit();
                }
            }
        });
    }

    private void initplayanimation() {
        boolean fullplay = sp.getBoolean("playanimation", false);
        if (fullplay) {
            play_animation.setChecked(true);
        } else {
            play_animation.setChecked(false);
        }
        play_animation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (play_animation.isChecked()) {
                    play_animation.setChecked(false);
                    sp.edit().putBoolean("playanimation", false).commit();
                } else {
                    play_animation.setChecked(true);
                    sp.edit().putBoolean("playanimation", true).commit();
                }
            }
        });
    }

    private void initfull() {
        boolean fullplay = sp.getBoolean("fullplay", true);
        if (fullplay) {
            full_screen.setChecked(true);
        } else {
            full_screen.setChecked(false);
        }
        full_screen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (full_screen.isChecked()) {
                    full_screen.setChecked(false);
                    sp.edit().putBoolean("fullplay", false).commit();
                } else {
                    full_screen.setChecked(true);
                    sp.edit().putBoolean("fullplay", true).commit();
                }
            }
        });
    }

    private void initPlayFinish() {
        int style = sp.getInt("playfinish", 0);// 读取保存的style
        svc_playfinish_select.setDesc(itemsfinish[style]);
        svc_playfinish_select.setTitle("播放完成后动作");
        svc_playfinish_select.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                showDialog(SettingActivity.this, itemsfinish, svc_playfinish_select, "playfinish");
            }
        });
    }

    private void initQingXiselece() {
        int style = sp.getInt("qingxidu", 0);// 读取保存的style
        scv_qingxi_select.setDesc(itemsplay[style]);
        scv_qingxi_select.setTitle("清晰度选择");
        scv_qingxi_select.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                showDialog(SettingActivity.this, itemsplay, scv_qingxi_select, "qingxidu");
            }
        });
    }

    private void initView() {
        scv_qingxi_select = (SettingClickView) findViewById(R.id.scv_qingxi_select);
        svc_playfinish_select = ((SettingClickView) findViewById(R.id.scv_playfinish_select));
        full_screen = ((SettingItemView) findViewById(R.id.full_screen));
        play_animation = ((SettingItemView) findViewById(R.id.play_animation));
        receiver_message = ((SettingItemView) findViewById(R.id.receive_message));
        flash_speed = ((SettingItemView) findViewById(R.id.flash_speed));
        cleancache = ((TextView) findViewById(R.id.cleancache));
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putInt("theme", theme);
    }



    public void helpme(View v) {
        OpenActivity(SettingActivity.this, HelpActivity.class);
    }

    public void startsetting(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog dialog = builder.create();
        View view = getLayoutInflater().inflate(R.layout.startsetting, null);
        Button yes = (Button) view.findViewById(R.id.yes);
        Button no = (Button) view.findViewById(R.id.no);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                sp.edit().clear().commit();
                GitUp();

            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                ;
            }
        });
        dialog.setView(view);
        dialog.show();
        ;
    }

    public void cleancache(View v) {
        DataCleanManager.cleanInternalCache(SettingActivity.this);
        mhandler.sendEmptyMessageDelayed(0, 1000);
        cleancache.setClickable(false);
        cleancache.setFocusable(false);
    }

    public void outlinesetting(View v) {
        OpenActivity(SettingActivity.this, OutlineActivity.class);
    }

    public void TanMuSetting(View V) {
        OpenActivity(SettingActivity.this, TanScreenActivity.class);
    }

    public void parserscreat(View v) {
        OpenActivity(SettingActivity.this, ParserActivity.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_setting, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
