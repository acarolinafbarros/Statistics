package org.iStat.api.response;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class ResponseiStat {

    private StatusEnum status;

    public ResponseiStat() {}

    public ResponseiStat(StatusEnum status) {
        this.status = status;
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
            .append("status", status).build();
    }

}
