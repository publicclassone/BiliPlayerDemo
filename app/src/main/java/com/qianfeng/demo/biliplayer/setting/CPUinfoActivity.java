package com.qianfeng.demo.biliplayer.setting;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.TextView;

import com.qianfeng.demo.biliplayer.R;
import com.qianfeng.demo.biliplayer.util.CPUtools;

/**
 * Created by Administrator on 2015/11/12.
 */
public class CPUinfoActivity extends AppCompatActivity{

    private TextView tv_phoneinfo, tv_androidinfo, tv_cpuinfo;
    private String[] version;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cpu_message);
        //获取手机型号
        tv_phoneinfo = (TextView) findViewById(R.id.tv_phoneinfo);
        tv_androidinfo = (TextView) findViewById(R.id.tv_androidinfo);
        tv_cpuinfo = (TextView) findViewById(R.id.tv_cpuinfo);
        tv_phoneinfo.setText("==" + Build.MODEL);
        version = CPUtools.getVersion();
        int max = CPUtools.getMaxCpuFreq();
        int min = CPUtools.getMinCpuFreq();
        String name = CPUtools.getCpuName();
        int cur = CPUtools.getCurCpuFreq();
        tv_androidinfo.setText("andorid" + version[1] + "\n" + version[3]);
        tv_cpuinfo.setText("Processor: " + name + "\n" + "CPU最大频率" + max + "\n" + "CPU最小频率" + min);


    }
}
