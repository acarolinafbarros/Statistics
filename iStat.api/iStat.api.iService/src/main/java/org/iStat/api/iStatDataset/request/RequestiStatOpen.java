package org.iStat.api.iStatDataset.request;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.iStat.api.iRequest.RequestiStat;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestiStatOpen extends RequestiStat {

    @JsonProperty(value = "name")
    private String name;

    public RequestiStatOpen() {}
    
    public RequestiStatOpen(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("name", name).build();
    }

}
