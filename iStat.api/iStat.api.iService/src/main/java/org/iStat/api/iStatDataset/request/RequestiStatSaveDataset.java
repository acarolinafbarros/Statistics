package org.iStat.api.iStatDataset.request;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestiStatSaveDataset {

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "cells")
    private List<RequestiStatSaveCell> cells;

    public RequestiStatSaveDataset() {}

    public RequestiStatSaveDataset(String name,
            List<RequestiStatSaveCell> cells) {
        this.name = name;
        this.cells = cells;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<RequestiStatSaveCell> getCells() {
        return cells;
    }

    public void setCells(List<RequestiStatSaveCell> cells) {
        this.cells = cells;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("name", name).append("cells", cells).build();
    }

}
