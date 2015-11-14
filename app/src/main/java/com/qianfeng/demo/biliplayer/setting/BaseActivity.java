package com.qianfeng.demo.biliplayer.setting;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.qianfeng.demo.biliplayer.R;
import com.qianfeng.demo.biliplayer.adapter.AdapterTanmu;
import com.qianfeng.demo.biliplayer.view.SettingClickView;

public class BaseActivity extends AppCompatActivity {
    protected int theme = 0;
    protected ActionBar actionbar;
    protected SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        actionbar = getSupportActionBar();
//        actionbar.setDisplayHomeAsUpEnabled(true);
        sp = getSharedPreferences("config",MODE_PRIVATE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_base, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
    public void OpenActivity(Context context,Class<?> cls){
        Intent intent=new Intent(context,cls);
        startActivity(intent);
    }
    protected void showDialog(Context context,final String[] array,final SettingClickView clickview , final String spname) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final AlertDialog dialog = builder.create();
        View view = getLayoutInflater().inflate(R.layout.activity_tanmu_dialogstyle, null);
        dialog.setView(view);
        ListView listview = (ListView) view.findViewById(R.id.tanmu_listview);
        int spString = sp.getInt(spname, 0);
        AdapterTanmu adapter = new AdapterTanmu(array,context, spString);

        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sp.edit().putInt(spname, position).commit();
                clickview.setDesc(array[position]);
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    public void GitUp() {
        Intent intent = getIntent();
        overridePendingTransition(0, 0);//不设置进入退出动画
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
    }

}
