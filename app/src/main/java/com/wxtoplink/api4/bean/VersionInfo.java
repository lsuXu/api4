package com.wxtoplink.api4.bean;

/**
 * Created by 12852 on 2018/10/29.
 */

public final class VersionInfo {

    private String versionInfo ;

    private String apkUrl ;

    private String updateTime ;

    public String getVersionInfo() {
        return versionInfo;
    }

    public void setVersionInfo(String versionInfo) {
        this.versionInfo = versionInfo;
    }

    public String getApkUrl() {
        return apkUrl;
    }

    public void setApkUrl(String apkUrl) {
        this.apkUrl = apkUrl;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "VersionInfo{" +
                "versionInfo='" + versionInfo + '\'' +
                ", apkUrl='" + apkUrl + '\'' +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }
}
