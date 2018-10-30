package com.wxtoplink.api4;

import android.content.Context;


import com.wxtoplink.api4.api4interface.API4CallBack;
import com.wxtoplink.api4.api4interface.DefaultData;
import com.wxtoplink.api4.unit.FileUploadUnit;
import com.wxtoplink.api4.unit.HeartUnit;

import java.io.File;

import okhttp3.MediaType;

/**
 * Created by 12852 on 2018/10/29.
 */

public final class API4Manager {

    public static final String TAG = API4Manager.class.getSimpleName();

    public static final MediaType MEDIA_TYPE_FORM = MediaType.parse("multipart/form-data");//MediaType

    private API4CallBack api4CallBack ;

    private DefaultData defaultData ;


    public void init(API4CallBack api4CallBack,DefaultData defaultData){
        this.api4CallBack = api4CallBack ;
        this.defaultData = defaultData ;
    }

    public API4CallBack getApi4CallBack(){
        return api4CallBack ;
    }

    public DefaultData getDefaultData(){
        return defaultData ;
    }

    //发送心跳
    public void sendHeart(Context context, boolean init){
        HeartUnit.sendHeart(context,init);
    }

    //上传文件接口
    public void uploadFile(File file, Context context){
        FileUploadUnit.fileUpload(file,context);
    }

    //上传文件接口
    public void uploadFile(String filePath, Context context){
        FileUploadUnit.fileUpload(filePath,context);
    }

    public static API4Manager getInstance(){
        return instance ;
    }

    private static final API4Manager instance = new API4Manager();

    private API4Manager() {}
}
