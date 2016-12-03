package org.iStat.api.iStatTransform.response;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseiStatTransformDataset {

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "cells")
    private List<ResponseiStatTransformCell> cells;

    public ResponseiStatTransformDataset() {}

    public ResponseiStatTransformDataset(String name,
            List<ResponseiStatTransformCell> cells) {
        this.name = name;
        this.cells = cells;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ResponseiStatTransformCell> getCells() {
        return cells;
    }

    public void setCells(List<ResponseiStatTransformCell> cells) {
        this.cells = cells;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("name", name).append("cells", cells).build();
    }

}
