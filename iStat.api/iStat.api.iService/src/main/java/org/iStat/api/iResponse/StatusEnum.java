package org.iStat.api.iResponse;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum StatusEnum {

    Success(200, "SUCESS");

    @JsonProperty(value = "code")
    private int code;

    @JsonProperty(value = "message")
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
