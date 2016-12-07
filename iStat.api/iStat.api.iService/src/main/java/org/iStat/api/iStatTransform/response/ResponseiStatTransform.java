package org.iStat.api.iStatTransform.response;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.iStat.api.iResponse.StatusEnum;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseiStatTransform {

	private StatusEnum status;
	
    @JsonProperty(value = "datasets")
    private List<ResponseiStatTransformDataset> datasets;

    public ResponseiStatTransform() {}

    public ResponseiStatTransform(StatusEnum status, List<ResponseiStatTransformDataset> dataset) {
    	this.status = status;
    	this.datasets = dataset;
    }

    public List<ResponseiStatTransformDataset> getDatasets() {
        return datasets;
    }

    public StatusEnum getStatus() {
        return status;
    }
    
    public void setDatasets(List<ResponseiStatTransformDataset> datasets) {
        this.datasets = datasets;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }
    
    @Override
    public String toString() {
        return new ToStringBuilder(this).append("status", status).append("datasets", datasets).build();
    }

}
