package org.iStat.api.iStatTransform.request;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestiStatTransform {

    @JsonProperty(value = "datasets")
    private List<RequestiStatCalcDatasetTransform> datasets;
    
    public RequestiStatTransform(){
    }
    
    public RequestiStatTransform(List<RequestiStatCalcDatasetTransform> dataset) {
        this.datasets = dataset;
    }

    public List<RequestiStatCalcDatasetTransform> getDatasets() {
        return datasets;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("datasets", datasets).build();
    }

}
