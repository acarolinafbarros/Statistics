package org.iStat.api.iStatIO.response;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.iStat.api.iResponse.ResponseiStat;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseiStatOpen extends ResponseiStat {

    @JsonProperty(value = "datasets")
    private List<ResponseiStatOpenDataset> datasets;

    public ResponseiStatOpen() {}

    public ResponseiStatOpen(List<ResponseiStatOpenDataset> dataset) {
        this.datasets = dataset;
    }

    public List<ResponseiStatOpenDataset> getDatasets() {
        return datasets;
    }

    public void setDatasets(List<ResponseiStatOpenDataset> datasets) {
        this.datasets = datasets;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("datasets", datasets).build();
    }

}
