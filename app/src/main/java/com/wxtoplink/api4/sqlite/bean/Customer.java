package com.wxtoplink.api4.sqlite.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by 12852 on 2018/10/30.
 */

@Entity
public class Customer {

    @Id(autoincrement = true)
    private Long id ;

    //进入时间，时间戳到秒
    private String startTime ;

    //离开时间，时间戳到秒
    private String endTime ;

    //用户标识
    private String customerCode ;

    //性别，女：0， 男：1
    private int sex ;

    //年龄
    private int age ;

    //人脸图片名称
    private String imgName ;

    //数据状态，0未发送，1已发送
    private int state ;

    public int getState() {
        return this.state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getImgName() {
        return this.imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSex() {
        return this.sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
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

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer(String startTime, String endTime, String customerCode, int sex, int age, String imgName) {
        this.id = null ;
        this.startTime = startTime;
        this.endTime = endTime;
        this.customerCode = customerCode;
        this.sex = sex;
        this.age = age;
        this.imgName = imgName;
        this.state = 0;
    }

    @Generated(hash = 146024361)
    public Customer(Long id, String startTime, String endTime, String customerCode,
            int sex, int age, String imgName, int state) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.customerCode = customerCode;
        this.sex = sex;
        this.age = age;
        this.imgName = imgName;
        this.state = state;
    }

    @Generated(hash = 60841032)
    public Customer() {
    }

}
