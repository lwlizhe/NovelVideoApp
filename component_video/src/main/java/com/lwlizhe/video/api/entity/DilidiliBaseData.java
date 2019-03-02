package com.lwlizhe.video.api.entity;

/**
 * Created by lwlizhe on 2019/2/14.
 */

public class DilidiliBaseData {

    private int errorCode;
    private String message;
    private String version;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

}
