package com.wxtoplink.api4.bean;

/**
 * Created by 12852 on 2018/10/31.
 */

public class ErrorBean {

    //系统版本
    private String systemVersion ;

    //主板名称
    private String boardName ;

    //APP版本号
    private String versionCode ;

    //设备mac地址
    private String mac ;

    //屏幕密度
    private String densityDpi;

    //屏幕宽度
    private String screenWidth ;

    //屏幕高度
    private String screenHeight ;

    //错误信息的摘要
    private String errorTitle ;

    //详细错误信息
    private String errorMessage ;

    public String getSystemVersion() {
        return systemVersion;
    }

    public void setSystemVersion(String systemVersion) {
        this.systemVersion = systemVersion;
    }

    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getDensityDpi() {
        return densityDpi;
    }

    public void setDensityDpi(String densityDpi) {
        this.densityDpi = densityDpi;
    }

    public String getScreenWidth() {
        return screenWidth;
    }

    public void setScreenWidth(String screenWidth) {
        this.screenWidth = screenWidth;
    }

    public String getScreenHeight() {
        return screenHeight;
    }

    public void setScreenHeight(String screenHeight) {
        this.screenHeight = screenHeight;
    }

    public String getErrorTitle() {
        return errorTitle;
    }

    public void setErrorTitle(String errorTitle) {
        this.errorTitle = errorTitle;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public ErrorBean(String systemVersion, String boardName, String versionCode, String mac, String densityDpi, String screenWidth, String screenHeight, String errorTitle, String errorMessage) {
        this.systemVersion = systemVersion;
        this.boardName = boardName;
        this.versionCode = versionCode;
        this.mac = mac;
        this.densityDpi = densityDpi;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.errorTitle = errorTitle;
        this.errorMessage = errorMessage;
    }

    public ErrorBean() {
    }
}
