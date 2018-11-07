package com.wxtoplink.api4.bean;

/**
 * Created by 12852 on 2018/10/29.
 */

public final class VersionInfo {

    private String versionCode ;

    private String apkUrl ;

    private String updateTime ;

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
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
                "versionCode='" + versionCode + '\'' +
                ", apkUrl='" + apkUrl + '\'' +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }
}
