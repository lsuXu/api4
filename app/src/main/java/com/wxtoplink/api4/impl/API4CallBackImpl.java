package com.wxtoplink.api4.impl;

import android.util.Log;

import com.wxtoplink.api4.api4interface.API4CallBack;


/**
 * Created by 12852 on 2018/10/29.
 */

public class API4CallBackImpl implements API4CallBack {

    @Override
    public void setDeviceCode(String deviceCode) {
        Log.i("API4CallBackImpl","deviceCode =" + deviceCode);
    }

    @Override
    public void onError(Throwable throwable) {
        Log.e("API4CallBackImpl","error:" + throwable.getMessage());
        throwable.printStackTrace();
    }
}
