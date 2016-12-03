package org.iStat.api.iStatTransform.request;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestiStatCalcCellTransform {

    @JsonProperty(value = "line")
    private String line;

    @JsonProperty(value = "column")
    private String column;

    @JsonProperty(value = "value")
    private float value;

    public RequestiStatCalcCellTransform(){
    }
    
    public RequestiStatCalcCellTransform(String line, String column,
            float value) {
        this.line = line;
        this.column = column;
        this.value = value;
    }

    public String getLine() {
        return line;
    }

    public String getColumn() {
        return column;
    }

    public float getValue() {
        return value;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("line", line).append("column", column)
            .append("value", value).build();
    }
}
