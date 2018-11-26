package com.wxtoplink.api4.api4interface;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.greenrobot.greendao.annotation.NotNull;

import java.util.Map;

/**
 * Created by 12852 on 2018/10/29.
 */

public interface API4Request {

    //获取设备编号
    @NonNull
    String getDeviceCode();
    //获取个推cid
    @NonNull
    String getCid();
    //获取mac地址
    @NonNull
    String getMac(Context context);
    //获取设备APP版本号
    @NonNull
    String getVersionCode(Context context);
    //获取设备的appKey
    @NonNull
    String getAppKey();
    //获取设备的appSecret
    @NonNull
    String getAPPSecret();
    //设备当前的一些数据，包含内容待定
    @NonNull
    Map getEventData(boolean init);

    //网络访问的基础URL
    @NonNull
    String getBaseUrl();

    //数据库文件存储目录
    @Nullable
    String getDBFolder();

    //数据库名称
    @NotNull
    String getDbName();

    //补丁包版本
    @NonNull
    String getTinkerId();
}
