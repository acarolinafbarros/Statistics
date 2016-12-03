package org.iStat.api.iStatTransform.request;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestiStatCalcDatasetTransform {

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "cells")
    private List<RequestiStatCalcCellTransform> cells;

    public RequestiStatCalcDatasetTransform(){
    }
    
    public RequestiStatCalcDatasetTransform(String name,
            List<RequestiStatCalcCellTransform> cells) {
        this.name = name;
        this.cells = cells;
    }

    public String getName() {
        return name;
    }

    public List<RequestiStatCalcCellTransform> getCells() {
        return cells;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("name", name).append("cells", cells).build();
    }

}
