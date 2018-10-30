package com.wxtoplink.api4.sqlite.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by 12852 on 2018/10/30.
 */

@Entity
public class BodyInduction {

    @Id(autoincrement = true)
    private Long id ;

    //0：红外感应，1：多普勒雷达
    private int type ;

    //进入时间，时间戳到秒
    private String startTime ;

    //离开时间，时间戳到秒
    private String endTime ;

    //用户标识
    private String customerCode ;

    //数据状态，0未发送，1已发送
    private int state ;

    public int getState() {
        return this.state;
    }

    public void setState(int state) {
        this.state = state;
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

    @Generated(hash = 652040661)
    public BodyInduction(Long id, int type, String startTime, String endTime,
            String customerCode, int state) {
        this.id = id;
        this.type = type;
        this.startTime = startTime;
        this.endTime = endTime;
        this.customerCode = customerCode;
        this.state = state;
    }

    @Generated(hash = 1895175062)
    public BodyInduction() {
    }

}
