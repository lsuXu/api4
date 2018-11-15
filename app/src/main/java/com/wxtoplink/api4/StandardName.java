package com.wxtoplink.api4;

import com.wxtoplink.api4.unit.DateUtil;

import java.util.Date;

/**
 * 命名规范
 * Created by 12852 on 2018/11/8.
 */

public class StandardName {

    private static final StandardName instance = new StandardName();

    private StandardName(){

    }

    public static StandardName getInstance(){
        return instance;
    }

    public String getCustomerCode(){
        return String.format("%s_%s",API4Manager.getInstance().getAPI4Request().getDeviceCode(), new Date().getTime()/1000);
    }

    public String getImageName(String customerCode){
        return String.format("%s_%s.jpg",customerCode,DateUtil.formatDate2String(new Date(),DateUtil.FORMAT_YYYYMMDDHHMMSS));
    }

    public String getDataFileName(){
        return String.format("%s_%s_data.txt",API4Manager.getInstance().getAPI4Request().getDeviceCode(),new Date().getTime()/1000);
    }

    public String getDailyFileName(){
        return String.format("%s_%s_log.txt",API4Manager.getInstance().getAPI4Request().getDeviceCode(),new Date().getTime()/1000);
    }

    public String getErrorFileName(){
        return String.format("%s_%s_error.txt",API4Manager.getInstance().getAPI4Request().getDeviceCode(),new Date().getTime()/1000);
    }
}
