package com.qianfeng.demo.biliplayer.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.qianfeng.demo.biliplayer.R;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    private EditText userName;
    private EditText password;
    private Toolbar toolbar;
    private ImageView iv_22, iv_33;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        toolbar = (Toolbar) findViewById(R.id.toolbar_login);
        //toolbar.setTitle("登录");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_2x);

        toolbar.setNavigationOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        // Set up the login form.

        initUserName();
        initPassWord();


    }

    /**
     * 初始化用户密码设置
     */
    private void initPassWord() {
        iv_22 = (ImageView) findViewById(R.id.iv_22);
        iv_33 = (ImageView) findViewById(R.id.iv_33);
        password = (EditText) findViewById(R.id.password);
        //密码框焦点获取变化
        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    imageHint();
                } else {
                    imageOpen();
                }
            }
        });
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.length() < 6) {
                    // password.setErrorEnabled(true);
                    password.setError("密码不能少于6位");
                    password.setEnabled(true);
                } else {
                    //inputLayout_name.setErrorEnabled(false);
                    // password.setEnabled(false);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {


            }
        });
    }

    private void imageOpen() {
        iv_22.setImageResource(R.drawable.ic_22);
        iv_33.setImageResource(R.drawable.ic_33);
    }

    private void imageHint() {
        iv_22.setImageResource(R.drawable.ic_22_hide);
        iv_33.setImageResource(R.drawable.ic_33_hide);
    }

    /**
     * 初始化用户名设置
     */
    private void initUserName() {
        userName = (EditText) findViewById(R.id.userName);
        userName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 11 || s.length() < 11) {
                    userName.setEnabled(true);
                    userName.setError("请输入正确的手机号");
                } else if (s.length() == 11) {
                    //userName.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}

