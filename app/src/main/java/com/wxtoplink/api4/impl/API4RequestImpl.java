package com.wxtoplink.api4.impl;

import android.content.Context;
import android.support.annotation.NonNull;

import com.wxtoplink.api4.api4interface.API4Request;


/**
 * Created by 12852 on 2018/10/29.
 */

public class API4RequestImpl implements API4Request {
    @NonNull
    @Override
    public String getDeviceCode() {
        return "";
    }

    @NonNull
    @Override
    public String getCid() {
        return "";
    }

    @NonNull
    @Override
    public String getMac(Context context) {
        return "cc:b8:a8:a8:8a:e8";
    }

    @NonNull
    @Override
    public String getVersionCode(Context context) {
        return "1.0.19.2";
    }

    @NonNull
    @Override
    public String getAppKey() {
        return "device";
    }

    @NonNull
    @Override
    public String getAPPSecret() {
        return "eXVOnw7DcB1krrCDd4vS";
    }

    @NonNull
    @Override
    public String getEventData() {
        return "";
    }

    @NonNull
    @Override
    public String getBaseUrl() {
        return "http://192.168.0.163:8010/api4/";
    }


}
