package com.wxtoplink.api4.bean;

/**
 * Created by 12852 on 2018/10/29.
 */

public final class ResponseData<T> {

    private int code ;

    private T data ;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseData{" +
                "code=" + code +
                ", data=" + data.toString() +
                '}';
    }
}
