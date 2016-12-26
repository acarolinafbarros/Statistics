package org.iStat.api.iEntity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class CellEntity {

	private String parentDatasetName;

	@Id
	private String cellId;

	private Integer line;

	private String column;

	private Float value;

	public CellEntity(String parentDatasetName, Integer line, String column, Float value) {
		super();
		this.cellId = line.toString() + column;
		this.parentDatasetName = parentDatasetName;
		this.line = line;
		this.column = column;
		this.value = value;
	}

	public String getParentDatasetName() {
		return parentDatasetName;
	}

	public void setParentDatasetName(String parentDatasetName) {
		this.parentDatasetName = parentDatasetName;
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
		return new ToStringBuilder(this).append("parentDatasetName", parentDatasetName).append("cellId", cellId)
				.append("line", line).append("column", column).append("value", value).build();
	}
}
