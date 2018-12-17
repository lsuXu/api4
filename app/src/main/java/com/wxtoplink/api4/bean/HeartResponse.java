package com.wxtoplink.api4.bean;

import java.util.List;

/**
 * Created by 12852 on 2018/10/29.
 */

public final class HeartResponse {

    private String deviceCode;

    private Object redBookInfo;

    private VersionInfo versionInfo;

    private List<Resource> resource;

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public Object getRedBookInfo() {
        return redBookInfo;
    }

    public void setRedBookInfo(Object redBookInfo) {
        this.redBookInfo = redBookInfo;
    }

    public VersionInfo getVersionInfo() {
        return versionInfo;
    }

    public void setVersionInfo(VersionInfo versionInfo) {
        this.versionInfo = versionInfo;
    }

    public List<Resource> getResource() {
        return resource;
    }

    public void setResource(List<Resource> resource) {
        this.resource = resource;
    }

    @Override
    public String toString() {
        return "HeartResponse{" +
                "deviceCode='" + deviceCode + '\'' +
                ", redBookInfo=" + redBookInfo +
                ", versionInfo=" + versionInfo +
                ", resource=" + resource +
                '}';
    }
}
