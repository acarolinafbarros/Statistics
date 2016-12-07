package org.iStat.api.iStatIO.request;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestiStatSave {

    @JsonProperty(value = "datasets")
    private List<RequestiStatSaveDataset> datasets;

    @JsonProperty(value = "name")
    private String name;

    public RequestiStatSave() {}

    public RequestiStatSave(String name) {
        this(null, name);
    }

    public RequestiStatSave(List<RequestiStatSaveDataset> dataset) {
        this(dataset, null);
    }

    public RequestiStatSave(List<RequestiStatSaveDataset> dataset,
            String name) {
        this.datasets = dataset;
        this.name = name;
    }

    public List<RequestiStatSaveDataset> getDatasets() {
        return datasets;
    }

    public void setDatasets(List<RequestiStatSaveDataset> datasets) {
        this.datasets = datasets;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("datasets", datasets).append("name", name)
            .build();
    }

}
