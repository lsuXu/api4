package com.wxtoplink.api4.unit;

import android.content.Context;
import android.util.Log;

import com.wxtoplink.api4.API4Manager;
import com.wxtoplink.api4.adapt.SubscribeAdapt;
import com.wxtoplink.api4.api4interface.DefaultData;
import com.wxtoplink.api4.http.RetrofitHelper;
import com.wxtoplink.api4.util.ShaUtil;

import java.io.File;
import java.util.Date;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * Created by 12852 on 2018/10/30.
 */

public class FileUploadUnit {

    private static final String TAG = FileUploadUnit.class.getSimpleName();

    private static Subscriber subscriber = null;

    public static void getUploadObservable(String filePath, Context context){
        getUploadObservable(new File(filePath),context);
    }

    public static Observable getUploadObservable(File file, Context context){
        if(file.exists()){
            RequestBody requestBody = RequestBody.create(API4Manager.MEDIA_TYPE_FORM,file);
            MultipartBody.Part filePart = MultipartBody.Part.createFormData("eventFile",file.getName(),requestBody);
            DefaultData defaultData = API4Manager.getInstance().getDefaultData();

            String mac = defaultData.getMac(context) ;

            String sendTime = String.valueOf(new Date().getTime()/1000);

            String versionCode = defaultData.getVersionCode(context);

            String appKey = defaultData.getAppKey();

            String sign = ShaUtil.encryptToSHA(String.format("%s%s%s%s%s",appKey,defaultData.getAPPSecret(),mac,sendTime,versionCode));

            String deviceCode = defaultData.getDeviceCode();

            return RetrofitHelper.getInstance().getApi4Services()
                    .api4FileUpload(filePart,
                            getPart("mac",defaultData.getMac(context)),
                            getPart("versionCode",defaultData.getVersionCode(context)),
                            getPart("sendTime", String.valueOf(new Date().getTime()/1000)),
                            getPart("appKey",defaultData.getAppKey()),
                            getPart("sign",sign),
                            getPart("deviceCode",deviceCode))
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.io());
        }

        Log.e(TAG, String.format("File %s is not exist",file.getAbsolutePath()));
        return Observable.empty();
    }

    public static void fileUpload(String filePath, Context context){
        fileUpload(new File(filePath),context);
    }

    public static void fileUpload(File file, Context context){
        if(subscriber != null){
            getUploadObservable(file,context).subscribe(subscriber);
        }else {
            getUploadObservable(file, context).subscribe(new SubscribeAdapt(){
                @Override
                public void onError(Throwable e) {
                    super.onError(e);
                    API4Manager.getInstance().getApi4CallBack().onError(e);
                }

                @Override
                public void onNext(Object o) {
                    super.onNext(o);
                    Log.i(TAG, String.format("upload file resoult = %s",o));
                }
            });
        }
    }

    public static MultipartBody.Part getPart(String key, String value){
        return MultipartBody.Part.createFormData(key,value);
    }

}
