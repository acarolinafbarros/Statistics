package org.iStat.api.iStatIO.response;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseiStatIOOpenDataset {

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "cells")
    private List<ResponseiStatIOOpenCell> cells;

    public ResponseiStatIOOpenDataset() {}

    public ResponseiStatIOOpenDataset(String name,
            List<ResponseiStatIOOpenCell> cells) {
        this.name = name;
        this.cells = cells;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ResponseiStatIOOpenCell> getCells() {
        return cells;
    }

    public void setCells(List<ResponseiStatIOOpenCell> cells) {
        this.cells = cells;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("name", name).append("cells", cells).build();
    }

}
