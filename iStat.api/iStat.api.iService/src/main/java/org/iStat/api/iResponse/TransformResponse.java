package org.iStat.api.iResponse;

import java.math.BigDecimal;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TransformResponse {

	private StatusEnum status;

    @JsonProperty(value = "value")
    private final BigDecimal result;

    public TransformResponse(BigDecimal result) {
        this.status = StatusEnum.Success;
        this.result = result;
    }

    public TransformResponse(StatusEnum status, BigDecimal result) {
        this.status = status;
        this.result = result;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public BigDecimal getResult() {
        return result;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("status", status).append("result", result).build();
    }
	
}
