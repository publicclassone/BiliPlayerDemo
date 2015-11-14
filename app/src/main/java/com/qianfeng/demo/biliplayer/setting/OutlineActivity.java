package com.qianfeng.demo.biliplayer.setting;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.format.Formatter;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.qianfeng.demo.biliplayer.R;
import com.qianfeng.demo.biliplayer.util.ApnSwitchTest;
import com.qianfeng.demo.biliplayer.view.SettingItemCheckView;
import com.qianfeng.demo.biliplayer.view.SettingItemRedioButton;


public class OutlineActivity extends BaseActivity {

    private SharedPreferences sp;
    private SettingItemRedioButton fu_storage;
    private SettingItemRedioButton selfesetting;
    private SettingItemRedioButton zhu_storage;
    private SettingItemCheckView start_move;
    private SettingItemCheckView auto;
    private RadioGroup radiogroup;
    private String OUT="off_line";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outline);
        sp = getSharedPreferences("config", MODE_PRIVATE);
        initView();
        initViewGroup();
        initinternetsetting();
        initdown();

    }

    private void initdown() {
        boolean startdown = sp.getBoolean("startdown", false);
        if (startdown) {
            auto.setChecked(true);
        } else {
            auto.setChecked(false);
        }
    }

    private void initinternetsetting() {
        boolean startmoveinternet = sp.getBoolean("startmoveinternet", false);
        if (startmoveinternet) {
            start_move.setChecked(true);
            ApnSwitchTest.setMobileData(OutlineActivity.this,true);
        } else {
            start_move.setChecked(false);
            ApnSwitchTest.setMobileData(OutlineActivity.this,false);
        }
    }

    private void initViewGroup() {
        int downposition = sp.getInt("off_line", 0);
        setchecked(downposition,"off_line");
        zhu_storage.setDesc("/sdcard/sdcard0" + "(" + getSDcard() + ")");
        selfesetting.setDesc("/sdcard/sdcard0" + "(" + getSDcard() + ")");
        fu_storage.setDesc("不可用");
    }

    private void initView() {
        radiogroup = ((RadioGroup) findViewById(R.id.radiogroup));
        zhu_storage = ((SettingItemRedioButton) findViewById(R.id.zhu));
        fu_storage = ((SettingItemRedioButton) findViewById(R.id.fu));
        selfesetting = ((SettingItemRedioButton) findViewById(R.id.selfesetting));
        start_move = ((SettingItemCheckView) findViewById(R.id.start));
        auto = ((SettingItemCheckView) findViewById(R.id.auto));
    }

    private String getSDcard() {
        long totalSpace = Environment.getExternalStorageDirectory().getTotalSpace();
        long freeSpace = Environment.getExternalStorageDirectory().getFreeSpace();
        String t = Formatter.formatFileSize(this, totalSpace);
        String f = Formatter.formatFileSize(this, freeSpace);
        String total = f + "/" + t;
        return total;
    }

    public void clickall(View v) {
        switch (v.getId()) {
            case R.id.zhu:
                setchecked(0, "off_line");
                break;
            case R.id.fu:
                setchecked(1, "off_line");
                break;
            case R.id.selfesetting:
                setchecked(2, "off_line");
                break;
            case R.id.start:
                boolean check = !start_move.isChecked();
                start_move.setChecked(check);
                sp.edit().putBoolean("startmoveinternet", check).commit();
                if (check){
                    ApnSwitchTest.setMobileData(OutlineActivity.this,true);
                    Toast.makeText(OutlineActivity.this,"移动梦网已开启",Toast.LENGTH_SHORT).show();
                }else{
                    ApnSwitchTest.setMobileData(OutlineActivity.this,false);
                    Toast.makeText(OutlineActivity.this,"移动梦网已关闭",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.auto:
                boolean checkauto = !auto.isChecked();
                auto.setChecked(checkauto);
                sp.edit().putBoolean("startdown", checkauto).commit();
                break;
        }
    }

    public void setchecked(int index,String spString) {
        sp.edit().putInt(spString, index).commit();
        switch (index) {
            case 0:
                zhu_storage.setChecked(true);
                fu_storage.setChecked(false);
                selfesetting.setChecked(false);
                break;
            case 1:
                fu_storage.setChecked(true);
                selfesetting.setChecked(false);
                zhu_storage.setChecked(false);
                break;
            case 2:
                selfesetting.setChecked(true);
                zhu_storage.setChecked(false);
                fu_storage.setChecked(false);
                break;
        }
    }

}
