package org.iStat.api.iEntity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection="DocumentIStat")
public class DocumentIStatEntity {

    @Id
    private String name;

    @Field("datasets")
    private List<DatasetEntity> datasets;

    public DocumentIStatEntity(String name, List<DatasetEntity> datasets) {
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

    public List<DatasetEntity> getDatasets() {
        return datasets;
    }

    public void setDatasets(List<DatasetEntity> datasets) {
        this.datasets = datasets;
    }

    @Override
    public String toString() {
        return "DocumentIStatModel [name=" + name + ", datasets=" + datasets + "]";
    }

}
