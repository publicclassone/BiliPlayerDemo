package com.qianfeng.demo.biliplayer.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.qianfeng.demo.biliplayer.R;


public class SettingItemCheckView extends RelativeLayout {

	private static final String NAMESPACE = "http://schemas.android.com/apk/res-auto";

	private TextView tvTitle;
	private TextView tvDesc;
	private CheckBox cbStatus;

	private String mTitle;
	private String mDescOn;
	private String mDescOff;

	public SettingItemCheckView(Context context) {
		super(context);
		init();	}

	public SettingItemCheckView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mTitle = attrs.getAttributeValue(NAMESPACE, "content");
		mDescOn = attrs.getAttributeValue(NAMESPACE, "desc_on");
		mDescOff = attrs.getAttributeValue(NAMESPACE, "desc_off");
		init();	}

	public SettingItemCheckView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	private void init() {
		View.inflate(getContext(), R.layout.view_checkview_item, this);
		
		tvTitle = (TextView) findViewById(R.id.tv_title);
		tvDesc = (TextView) findViewById(R.id.tv_desc);
		cbStatus = (CheckBox) findViewById(R.id.cb_status);
		setTitle(mTitle);
	}
	
	public void setTitle(String title){
		tvTitle.setText(title);
	}

	public void setDesc(String desc){
		tvDesc.setText(desc);
	}
	public boolean isChecked(){
		return cbStatus.isChecked();
	}
	public void setChecked(boolean check){
		cbStatus.setChecked(check);
		
		if (check) {
			setDesc(mDescOn);
		}else{
			setDesc(mDescOff);
		}
	}
}
