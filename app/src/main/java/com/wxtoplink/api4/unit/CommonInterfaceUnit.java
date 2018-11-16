package com.wxtoplink.api4.unit;

import android.content.Context;

import com.wxtoplink.api4.API4Manager;
import com.wxtoplink.api4.api4interface.API4Request;
import com.wxtoplink.api4.bean.Resource;
import com.wxtoplink.api4.bean.ResponseData;
import com.wxtoplink.api4.http.RetrofitHelper;
import com.wxtoplink.api4.util.EncryptionCheckUtil;
import com.wxtoplink.api4.util.GSONUtils;

import org.greenrobot.greendao.annotation.NotNull;

import java.io.File;
import java.util.Date;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;
import retrofit2.http.Part;
import retrofit2.http.Url;

/**
 * Created by 12852 on 2018/11/6.
 */

public class CommonInterfaceUnit {

    //获取通用接口
    public static Observable<Object> getCommonInterface(@Url String url , @Part MultipartBody.Part... responseData){
        return RetrofitHelper.getInstance().getApi4Services().api4CommonInterface(url,responseData);
    }

    //获取标准通用数据接口，携带APP基础数据（数据放在eventData里面）
    private static Observable<ResponseData<Object>> getStandardCommonInterfaceObserver(Object obj, Context context){

        API4Request api4Request = API4Manager.getInstance().getAPI4Request();

        if(api4Request == null){
            return Observable.empty();
        }

        String mac = api4Request.getMac(context) ;

        String sendTime = String.valueOf(new Date().getTime()/1000);

        String versionCode = api4Request.getVersionCode(context);

        String appKey = api4Request.getAppKey();

        String sign = EncryptionCheckUtil.encryptToSHA(String.format("%s%s%s%s%s",appKey, api4Request.getAPPSecret(),mac,sendTime,versionCode));

        String deviceCode = api4Request.getDeviceCode();

        return RetrofitHelper.getInstance().getApi4Services().api4StandardCommonInterface(

                getPart("mac",mac),
                getPart("versionCode",versionCode),
                getPart("deviceCode",deviceCode),
                getPart("sendTime",sendTime),
                getPart("appKey",appKey),
                getPart("sign",sign),
                getPart("eventData", GSONUtils.toJson(obj)))
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io());
    }

    //标准通用接口
    public static void standardCommonInterface(@NotNull Object obj,@NotNull Context context,@NotNull Observer observer){
        getStandardCommonInterfaceObserver(obj,context).subscribe(observer);
    }

    private static MultipartBody.Part getPart(String key, String value){
        return MultipartBody.Part.createFormData(key,value);
    }

}
