package com.wxtoplink.api4.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.DisplayMetrics;

/**
 * 获取设备信息的工具类
 * Created by 12852 on 2018/10/31.
 */

public class DeviceUtil {

    /**
     * // 屏幕密度DPI（每寸像素 120 / 160 / 240 / 320 / 480 /640）
     *
     * @param context
     * @return
     */
    public static int getScreenDensityDpi(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return displayMetrics.densityDpi;
    }


    /**
     * 获取屏幕宽度
     *
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return displayMetrics.widthPixels;
    }

    /**
     * 获取屏幕高度
     *
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return displayMetrics.heightPixels;
    }


    //获取系统版本号
    public static String getSystemVersion(){
        return Build.VERSION.RELEASE;
    }

    //获取系统信息
    public static String getDeviceBrand(){
        return Build.MODEL;
    }

}
