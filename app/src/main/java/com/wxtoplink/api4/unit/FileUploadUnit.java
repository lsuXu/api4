package com.wxtoplink.api4.unit;

import android.content.Context;

import com.wxtoplink.api4.API4Manager;
import com.wxtoplink.api4.api4interface.API4Request;
import com.wxtoplink.api4.http.RetrofitHelper;
import com.wxtoplink.api4.util.EncryptionCheckUtil;

import java.io.File;
import java.io.FileNotFoundException;
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

    public static void getUploadObservable(String filePath, Context context){
        getUploadObservable(new File(filePath),context);
    }

    public static Observable getUploadObservable(File file, Context context){
        if(file.exists()){
            RequestBody requestBody = RequestBody.create(API4Manager.MEDIA_TYPE_FORM,file);
            MultipartBody.Part filePart = MultipartBody.Part.createFormData("eventFile",file.getName(),requestBody);
            API4Request API4Request = API4Manager.getInstance().getAPI4Request();

            String mac = API4Request.getMac(context) ;

            String sendTime = String.valueOf(new Date().getTime()/1000);

            String versionCode = API4Request.getVersionCode(context);

            String appKey = API4Request.getAppKey();

            String sign = EncryptionCheckUtil.encryptToSHA(String.format("%s%s%s%s%s",appKey, API4Request.getAPPSecret(),mac,sendTime,versionCode));

            String deviceCode = API4Request.getDeviceCode();

            return RetrofitHelper.getInstance().getApi4Services()
                    .api4FileUpload(filePart,
                            getPart("mac", API4Request.getMac(context)),
                            getPart("versionCode", API4Request.getVersionCode(context)),
                            getPart("sendTime", String.valueOf(new Date().getTime()/1000)),
                            getPart("appKey", API4Request.getAppKey()),
                            getPart("sign",sign),
                            getPart("deviceCode",deviceCode))
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.io());
        }

        API4Manager.getInstance().getApi4Response()
                .onError(new FileNotFoundException(String.format("Error uploading file,file %s does not exist",file.getAbsolutePath())));
        return Observable.empty();
    }

    public static void fileUpload(String filePath, Context context){
        fileUpload(new File(filePath),context);
    }

    public static void fileUpload(File file, Context context){
        getUploadObservable(file, context).subscribe(API4Manager.getInstance().getApi4Response().getFileUploadObservable());
    }

    public static void fileUpload(File file,Context context,Subscriber subscriber){
        getUploadObservable(file,context).subscribe(subscriber);
    }

    public static void fileUpload(String filePath,Context context,Subscriber subscriber){
        fileUpload(new File(filePath),context,subscriber);
    }

    public static MultipartBody.Part getPart(String key, String value){
        return MultipartBody.Part.createFormData(key,value);
    }

}
