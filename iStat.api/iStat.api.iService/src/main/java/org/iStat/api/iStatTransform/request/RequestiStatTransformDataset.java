package org.iStat.api.iStatTransform.request;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestiStatTransformDataset {

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "cells")
    private List<RequestiStatTransformCell> cells;

    public RequestiStatTransformDataset() {}

    public RequestiStatTransformDataset(String name,
            List<RequestiStatTransformCell> cells) {
        this.name = name;
        this.cells = cells;
    }

    public String getName() {
        return name;
    }

    public List<RequestiStatTransformCell> getCells() {
        return cells;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("name", name).append("cells", cells).build();
    }

}
