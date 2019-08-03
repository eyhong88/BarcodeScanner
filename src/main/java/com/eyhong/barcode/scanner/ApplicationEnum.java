package com.eyhong.barcode.scanner;

public enum ApplicationEnum {

    SCAN(1),
    ADD(2);

    private Integer appId;

    ApplicationEnum(Integer appId){
        this.appId = appId;
    }

    public Integer getAppId(){
        return this.appId;
    }
}
