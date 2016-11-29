package org.iStat.api.iResponse;

public enum StatusEnum {
    Success(200, "SUCESS");

    private int code;

    private String message;

    private StatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
