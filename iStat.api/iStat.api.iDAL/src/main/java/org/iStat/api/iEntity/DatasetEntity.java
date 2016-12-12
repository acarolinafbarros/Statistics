package org.iStat.api.iEntity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class DatasetEntity {

    @Id
    private String name;

    @Field("cells")
    private List<CellEntity> cells;

    public DatasetEntity(String name, List<CellEntity> cells) {
        this.name = name;
        this.cells = cells;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CellEntity> getCells() {
        return cells;
    }

    public void setCells(List<CellEntity> cells) {
        this.cells = cells;
    }

    @Override
    public String toString() {
        return "DatasetModel [name=" + name + ", cells=" + cells + "]";
    }

}
