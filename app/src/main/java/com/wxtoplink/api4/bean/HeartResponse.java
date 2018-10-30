package com.wxtoplink.api4.bean;

/**
 * Created by 12852 on 2018/10/29.
 */

public final class HeartResponse {

    private String deviceCode ;

    private Object redBookInfo ;

    private VersionInfo versionInfo ;

    public String getDeviceCode() {
        return deviceCode;
    }

    public Object getRedBookInfo() {
        return redBookInfo;
    }

    public VersionInfo getVersionInfo() {
        return versionInfo;
    }

    @Override
    public String toString() {
        return "HeartResponse{" +
                "deviceCode='" + deviceCode + '\'' +
                ", redBookInfo=" + redBookInfo.toString() +
                ", versionInfo=" + versionInfo.toString() +
                '}';
    }
}
