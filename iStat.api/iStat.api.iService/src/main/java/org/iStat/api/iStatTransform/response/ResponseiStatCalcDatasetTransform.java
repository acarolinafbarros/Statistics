package org.iStat.api.iStatTransform.response;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseiStatCalcDatasetTransform {

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "cells")
    private List<ResponseiStatCalcCellTransform> cells;

    public ResponseiStatCalcDatasetTransform() {}

    public ResponseiStatCalcDatasetTransform(String name,
            List<ResponseiStatCalcCellTransform> cells) {
        this.name = name;
        this.cells = cells;
    }

    public String getName() {
        return name;
    }

    public List<ResponseiStatCalcCellTransform> getCells() {
        return cells;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("name", name).append("cells", cells).build();
    }

}
