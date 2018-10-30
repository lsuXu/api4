package com.wxtoplink.api4.bean;

/**
 * Created by sg on 2017/6/23.
 * 事件类型
 */

public enum EventType {

    TYPE_1("001"),//初始化心跳，单条数据
    TYPE_2("002"),//心跳，单条数据
    TYPE_3("003"),//人脸数据，批量中使用
    TYPE_4("004"),//拿起放下数据（分为重力传感和光敏传感）
    TYPE_5("005"),//屏幕点击数据
    TYPE_6("006");//人体感应数据，（分为多普勒雷达和红外数据）

    private String type;

    EventType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}
