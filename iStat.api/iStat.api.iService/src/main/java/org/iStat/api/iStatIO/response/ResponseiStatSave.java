package org.iStat.api.iStatIO.response;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.iStat.api.response.ResponseiStat;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseiStatSave extends ResponseiStat {

    @JsonProperty(value = "success")
    private Boolean success;

    public ResponseiStatSave() {}

    public ResponseiStatSave(Boolean success) {
        this.success = success;
    }

    public Boolean getName() {
        return success;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("success", success).build();
    }

}
