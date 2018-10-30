package com.wxtoplink.api4.http;


import com.wxtoplink.api4.bean.HeartResponse;
import com.wxtoplink.api4.bean.ResponseData;

import okhttp3.MultipartBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import rx.Observable;

/**
 * Created by 12852 on 2018/10/29.
 */

public interface API4Services {

    //心跳，初始化接口
    @Multipart
    @POST("receive")
    Observable<ResponseData<HeartResponse>> api4Inti(@Part MultipartBody.Part... eventData);


    //批量数据（日志，文件，压缩包）
    @Multipart
    @POST("file_receive")
    Observable<ResponseData<Object>> api4FileUpload(@Part MultipartBody.Part filePart, @Part MultipartBody.Part... data);


}
