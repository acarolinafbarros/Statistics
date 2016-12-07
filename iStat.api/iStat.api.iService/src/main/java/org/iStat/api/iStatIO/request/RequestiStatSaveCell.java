package org.iStat.api.iStatIO.request;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestiStatSaveCell {

    @JsonProperty(value = "line")
    private Integer line;

    @JsonProperty(value = "column")
    private String column;

    @JsonProperty(value = "value")
    private Float value;

    public RequestiStatSaveCell() {}

    public RequestiStatSaveCell(Integer line, String column,
            Float value) {
        this.line = line;
        this.column = column;
        this.value = value;
    }

    public Integer getLine() {
        return line;
    }

    public void setLine(Integer line) {
        this.line = line;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
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
            .append("line", line).append("column", column)
            .append("value", value).build();
    }
}
