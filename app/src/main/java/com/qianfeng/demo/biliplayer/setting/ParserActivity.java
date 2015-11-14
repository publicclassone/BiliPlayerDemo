package com.qianfeng.demo.biliplayer.setting;

import android.os.Bundle;
import android.view.View;

import com.qianfeng.demo.biliplayer.R;
import com.qianfeng.demo.biliplayer.view.SettingClickView;
import com.qianfeng.demo.biliplayer.view.SettingItemRedioButton;

/**
 * Created by Administrator on 2015/11/11.
 */
public class ParserActivity extends BaseActivity {
    private SettingItemRedioButton automatic_selection;
    private SettingItemRedioButton System_hardware;
    private SettingItemRedioButton V3solution_preferred;
    private SettingItemRedioButton solution_preferred;
    private SettingItemRedioButton compatible_mode;

    private String[] circulationArray={"自动选择（推荐）","None（画质最平滑）","Non-Ref：平衡","Bi-Dir：可能出现马赛克","Non-Key:可能出现马赛克","All：性能最好，可能出现马赛克"};
    private String[] pixecl_formatArray={"自动选择（推荐）","16位RGB","32位RGB","YV12"};
    private String SPNAME="System_hardware";
    private SettingClickView circulation;
    private SettingClickView pixecl_format;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parser);
        initView();
        initGroupView();
        initsecond();
    }

    private void initsecond() {
        int circula = sp.getInt("circulation", 0);
        circulation.setDesc(circulationArray[circula]);
        circulation.setTitle("跳过循环滤波（loop-filter）");
        int pixecl = sp.getInt("pixecl_format", 0);
        pixecl_format.setDesc(pixecl_formatArray[pixecl]);
        pixecl_format.setTitle("像素格式");
    }

    private void initView() {
        automatic_selection = ((SettingItemRedioButton) findViewById(R.id.automatic_selection));
        System_hardware = ((SettingItemRedioButton) findViewById(R.id.System_hardware));
        V3solution_preferred = ((SettingItemRedioButton) findViewById(R.id.V3solution_preferred));
        solution_preferred = ((SettingItemRedioButton) findViewById(R.id.solution_preferred));
        compatible_mode = ((SettingItemRedioButton) findViewById(R.id.compatible_mode));
        circulation = ((SettingClickView) findViewById(R.id.text_circulation));
        pixecl_format = ((SettingClickView) findViewById(R.id.text_pixecl_format));
    }

    private void initGroupView() {
        automatic_selection.setDesc("根据机型和视频智能适配");
        System_hardware.setDesc("效率最好，支持部分手机系统");
        V3solution_preferred.setDesc("效率也很好，支持一部分楼上不支持的机型");
        solution_preferred.setDesc("比楼上差点，快要被抛弃啦");
        compatible_mode.setDesc("冬季暖手必备，偏重兼容性，使用所有手机系统");
        int hardware = sp.getInt("System_hardware", 0);
        setchecked(hardware, "System_hardware");
    }

    public void GroupClick(View v) {
        switch (v.getId()) {
            case R.id.automatic_selection:
                setchecked(0,"System_hardware");
                break;
            case R.id.System_hardware:
                setchecked(1,"System_hardware");
                break;
            case R.id.V3solution_preferred:
                setchecked(2,"System_hardware");
                break;
            case R.id.solution_preferred:
                setchecked(3,"System_hardware");
                break;
            case R.id.compatible_mode:
                setchecked(4,"System_hardware");
                break;
        }
    }

    public void setchecked(int index, String spName) {
        sp.edit().putInt(spName, index).commit();
        switch (index) {
            case 0:
                automatic_selection.setChecked(true);
                System_hardware.setChecked(false);
                V3solution_preferred.setChecked(false);
                solution_preferred.setChecked(false);
                compatible_mode.setChecked(false);
                break;
            case 1:
                automatic_selection.setChecked(false);
                System_hardware.setChecked(true);
                V3solution_preferred.setChecked(false);
                solution_preferred.setChecked(false);
                compatible_mode.setChecked(false);
                break;
            case 2:
                automatic_selection.setChecked(false);
                System_hardware.setChecked(false);
                V3solution_preferred.setChecked(true);
                solution_preferred.setChecked(false);
                compatible_mode.setChecked(false);
                break;
            case 3:
                automatic_selection.setChecked(false);
                System_hardware.setChecked(false);
                V3solution_preferred.setChecked(false);
                solution_preferred.setChecked(true);
                compatible_mode.setChecked(false);
                break;
            case 4:
                automatic_selection.setChecked(false);
                System_hardware.setChecked(false);
                V3solution_preferred.setChecked(false);
                solution_preferred.setChecked(false);
                compatible_mode.setChecked(true);
                break;
        }
    }
    public void Tnmuclick(View v){
        switch (v.getId()){
            case R.id.text_circulation:
                showDialog(ParserActivity.this,circulationArray,circulation,"circulation");
            break;
            case R.id.text_pixecl_format:
                showDialog(ParserActivity.this,pixecl_formatArray,pixecl_format,"pixecl_format");
                break;
        }
    }
}
