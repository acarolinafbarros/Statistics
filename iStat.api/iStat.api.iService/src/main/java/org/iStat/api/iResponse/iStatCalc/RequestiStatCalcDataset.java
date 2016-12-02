package org.iStat.api.iResponse.iStatCalc;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestiStatCalcDataset {

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "cells")
    private List<RequestiStatCalcCell> cells;

    public RequestiStatCalcDataset(){
    }
    
    public RequestiStatCalcDataset(String name,
            List<RequestiStatCalcCell> cells) {
        this.name = name;
        this.cells = cells;
    }

    public String getName() {
        return name;
    }

    public List<RequestiStatCalcCell> getCells() {
        return cells;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("name", name).append("cells", cells).build();
    }

}
