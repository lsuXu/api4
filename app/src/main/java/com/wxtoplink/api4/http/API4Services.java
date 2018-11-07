package com.wxtoplink.api4.http;

import com.wxtoplink.api4.bean.HeartResponse;
import com.wxtoplink.api4.bean.ResponseData;

import io.reactivex.Observable;
import io.reactivex.Observer;
import okhttp3.MultipartBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Url;

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


    //资源文件更新失败列表上传
    @Multipart
    @POST("single_receive")
    Observable<ResponseData<Object>> api4StandardCommonInterface(@Part MultipartBody.Part... eventData);

    //通用接口
    @Multipart
    @POST
    Observable<Object> api4CommonInterface(@Url String url ,@Part MultipartBody.Part... commonData);

}
