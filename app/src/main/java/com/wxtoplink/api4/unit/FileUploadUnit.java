package com.wxtoplink.api4.unit;

import android.content.Context;

import com.wxtoplink.api4.API4Manager;
import com.wxtoplink.api4.api4interface.API4Request;
import com.wxtoplink.api4.http.RetrofitHelper;
import com.wxtoplink.api4.util.EncryptionCheckUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

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
            API4Request api4Request = API4Manager.getInstance().getAPI4Request();

            String mac = api4Request.getMac(context) ;

            String sendTime = String.valueOf(new Date().getTime()/1000);

            String versionCode = api4Request.getVersionCode(context);

            String appKey = api4Request.getAppKey();

            String sign = EncryptionCheckUtil.encryptToSHA(String.format("%s%s%s%s%s",appKey, api4Request.getAPPSecret(),mac,sendTime,versionCode));

            String deviceCode = api4Request.getDeviceCode();

            return RetrofitHelper.getInstance().getApi4Services()
                    .api4FileUpload(filePart,
                            getPart("mac", mac),
                            getPart("versionCode", versionCode),
                            getPart("sendTime", sendTime),
                            getPart("appKey", appKey),
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

    public static void fileUpload(File file,Context context,Observer observer){
        getUploadObservable(file,context).subscribe(observer);
    }

    public static void fileUpload(String filePath,Context context,Observer observer){
        fileUpload(new File(filePath),context,observer);
    }

    public static MultipartBody.Part getPart(String key, String value){
        return MultipartBody.Part.createFormData(key,value);
    }

}
