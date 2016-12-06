package org.iStat.api.iStatIO.response;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseiStatIOOpen {

    @JsonProperty(value = "datasets")
    private List<ResponseiStatIOOpenDataset> datasets;

    public ResponseiStatIOOpen() {}

    public ResponseiStatIOOpen(
            List<ResponseiStatIOOpenDataset> dataset) {
        this.datasets = dataset;
    }

    public List<ResponseiStatIOOpenDataset> getDatasets() {
        return datasets;
    }

    public void setDatasets(List<ResponseiStatIOOpenDataset> datasets) {
        this.datasets = datasets;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("datasets", datasets).build();
    }

}
