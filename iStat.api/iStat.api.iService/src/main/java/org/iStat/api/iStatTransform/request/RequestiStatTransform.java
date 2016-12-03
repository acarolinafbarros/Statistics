package org.iStat.api.iStatTransform.request;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestiStatTransform {

    @JsonProperty(value = "datasets")
    private List<RequestiStatTransformDataset> datasets;
    
    public RequestiStatTransform(){
    }
    
    public RequestiStatTransform(List<RequestiStatTransformDataset> dataset) {
        this.datasets = dataset;
    }

    public List<RequestiStatTransformDataset> getDatasets() {
        return datasets;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("datasets", datasets).build();
    }

}
