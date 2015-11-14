package com.qianfeng.demo.biliplayer.activity;


import android.app.Activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.qianfeng.demo.biliplayer.R;

import java.util.Calendar;

public class PersonalDataActivity extends AppCompatActivity {

    private RadioButton takePhote, photechoose;
    private View view;
    private AlertDialog.Builder builder;
    private AlertDialog dialog;
    private Button btn_cancle;

    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_personal_data);

        iv = (ImageView) findViewById(R.id.iv_persional_head);

    }

    //注销
    public void Cancellation(View v) {
        this.finish();
    }


    //头像选择
    public void selecthead(View v) {

        builder = new AlertDialog.Builder(this);
        view = View.inflate(this, R.layout.headselect, null);
        builder.setView(view);
        dialog = builder.create();
        dialog.show();
        initSelectHead();
    }

    public void birthday(View v) {
        Calendar c = Calendar.getInstance();
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                TextView tv = (TextView) findViewById(R.id.tv_birthday);
                tv.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
            }
        }, c.get(Calendar.YEAR),
                c.get(Calendar.MONTH),
                c.get(Calendar.DAY_OF_MONTH)).show();

    }


    private void initSelectHead() {

        //调用系统相机
        takePhote = (RadioButton) view.findViewById(R.id.rb_take_photo);
        photechoose = (RadioButton) view.findViewById(R.id.rb_photo_choose);
        btn_cancle = (Button) view.findViewById(R.id.btn_cancle);


        Log.e("Mainactivity", "ss" + takePhote);
        takePhote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(getBaseContext(), "s", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 1);

                dialog.dismiss();
            }
        });

        //调用系统相册
        photechoose.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 2);
                dialog.dismiss();
            }
        });

        btn_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                    Toast.makeText(this, "内存卡不可用", Toast.LENGTH_SHORT).show();
                    return;
                }
                Bundle bundle = data.getExtras();
                Bitmap bitmap = (Bitmap) bundle.get("data");

                Log.e("Mainactivity", "ss+++++" + iv);
                Log.e("Mainactivity", "ss=====" + bitmap);
                iv.setImageBitmap(bitmap);

            }
        } else if (requestCode == 2) {
            if (resultCode == Activity.RESULT_OK) {
                Uri uri = data.getData();
                Cursor cursor = getContentResolver().query(uri, null, null, null, null);
                cursor.moveToNext();
                String imgNo = cursor.getString(0); // 图片编号
                String imgPath = cursor.getString(1); // 图片文件路径
                String imgSize = cursor.getString(2); // 图片大小
                String imgName = cursor.getString(3); // 图片文件名
                cursor.close();

                Bitmap bitmap = BitmapFactory.decodeFile(imgPath, null);
                iv.setImageBitmap(bitmap);
            }

        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_personal_data, menu);
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

        return super.onOptionsItemSelected(item);
    }
}
