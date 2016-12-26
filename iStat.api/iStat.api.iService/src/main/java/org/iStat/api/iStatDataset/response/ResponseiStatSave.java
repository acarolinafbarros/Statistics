package org.iStat.api.iStatDataset.response;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.iStat.api.iResponse.ResponseiStat;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseiStatSave extends ResponseiStat {

    @JsonProperty(value = "success", required=false)
    private Boolean success;

    public ResponseiStatSave() {}

    public ResponseiStatSave(Boolean success) {
        this.success = success;
    }

    public Boolean isSuccess() {
        return success;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("success", success).build();
    }

}
