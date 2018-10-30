package com.wxtoplink.api4.sqlite.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by 12852 on 2018/10/30.
 */

@Entity
public class Product {

    @Id(autoincrement = true)
    private Long id ;

    //0：重力传感，1：光敏传感
    private int type ;

    //拿起时间，时间戳到秒
    private String startTime ;

    //放下时间，时间戳到秒
    private String endTime ;

    //用户标识
    private String customerCode ;

    //产品编码
    private String productCode ;

    //点位编号
    private String pointCode ;

    //拿起时点位重量（重力传感）
    private String startWeight ;

    //放下时点位重量（重力传感）
    private String endWeight ;

    //数据状态，0未发送，1已发送
    private int state ;

    public int getState() {
        return this.state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getEndWeight() {
        return this.endWeight;
    }

    public void setEndWeight(String endWeight) {
        this.endWeight = endWeight;
    }

    public String getStartWeight() {
        return this.startWeight;
    }

    public void setStartWeight(String startWeight) {
        this.startWeight = startWeight;
    }

    public String getPointCode() {
        return this.pointCode;
    }

    public void setPointCode(String pointCode) {
        this.pointCode = pointCode;
    }

    public String getProductCode() {
        return this.productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getCustomerCode() {
        return this.customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getEndTime() {
        return this.endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStartTime() {
        return this.startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Generated(hash = 1025172575)
    public Product(Long id, int type, String startTime, String endTime,
            String customerCode, String productCode, String pointCode,
            String startWeight, String endWeight, int state) {
        this.id = id;
        this.type = type;
        this.startTime = startTime;
        this.endTime = endTime;
        this.customerCode = customerCode;
        this.productCode = productCode;
        this.pointCode = pointCode;
        this.startWeight = startWeight;
        this.endWeight = endWeight;
        this.state = state;
    }

    @Generated(hash = 1890278724)
    public Product() {
    }
}
