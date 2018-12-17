package com.wxtoplink.api4.constant;

/**
 * Created by 12852 on 2018/12/14.
 */

public enum  ResourceType {
    NORMAL(0L, "normal"),
    BASE(1L, "base"),
    INDEX_PAGE(2L, "indexPage"),
    WELCOME_PAGE(3L, "welcomePage"),
    LEAVE_PAGE(4L, "leavePage"),
    PICK_UP_PAGE(5L, "pickUpPage"),
    PUT_DOWN_PAGE(6L, "putDownPage");

    private long typeId;
    private String typeName;

    private ResourceType(long typeId, String param4) {
        this.typeId = typeId;
        this.typeName = typeName;
    }

    public long getTypeId() {
        return this.typeId;
    }

    public String getTypeName() {
        return this.typeName;
    }
}
