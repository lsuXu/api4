package com.wxtoplink.api4.api4interface;

import com.wxtoplink.api4.bean.HeartResponse;
import com.wxtoplink.api4.bean.ResponseData;

import rx.Subscriber;

/**
 * Created by 12852 on 2018/10/29.
 */

public interface API4Response {

    Subscriber<ResponseData<HeartResponse>> getHeartSubscribe();

    Subscriber<ResponseData> getFileUploadObservable();

    void onError(Throwable throwable);
}
