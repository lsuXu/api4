package com.wxtoplink.api4.bean;

/**
 * 心跳类，包含初始化使用
 * Created by 12852 on 2018/10/29.
 */

public final class Heart {

    //事件类型
    private String eventType ;
    //设备蓝牙地址或mac地址，唯一标识
    private String mac ;
    //设备APP当前版本号
    private String versionCode ;
    //个推cid
    private String cid ;
    //设备编号
    private String deviceCode ;
    //数据发送时间
    private String sendTime ;
    //校验的key
    private String appKey ;
    //数据校验字符串
    private String sign ;
    //设备当前的一些数据，包含内容待定
    private Object eventData ;
    //设备当前的补丁包版本号
    private String tinkerId ;

    public Heart() {
    }

    public Heart(String eventType, String mac, String versionCode, String cid, String deviceCode, String sendTime, String appKey, String sign, Object eventData, String tinkerId) {
        this.eventType = eventType;
        this.mac = mac;
        this.versionCode = versionCode;
        this.cid = cid;
        this.deviceCode = deviceCode;
        this.sendTime = sendTime;
        this.appKey = appKey;
        this.sign = sign;
        this.eventData = eventData;
        this.tinkerId = tinkerId;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public Object getEventData() {
        return eventData;
    }

    public void setEventData(Object eventData) {
        this.eventData = eventData;
    }

    public String getTinkerId() {
        return tinkerId;
    }

    public void setTinkerId(String tinkerId) {
        this.tinkerId = tinkerId;
    }

    @Override
    public String toString() {
        return "Heart{" +
                "eventType='" + eventType + '\'' +
                ", mac='" + mac + '\'' +
                ", versionCode='" + versionCode + '\'' +
                ", cid='" + cid + '\'' +
                ", deviceCode='" + deviceCode + '\'' +
                ", sendTime='" + sendTime + '\'' +
                ", appKey='" + appKey + '\'' +
                ", sign='" + sign + '\'' +
                ", eventData=" + eventData +
                ", tinkerId='" + tinkerId + '\'' +
                '}';
    }
}
