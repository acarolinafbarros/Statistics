package org.iStat.api.iDomain;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Cell<Line, Column> {

    private String parentDatasetName;

    private Line line;

    private Column column;

    private Float value;

    private Cell(CellBuilder<Line, Column> cellBuilder) {
        this.parentDatasetName = cellBuilder.parentDatasetName;
        this.line = cellBuilder.line;
        this.column = cellBuilder.column;
        this.value = cellBuilder.value;
    }

    public String getParentDatasetName() {
        return parentDatasetName;
    }

    public void setParentDatasetName(String parentDatasetName) {
        this.parentDatasetName = parentDatasetName;
    }

    public Line getLine() {
        return line;
    }

    public void setLine(Line line) {
        this.line = line;
    }

    public Column getColumn() {
        return column;
    }

    public void setColumn(Column column) {
        this.column = column;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("parentDatasetName", parentDatasetName)
            .append("line", line).append("column", column)
            .append("value", value).build();
    }

    public static class CellBuilder<Line, Column> {

        private String parentDatasetName;

        private Line line;

        private Column column;

        private Float value;

        public CellBuilder<Line, Column> withParentDatasetName(String parentName) {
            this.parentDatasetName = parentName;
            return this;
        }

        public CellBuilder<Line, Column> withLine(Line line) {
            this.line = line;
            return this;
        }

        public CellBuilder<Line, Column> withColumn(Column column) {
            this.column = column;
            return this;
        }

        public CellBuilder<Line, Column> withValue(Float value) {
            this.value = value;
            return this;
        }

        public Cell<Line, Column> build() {
            return new Cell<>(this);
        }

    }

}
