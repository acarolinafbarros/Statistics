package org.iStat.api.iStatTransform.response;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseiStatTransform {

    @JsonProperty(value = "datasets")
    private List<ResponseiStatCalcDatasetTransform> datasets;

    public ResponseiStatTransform() {}

    public ResponseiStatTransform(
            List<ResponseiStatCalcDatasetTransform> dataset) {
        this.datasets = dataset;
    }

    public List<ResponseiStatCalcDatasetTransform> getDatasets() {
        return datasets;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("datasets", datasets).build();
    }

}
