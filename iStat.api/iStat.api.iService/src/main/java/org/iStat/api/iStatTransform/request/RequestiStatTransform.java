package org.iStat.api.iStatTransform.request;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestiStatTransform {

    @JsonProperty(value = "datasets")
    private List<RequestiStatTransformDataset> datasets;
    
    @JsonProperty(value = "scalar")
    private Float scalar;
    
    public RequestiStatTransform(){
    }

    public RequestiStatTransform(List<RequestiStatTransformDataset> dataset) {
        this.datasets = dataset;
    }
    
    public RequestiStatTransform(List<RequestiStatTransformDataset> dataset, Float scalar) {
        this.datasets = dataset;
        this.scalar = scalar;
    }

    public List<RequestiStatTransformDataset> getDatasets() {
        return datasets;
    }

    public Float getScalar() {
        return scalar;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("datasets", datasets)
            .append("scalar", scalar).build();
    }

}
