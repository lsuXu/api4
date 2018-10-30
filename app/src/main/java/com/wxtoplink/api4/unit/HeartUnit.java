package com.wxtoplink.api4.unit;

import android.content.Context;
import android.util.Log;


import com.wxtoplink.api4.API4Manager;
import com.wxtoplink.api4.api4interface.DefaultData;
import com.wxtoplink.api4.bean.EventType;
import com.wxtoplink.api4.bean.Heart;
import com.wxtoplink.api4.bean.HeartResponse;
import com.wxtoplink.api4.bean.ResponseData;
import com.wxtoplink.api4.http.RetrofitHelper;
import com.wxtoplink.api4.util.ShaUtil;

import java.util.Date;

import okhttp3.MultipartBody;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * 心跳单元模块
 * Created by 12852 on 2018/10/29.
 */

public class HeartUnit {

    private static final String TAG = HeartUnit.class.getSimpleName();

    private static Subscriber subscriber = null;

    //获取心跳对象
    public static Heart getHeartArray(Context context, boolean isInitHeart){

        DefaultData defaultData = API4Manager.getInstance().getDefaultData();

        String eventType = isInitHeart? EventType.TYPE_1.toString():EventType.TYPE_2.toString() ;

        String mac = defaultData.getMac(context) ;

        String sendTime = String.valueOf(new Date().getTime()/1000);

        String versionCode = defaultData.getVersionCode(context);

        String appKey = defaultData.getAppKey();

        String sign = ShaUtil.encryptToSHA(String.format("%s%s%s%s%s",appKey,defaultData.getAPPSecret(),mac,sendTime,versionCode));

        String cid = defaultData.getCid();

        String deviceCode = defaultData.getDeviceCode();

        String eventData = defaultData.getEventData();

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
        if(subscriber != null){
            getHeartObservable(context,init).subscribe(subscriber);
        }else {
            getHeartObservable(context, init)
                    .subscribe(new Action1<ResponseData<HeartResponse>>() {
                        @Override
                        public void call(ResponseData<HeartResponse> responseResponseData) {
                            Log.i(TAG, String.format("response data = %s", responseResponseData.toString()));
                            API4Manager.getInstance().getApi4CallBack().setDeviceCode(responseResponseData.getData().getDeviceCode());
                        }
                    }, new Action1<Throwable>() {
                        @Override
                        public void call(Throwable throwable) {
                            throwable.printStackTrace();
                            API4Manager.getInstance().getApi4CallBack().onError(throwable);
                        }
                    });
        }
    }

    public static MultipartBody.Part getPart(String key, String value){
        return MultipartBody.Part.createFormData(key,value);
    }
}
