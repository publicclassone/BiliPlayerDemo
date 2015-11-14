package com.qianfeng.demo.biliplayer.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.qianfeng.demo.biliplayer.R;

/**
 * Created by Administrator on 2015/11/13.
 */
public class SharedPreferrenceHelper {
    private static final String THEME = "theme";

    public static void settheme(Context context, int theme) {
        SharedPreferences sp = context.getSharedPreferences("demo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(THEME, theme);
        editor.apply();
    }

    public static int gettheme(Context context) {
        SharedPreferences sp = context.getSharedPreferences("demo", Context.MODE_PRIVATE);
        return sp.getInt(THEME, 1);
    }

    public static int getAppTheme(Context context) {
        int value = SharedPreferrenceHelper.gettheme(context);
        switch (value) {
            case 1:
                return R.style.AppTheme;
            case 2:
                return R.style.AppTheme;
            default:
                return R.style.AppTheme;
        }
    }

    public static void switchAppTheme(Context context) {
        int value = SharedPreferrenceHelper.gettheme(context);
        switch (value) {
            case 1:
                SharedPreferrenceHelper.settheme(context, 2);
                break;
            case 2:
                SharedPreferrenceHelper.settheme(context, 1);
                break;
            default:
                SharedPreferrenceHelper.settheme(context, 1);
                break;
        }
    }
}
