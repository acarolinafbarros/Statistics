package org.iStat.api.iStatIO.request;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestiStatIOOpen {

    @JsonProperty(value = "name")
    private String name;

    public RequestiStatIOOpen() {}

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("name", name).build();
    }

}
