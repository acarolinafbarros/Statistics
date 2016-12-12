package org.iStat.api.iStatTransform.response;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.iStat.api.iResponse.ResponseStatusiStat;
import org.iStat.api.iResponse.ResponseiStat;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseiStatTransform extends ResponseiStat {

    @JsonProperty(value = "datasets")
    private List<ResponseiStatTransformDataset> datasets;

    public ResponseiStatTransform() {}

    public ResponseiStatTransform(ResponseStatusiStat status,
            List<ResponseiStatTransformDataset> dataset) {
        this.status = status;
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
