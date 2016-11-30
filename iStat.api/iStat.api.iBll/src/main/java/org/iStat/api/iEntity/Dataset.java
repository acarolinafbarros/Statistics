package org.iStat.api.iEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Dataset {

    private String name;

    private List<Cell<Integer, String>> cells;

    private Dataset(DatasetBuilder builder) {
        this.name = builder.name;
        this.cells = builder.cells;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Cell<Integer, String>> getCells() {
        if (Objects.isNull(cells)) {
            this.cells = new ArrayList<>();
        }
        return cells;
    }

    public void setCells(List<Cell<Integer, String>> cells) {
        this.cells = cells;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("name", name).append("cells", cells).build();
    }

    public static class DatasetBuilder {

        private String name;

        private List<Cell<Integer, String>> cells;

        public DatasetBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public DatasetBuilder withCells(List<Cell<Integer, String>> cells) {
            this.cells = cells;
            return this;
        }

        public Dataset build() {
            return new Dataset(this);
        }

    }

}
