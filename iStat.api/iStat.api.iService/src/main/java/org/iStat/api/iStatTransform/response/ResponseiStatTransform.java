package org.iStat.api.iStatTransform.response;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseiStatTransform {

    @JsonProperty(value = "datasets")
    private List<ResponseiStatTransformDataset> datasets;

    public ResponseiStatTransform() {}

    public ResponseiStatTransform(
            List<ResponseiStatTransformDataset> dataset) {
        this.datasets = dataset;
    }

    public List<ResponseiStatTransformDataset> getDatasets() {
        return datasets;
    }

    public void setDatasets(List<ResponseiStatTransformDataset> datasets) {
        this.datasets = datasets;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("datasets", datasets).build();
    }

}
