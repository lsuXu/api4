package com.wxtoplink.api4.constant;

/**
 * Created by 12852 on 2018/12/5.
 */

public enum ResourceType {

    NORMAL(0,"normal"),
    BASE(1,"base"),
    INDEX_PAGE(2,"indexPage"),
    WELCOME_PAGE(3,"welcomePage"),
    LEAVE_PAGE(4,"leavePage"),
    PICK_UP_PAGE(5,"pickUpPage"),
    PICK_DOWN_PAGE(6,"pickDownPage") ;

    private ResourceType(long typeId,String typeName) {
        this.typeId = typeId;
        this.typeName = typeName ;
    }

    private long typeId;

    private String typeName ;

    public long getTypeId(){
        return typeId ;
    }

    public String getTypeName() {
        return typeName;
    }

}
