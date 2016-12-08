package org.iStat.api.iEntity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

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

    /**
     * Method to get all the columns of the cells that make part of dataset.
     * 
     * @return Set<String>
     */
    public Set<String> getAllColumnsOfCells() {
        Set<String> columns = new HashSet<>();
        for (Cell<Integer, String> cell : this.cells) {
            columns.add(cell.getColumn());
        }
        return columns;
    }

    /**
     * Method to get all the lines of the cells that make part of dataset.
     * 
     * @return Set<Integer>
     */
    public Set<Integer> getAllLinesOfCells() {
        Set<Integer> lines = new HashSet<>();
        for (Cell<Integer, String> cell : this.cells) {
            lines.add(cell.getLine());
        }
        return lines;
    }

    /**
     * Method to get the value of a cell with a specific column and line.
     * 
     * @param columnPosition
     * @param linePosition
     * @return Float
     */
    public Float getValueOfColumnLine(Integer columnPosition, Integer linePosition) {
        List<String> columns = new ArrayList<>(getAllColumnsOfCells());
        List<Integer> lines = new ArrayList<>(getAllLinesOfCells());
        Float value = new Float(0);
        for (Cell<Integer, String> cell : this.cells) {
            if (cell.getColumn().equals(columns.get(columnPosition)) & cell.getLine().equals(lines.get(linePosition))) {
                if (cell.getValue() != null) {
                    value = cell.getValue();
                    break;
                }
            }
        }
        return value;
    }

}
