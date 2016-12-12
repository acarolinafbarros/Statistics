package org.iStat.api.iResponse;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(value = Include.NON_NULL)
public class ResponseStatusiStat {

    @JsonProperty(value = "message", required = false)
    private String message;

    @JsonProperty(value = "code")
    private StatusEnum status;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("status", status).append("msg", message).build();
    }

}
