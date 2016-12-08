package org.iStat.api.iModel;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection="DocumentIStat")
public class DocumentIStatModel {

    @Id
    private String name;

    @Field("datasets")
    private List<DatasetModel> datasets;

    public DocumentIStatModel(String name, List<DatasetModel> datasets) {
        super();
        this.name = name;
        this.datasets = datasets;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DatasetModel> getDatasets() {
        return datasets;
    }

    public void setDatasets(List<DatasetModel> datasets) {
        this.datasets = datasets;
    }

    @Override
    public String toString() {
        return "DocumentIStatModel [name=" + name + ", datasets=" + datasets + "]";
    }

}
