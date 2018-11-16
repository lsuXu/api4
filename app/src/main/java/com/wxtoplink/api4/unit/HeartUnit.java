package com.wxtoplink.api4.unit;

import android.content.Context;


import com.wxtoplink.api4.API4Manager;
import com.wxtoplink.api4.api4interface.API4Request;
import com.wxtoplink.api4.api4interface.API4Response;
import com.wxtoplink.api4.bean.EventType;
import com.wxtoplink.api4.bean.Heart;
import com.wxtoplink.api4.http.RetrofitHelper;
import com.wxtoplink.api4.util.EncryptionCheckUtil;
import com.wxtoplink.api4.util.GSONUtils;

import java.util.Date;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;

/**
 * 心跳单元模块
 * Created by 12852 on 2018/10/29.
 */

public class HeartUnit {

    private static final String TAG = HeartUnit.class.getSimpleName();

    //获取心跳对象
    public Heart getHeartArray(Context context, boolean isInitHeart){

        API4Request api4Request = API4Manager.getInstance().getAPI4Request();

        if(api4Request == null){
            return new Heart();
        }

        String eventType = isInitHeart? EventType.TYPE_1.toString():EventType.TYPE_2.toString() ;

        String mac = api4Request.getMac(context) ;

        String sendTime = String.valueOf(new Date().getTime()/1000);

        String versionCode = api4Request.getVersionCode(context);

        String appKey = api4Request.getAppKey();

        String sign = EncryptionCheckUtil.encryptToSHA(String.format("%s%s%s%s%s",appKey, api4Request.getAPPSecret(),mac,sendTime,versionCode));

        String cid = api4Request.getCid();

        String deviceCode = api4Request.getDeviceCode();

        String tinkerId = api4Request.getTinkerId();

        Map eventData = api4Request.getEventData(isInitHeart);

        return new Heart(eventType,mac,versionCode,cid,deviceCode,sendTime,appKey,sign,eventData,tinkerId);
    }

    //生成上报的心跳Observable对象
    public Observable getHeartObservable(Context context , boolean init){
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
                getPart("eventData", GSONUtils.toJson(heart.getEventData())),
                getPart("tinkerId",heart.getTinkerId()))
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io());

    }

    //上传心跳
    public void sendHeart(Context context, boolean init){
        API4Response api4Response = API4Manager.getInstance().getApi4Response() ;
        if(api4Response != null) {

            getHeartObservable(context, init)
                    .subscribe(api4Response.getHeartSubscribe());
        }
    }

    private static MultipartBody.Part getPart(String key, String value){
        return MultipartBody.Part.createFormData(key,value);
    }

    private HeartUnit(){};

    private static final HeartUnit instance = new HeartUnit();

    public static HeartUnit getInstance(){
        return instance;
    };
}
