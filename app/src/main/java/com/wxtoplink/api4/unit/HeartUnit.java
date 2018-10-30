package com.wxtoplink.api4.unit;

import android.content.Context;


import com.wxtoplink.api4.API4Manager;
import com.wxtoplink.api4.api4interface.API4Request;
import com.wxtoplink.api4.bean.EventType;
import com.wxtoplink.api4.bean.Heart;
import com.wxtoplink.api4.http.RetrofitHelper;
import com.wxtoplink.api4.util.ShaUtil;

import java.util.Date;

import okhttp3.MultipartBody;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * 心跳单元模块
 * Created by 12852 on 2018/10/29.
 */

public class HeartUnit {

    private static final String TAG = HeartUnit.class.getSimpleName();

    //获取心跳对象
    public static Heart getHeartArray(Context context, boolean isInitHeart){

        API4Request API4Request = API4Manager.getInstance().getAPI4Request();

        String eventType = isInitHeart? EventType.TYPE_1.toString():EventType.TYPE_2.toString() ;

        String mac = API4Request.getMac(context) ;

        String sendTime = String.valueOf(new Date().getTime()/1000);

        String versionCode = API4Request.getVersionCode(context);

        String appKey = API4Request.getAppKey();

        String sign = ShaUtil.encryptToSHA(String.format("%s%s%s%s%s",appKey, API4Request.getAPPSecret(),mac,sendTime,versionCode));

        String cid = API4Request.getCid();

        String deviceCode = API4Request.getDeviceCode();

        String eventData = API4Request.getEventData();

        return new Heart(eventType,mac,versionCode,cid,deviceCode,sendTime,appKey,sign,eventData);
    }

    //生成上报的心跳Observable对象
    public static Observable getHeartObservable(Context context , boolean init){
        Heart heart = getHeartArray(context,init);

        return RetrofitHelper.getInstance().getApi4Services().api4Inti(
                getPart("eventType",heart.getEventType()),
                getPart("mac",heart.getMac()),
                getPart("versionCode",heart.getVersionCode()),
                getPart("cid",heart.getCid()),
                getPart("deviceCode",heart.getDeviceCode()),
                getPart("sendTime",heart.getSendTime()),
                getPart("appKey",heart.getAppKey()),
                getPart("sign",heart.getSign()),
                getPart("eventData",heart.getEventData()))
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io());

    }

    //上传心跳
    public static void sendHeart(Context context, boolean init){
        getHeartObservable(context, init)
                .subscribe(API4Manager.getInstance().getApi4Response().getHeartSubscribe());
    }

    public static MultipartBody.Part getPart(String key, String value){
        return MultipartBody.Part.createFormData(key,value);
    }
}
