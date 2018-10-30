package com.wxtoplink.api4.api4interface;

/**
 * Created by 12852 on 2018/10/29.
 */

public interface API4CallBack {

    void setDeviceCode(String deviceCode);

    void onError(Throwable throwable);
}
