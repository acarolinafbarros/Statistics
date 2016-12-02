package org.iStat.api.iResponse.iStatCalc;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.iStat.api.iResponse.StatusEnum;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseiStatCalc {

    private StatusEnum status;

    @JsonProperty(value = "value")
    private Float result;

    public ResponseiStatCalc() {
        this(null, null);
    }

    public ResponseiStatCalc(Float result) {
        this(StatusEnum.Success, result);
    }

    public ResponseiStatCalc(StatusEnum status, Float result) {
        this.status = status;
        this.result = result;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public Float getResult() {
        return result;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public void setResult(Float result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("status", status).append("result", result).build();
    }

}
