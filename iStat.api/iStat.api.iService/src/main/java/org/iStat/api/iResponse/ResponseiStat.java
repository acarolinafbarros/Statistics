package org.iStat.api.iResponse;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(value = Include.NON_NULL)
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
