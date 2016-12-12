package org.iStat.api.iStatCalc.request;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestiStatCalc {

    @JsonProperty(value = "datasets")
    private List<RequestiStatCalcDataset> datasets;
    
    public RequestiStatCalc(){
    }
    
    public RequestiStatCalc(List<RequestiStatCalcDataset> dataset) {
        this.datasets = dataset;
    }

    public List<RequestiStatCalcDataset> getDatasets() {
        return datasets;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("datasets", datasets).build();
    }

}
