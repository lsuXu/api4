package com.wxtoplink.api4.sqlite.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by 12852 on 2018/10/30.
 */

@Entity
public class Btn {

    @Id(autoincrement = true)
    private Long id ;

    //点击时间，时间戳到秒
    private String clickTime ;

    //用户标识
    private String customerCode ;

    //点击按钮
    private String buttonCode ;

    //产品标识
    private String productCode ;

    //点击页面
    private String pageCode ;

    //数据状态，0未发送，1已发送
    private int state ;

    public int getState() {
        return this.state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getPageCode() {
        return this.pageCode;
    }

    public void setPageCode(String pageCode) {
        this.pageCode = pageCode;
    }

    public String getProductCode() {
        return this.productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getButtonCode() {
        return this.buttonCode;
    }

    public void setButtonCode(String buttonCode) {
        this.buttonCode = buttonCode;
    }

    public String getCustomerCode() {
        return this.customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getClickTime() {
        return this.clickTime;
    }

    public void setClickTime(String clickTime) {
        this.clickTime = clickTime;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Generated(hash = 1999131576)
    public Btn(Long id, String clickTime, String customerCode, String buttonCode,
            String productCode, String pageCode, int state) {
        this.id = id;
        this.clickTime = clickTime;
        this.customerCode = customerCode;
        this.buttonCode = buttonCode;
        this.productCode = productCode;
        this.pageCode = pageCode;
        this.state = state;
    }

    @Generated(hash = 308065284)
    public Btn() {
    }
}
