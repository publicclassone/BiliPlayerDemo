package com.qianfeng.demo.biliplayer.setting;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.qianfeng.demo.biliplayer.R;
import com.qianfeng.demo.biliplayer.adapter.AdapterTanmu;
import com.qianfeng.demo.biliplayer.view.SettingClickView;
import com.qianfeng.demo.biliplayer.view.SettingItemView;

/**
 * Created by Administrator on 2015/11/11.
 */
public class TanScreenActivity extends BaseActivity  {
    private SettingClickView Tanmu;
    private String tanmuselect[] = {"自动选择（推荐）", "传统弹幕引擎", "烈焰弹幕使[Danmaku Flame Master]"};
    private String strokestyle[] = {"自动选择", "普通（最快）", "阴影（推荐）", "描边（最好）", "45投影（实验性）"};
    private SettingClickView TanMu;
    private SettingClickView border_style;
    private SettingItemView hid_danmu;
    private SettingItemView Yingjian_speed;
    private SettingItemView width_font;
    private SettingItemView start_chuti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tanmu);
        initView();
        initTanMu();
        initborder_style();
        initcheckStyle();
    }

    private void initcheckStyle() {
        setrequest(Yingjian_speed,"yingjianspeed");
        setrequest(hid_danmu,"hiddanmu");
        setrequest(width_font,"width_font");
        setrequest(start_chuti,"start_chuti");
    }


    private void  setrequest(SettingItemView view,String spname){
        boolean hiddanmu = sp.getBoolean(spname, false);
        if (hiddanmu){
            view.setChecked(true);
        }else{
            view.setChecked(false);
        }
    }

    private void initborder_style() {
        border_style.setTitle("弹幕边框样式");
        int border = sp.getInt("border_style", 0);
        border_style.setDesc(strokestyle[border]);
    }

    private void initTanMu() {
        int i = sp.getInt("tanmuyinqing", 0);
        Tanmu.setTitle("弹幕引擎选择");
        Tanmu.setDesc(tanmuselect[i]);

    }
    private void initView() {
        Tanmu = ((SettingClickView) findViewById(R.id.Tanmu_yinqing));
        border_style = ((SettingClickView) findViewById(R.id.tanmu_biankuang_yangshi));
        hid_danmu = ((SettingItemView) findViewById(R.id.hid_danmu));
        Yingjian_speed = ((SettingItemView) findViewById(R.id.yingjian_speed));
        width_font = ((SettingItemView) findViewById(R.id.width_font));
        start_chuti = ((SettingItemView) findViewById(R.id.start_chuti));
    }

    public void Tnmuclick(View v) {
        switch (v.getId()) {
            case R.id.Tanmu_yinqing:
                showDialog(TanScreenActivity.this,tanmuselect,Tanmu,"tanmuyinqing");
                break;
            case R.id.key_word:
                OpenActivity(TanScreenActivity.this, KeyworkActivity.class);
                break;
            case R.id.hid_danmu:
                chickedAllRestule(hid_danmu,"hiddanmu");
                break;
            case R.id.tanmu_biankuang_yangshi:
                showDialog(TanScreenActivity.this,strokestyle,border_style,"border_style");
                break;
            case R.id.yingjian_speed:
                chickedAllRestule(Yingjian_speed,"yingjianspeed");
                break;
            case R.id.width_font:
                chickedAllRestule(width_font, "width_font");
                break;
            case R.id.start_chuti:
                chickedAllRestule(start_chuti, "start_chuti");
                break;
        }
    }
    private void chickedAllRestule(SettingItemView view,String spname){
        if (view.isChecked()){
            view.setChecked(false);
            sp.edit().putBoolean(spname, false).commit();
        }else {
            view.setChecked(true);
            sp.edit().putBoolean(spname,true).commit();
        }
    }

}
