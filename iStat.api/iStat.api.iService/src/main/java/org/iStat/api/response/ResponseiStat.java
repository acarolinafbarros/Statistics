package org.iStat.api.response;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseiStat {

    @JsonProperty(value = "status")
    protected ResponseStatusiStat status;

    public ResponseiStat() {}

    public ResponseiStat(ResponseStatusiStat status) {
        this.status = status;
    }

    public ResponseStatusiStat getStatus() {
        return status;
    }

    public void setStatus(ResponseStatusiStat status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("status", status).build();
    }

}
