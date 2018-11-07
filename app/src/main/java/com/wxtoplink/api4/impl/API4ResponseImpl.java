package com.wxtoplink.api4.impl;

import android.util.Log;

import com.wxtoplink.api4.adapt.SubscribeAdapt;
import com.wxtoplink.api4.api4interface.API4Response;
import com.wxtoplink.api4.bean.HeartResponse;
import com.wxtoplink.api4.bean.ResponseData;

import io.reactivex.Observer;


/**
 * Created by 12852 on 2018/10/29.
 */

public class API4ResponseImpl implements API4Response {

    private static final String TAG = API4ResponseImpl.class.getSimpleName();

    //心跳回调处理
    @Override
    public Observer<ResponseData<HeartResponse>> getHeartSubscribe() {
        return new SubscribeAdapt<ResponseData<HeartResponse>>(){
            @Override
            public void onError(Throwable e) {
                Log.e(TAG,String.format("heart response error:%s",e.getMessage()));
                e.printStackTrace();
                super.onError(e);
            }

            @Override
            public void onNext(ResponseData<HeartResponse> heartResponseResponseData) {
                Log.i(TAG,String.format("heart response ok : %s",heartResponseResponseData.toString()));
                super.onNext(heartResponseResponseData);
            }
        };
    }

    //文件上传回调处理
    @Override
    public Observer<ResponseData> getFileUploadObservable() {
        return new SubscribeAdapt<ResponseData>(){
            @Override
            public void onError(Throwable e) {
                Log.e(TAG,String.format("fileUpload response error:%s",e.getMessage()));
                e.printStackTrace();
                super.onError(e);
            }

            @Override
            public void onNext(ResponseData responseData) {
                Log.i(TAG,String.format("fileUpload response ok : %s",responseData.toString()));
                super.onNext(responseData);
            }
        };
    }

    //显示一些其他的运行过程中发生的错误，以及一些操作不当引起的错误（如上传了一个不存在的文件）
    @Override
    public void onError(Throwable throwable) {
        Log.e(TAG,String.format("other error or cached error: %s",throwable.getMessage()));
        throwable.printStackTrace();
    }
}
