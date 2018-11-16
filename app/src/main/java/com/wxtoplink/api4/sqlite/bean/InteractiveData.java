package com.wxtoplink.api4.sqlite.bean;

import java.util.List;

/**
 * Created by 12852 on 2018/10/30.
 */

public class InteractiveData<T> {

    private String eventType ;

    private List<T> data ;

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public InteractiveData() {
    }

    public InteractiveData(String eventType, List<T> data) {
        this.eventType = eventType;
        this.data = data;
    }
}
