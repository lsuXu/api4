package com.wxtoplink.api4.api4interface;

import com.wxtoplink.api4.sqlite.OperateType;

/**
 * Created by 12852 on 2018/11/22.
 */

public interface API4DBOperateCallBack {

    void operateData(String operateData , OperateType operateType);
}
