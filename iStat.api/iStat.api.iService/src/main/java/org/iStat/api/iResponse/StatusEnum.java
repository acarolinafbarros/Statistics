package org.iStat.api.iResponse;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum StatusEnum {

    SUCCESS(1000, "SUCCESS"),
    UNSUCCESS(1001, "UNSUCCESS"),
    UNEXPECTED(9999, "UNEXPECTED");

    private int code;

    @JsonIgnore
    private String message;

    private StatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @JsonValue
    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
