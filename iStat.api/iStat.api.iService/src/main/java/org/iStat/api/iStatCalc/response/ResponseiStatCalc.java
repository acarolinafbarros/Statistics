package org.iStat.api.iStatCalc.response;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.iStat.api.response.ResponseStatusiStat;
import org.iStat.api.response.ResponseiStat;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseiStatCalc extends ResponseiStat {

    @JsonProperty(value = "value")
    private Float result;

    public ResponseiStatCalc() {
        this(null, null);
    }

    public ResponseiStatCalc(Float result) {
        this(null, result);
    }

    public ResponseiStatCalc(ResponseStatusiStat status, Float result) {
        this.status = status;
        this.result = result;
    }

    public Float getResult() {
        return result;
    }

    public void setResult(Float result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("result", result).build();
    }

}
