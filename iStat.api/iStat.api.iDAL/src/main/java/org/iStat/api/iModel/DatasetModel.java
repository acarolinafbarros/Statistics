package org.iStat.api.iModel;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class DatasetModel {

    @Id
    private String name;

    @Field("cells")
    private List<CellModel> cells;

    public DatasetModel(String name, List<CellModel> cells) {
        this.name = name;
        this.cells = cells;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CellModel> getCells() {
        return cells;
    }

    public void setCells(List<CellModel> cells) {
        this.cells = cells;
    }

    @Override
    public String toString() {
        return "DatasetModel [name=" + name + ", cells=" + cells + "]";
    }

}
