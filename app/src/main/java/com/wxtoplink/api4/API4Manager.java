package com.wxtoplink.api4;

import android.content.Context;

import com.wxtoplink.api4.api4interface.API4Response;
import com.wxtoplink.api4.api4interface.API4Request;
import com.wxtoplink.api4.sqlite.InteractiveDataBuilder;
import com.wxtoplink.api4.sqlite.SqliteOperateUtil;
import com.wxtoplink.api4.unit.FileUploadUnit;
import com.wxtoplink.api4.unit.HeartUnit;
import com.wxtoplink.api4.unit.UncaughtExceptionUnit;
import com.wxtoplink.api4.unit.CommonInterfaceUnit;

import java.io.File;

import io.reactivex.Observable;
import io.reactivex.Observer;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import retrofit2.http.Part;
import retrofit2.http.Url;

/**
 * API4主要提供模块：
 * 1.文件上传模块（日志，批量数据）
 * 2.心跳模块（心跳数据获取，心跳上传）
 * 3.异常模块（异常数据获取，保存日常信息，获取异常信息的包装bean）
 * Created by 12852 on 2018/10/29.
 */

public final class API4Manager {

    public static final String TAG = API4Manager.class.getSimpleName();

    public static final MediaType MEDIA_TYPE_FORM = MediaType.parse("multipart/form-data");//MediaType

    private boolean init = false ,ignoreCheck = false;
    //数据回调对象
    private API4Response api4Response;

    //不同设备传入的一些基本数据
    private API4Request api4Request;


    //初始化，必须先初始化，才能调用其他方法，否则会引发异常,可以重复初始化
    public synchronized void init(API4Response api4Response, API4Request api4Request, Context context){
        init(api4Response,api4Request,context,false);
    }

    //初始化，必须先初始化，才能调用其他方法，否则会引发异常,可以重复初始化
    public synchronized void init(API4Response api4Response, API4Request api4Request, Context context,boolean ignoreCheck){
        init = true ;
        this.ignoreCheck = ignoreCheck ;
        this.api4Response = api4Response;
        this.api4Request = api4Request;
        SqliteOperateUtil.getInstance().init(context);
        checkInit();
    }

    public API4Response getApi4Response(){
        return api4Response;
    }

    public API4Request getAPI4Request(){
        return api4Request;
    }

    //获取交互数据的构建器，用于查询需要上报的数据
    public InteractiveDataBuilder getInteractiveDataBuilder(){
        if(checkInit()){
            return new InteractiveDataBuilder();
        }else
            return null;
    }

    //获取心跳单元
    public HeartUnit getHeartUnit(){
        if(checkInit()) {
            return HeartUnit.getInstance();
        }
        return null ;
    }

    //上传文件接口（使用统一回调处理）
    public void uploadFile(File file, Context context){
        if(checkInit())
            FileUploadUnit.fileUpload(file,context);
    }

    //上传文件接口（使用统一回调处理）
    public void uploadFile(String filePath, Context context){
        if(checkInit())
            FileUploadUnit.fileUpload(filePath,context);
    }

    //上传文件接口（自定义回调处理）
    public void uploadFile(String filePath, Context context, Observer observer){
        if(checkInit())
            FileUploadUnit.fileUpload(filePath,context,observer);
    }

    //上传文件接口（自定义回调处理）
    public void uploadFile(File file, Context context, Observer observer){
        if(checkInit())
            FileUploadUnit.fileUpload(file,context,observer);
    }

    //标准返回接口，携带APP相关信息
    public void standCommonInterface(Object obj,Context context,Observer observer){
        CommonInterfaceUnit.standardCommonInterface(obj,context,observer);
    }

    //通用数据接口
    public Observable<Object> commonInterface(@Url String url , @Part MultipartBody.Part... commonDataPart){
        return CommonInterfaceUnit.getCommonInterface(url,commonDataPart);
    }

    //获取数据库操作工具
    public SqliteOperateUtil getSqliteOperateUtil(){
        return SqliteOperateUtil.getInstance();
    }

    //获取错误日志工具
    public UncaughtExceptionUnit getExceptionUnit(){
        return UncaughtExceptionUnit.getInstance();
    }

    //获取标准命名
    public StandardName getStandName(){
        return StandardName.getInstance();
    }

    //检查初始化
    private boolean checkInit(){
        if(ignoreCheck){
            return true ;
        }
        if(init){
            if(this.api4Request != null && this.api4Response != null) {
                return true;
            }else{
                init = false ;
                throw new IllegalArgumentException("Pass in a valid parameter when calling the init() method");
            }
        }else{
            throw new IllegalStateException("Call init() to initialize the API4Manager before using this method)");
        }
    }

    public static API4Manager getInstance(){
        return instance ;
    }

    private static final API4Manager instance = new API4Manager();

    private API4Manager() {}

}
