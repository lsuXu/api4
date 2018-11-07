package com.wxtoplink.api4.api4interface;

import com.wxtoplink.api4.bean.HeartResponse;
import com.wxtoplink.api4.bean.ResponseData;

import io.reactivex.Observer;

/**
 * Created by 12852 on 2018/10/29.
 */

public interface API4Response {

    Observer<ResponseData<HeartResponse>> getHeartSubscribe();

    Observer<ResponseData> getFileUploadObservable();

    void onError(Throwable throwable);
}
