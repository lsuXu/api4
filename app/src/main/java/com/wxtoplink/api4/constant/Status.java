package com.wxtoplink.api4.constant;

/**
 * Created by 12852 on 2018/12/14.
 */

public enum Status {
    DISABLE(-1),
    ENABLE(0),
    DOWNLOADING(1),
    DOWNLOAD_SUCCESS(2),
    DOWNLOAD_ERROR(3);

    private long statusId;

    private Status(int statusId) {
        this.statusId = statusId;
    }

    public long getStatusId() {
        return this.statusId;
    }
}
