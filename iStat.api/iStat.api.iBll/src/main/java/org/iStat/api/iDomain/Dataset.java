package org.iStat.api.iDomain;

import static com.google.common.collect.Lists.newArrayList;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.iStat.api.iDomain.Cell.CellBuilder;
import org.iStat.api.iExceptions.DomainException;

public class Dataset {

    private String name;

    private List<Cell<Integer, String>> cells;

    private Float[][] datasetMatrix;

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
        for (Cell<Integer, String> cell : getCells()) {
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
        for (Cell<Integer, String> cell : getCells()) {
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
     * @throws DomainException
     */
    public Float getValueOfColumnLine(Integer columnPosition, Integer linePosition) throws DomainException {

        Objects.requireNonNull(columnPosition, "columnPosition must not be null!");
        Objects.requireNonNull(linePosition, "linePosition must not be null!");

        Float value = new Float(0);

        if (columnPosition >= 0 && linePosition >= 0) {

            List<String> columns = newArrayList(getAllColumnsOfCells());
            List<Integer> lines = newArrayList(getAllLinesOfCells());

            for (Cell<Integer, String> cell : getCells()) {
                String cellColumn = columns.get(columnPosition);
                int cellLine = lines.get(linePosition);

                if (cell.getColumn().equals(cellColumn) && cell.getLine().equals(cellLine)) {
                    if (cell.getValue() != null) {
                        return cell.getValue();
                    }
                }
            }
        } else {
            throw new DomainException("The columnPosition must be > 0 and linePosition must be > 0.");
        }

        return value;
    }

    public boolean hasMoreThenOneLine() {

        if (CollectionUtils.isNotEmpty(this.cells)) {
            Integer line = this.cells.get(0).getLine();
            return this.cells.stream().anyMatch(cell -> cell.getLine() == line);
        }

        return false;

    }

    public boolean hasMoreThenOneColumn() {

        if (CollectionUtils.isNotEmpty(this.cells)) {
            String column = this.cells.get(0).getColumn();
            return this.cells.stream().anyMatch(cell -> cell.getColumn().equals(column));
        }

        return false;

    }

    public List<Cell<Integer, String>> getLine(int searchLine) {

        List<Cell<Integer, String>> line = newArrayList();

        if (CollectionUtils.isNotEmpty(this.cells)) {
            this.cells.stream().forEach(cell -> {
                if (cell.getLine() == searchLine) {
                    line.add(cell);
                }
            });
        }

        return line;

    }

    public List<Cell<Integer, String>> getColumn(String searchColumn) {

        List<Cell<Integer, String>> column = newArrayList();

        if (CollectionUtils.isNotEmpty(this.cells)) {
            this.cells.stream().forEach(cell -> {
                if (cell.getColumn().equals(searchColumn)) {
                    column.add(cell);
                }
            });
        }

        return column;

    }

    /**
     * Method to convert the list of cells from a dataset to a matrix.
     * 
     * @param dataset
     * @return Float[][]
     * @throws DomainException
     */
    public Float[][] convertToMatrix() throws DomainException {

        if (Objects.isNull(this.datasetMatrix)) {
            List<String> orderedColumns = newArrayList(getAllColumnsOfCells());
            List<Integer> orderedLines = newArrayList(getAllLinesOfCells());

            Float[][] matrix = new Float[orderedLines.size()][orderedColumns.size()];

            for (int row = 0; row < orderedLines.size(); row++) {
                for (int column = 0; column < orderedColumns.size(); column++) {
                    matrix[row][column] = this.getValueOfColumnLine(column, row);
                }
            }
            this.datasetMatrix = matrix;
        }

        return this.datasetMatrix;
    }

    /**
     * Method to convert a matrix into a list of cells.
     * 
     * @param input
     * @param startColumn
     * @param startLine
     * @return List<Cell<Integer,String>>
     */
    public List<Cell<Integer, String>> convertMatrixtToList(Float[][] newMatrix, String startColumn, Integer startLine) {

        Objects.requireNonNull(newMatrix, "newMatrix must not be null!");
        Objects.requireNonNull(startColumn, "startColumn must not be null!");
        Objects.requireNonNull(startLine, "startLine must not be null!");

        List<Cell<Integer, String>> output = new ArrayList<>();

        // A - Z columns
        char[] columnArray = IntStream
            .rangeClosed('A', 'Z').mapToObj(c -> "" + (char) c).collect(Collectors.joining()).toCharArray();
        List<String> columns = new ArrayList<>();
        for (int i = 0; i < columnArray.length; i++) {
            columns.add(columnArray[i] + "");
        }

        // 1 - 200 lines
        List<Integer> lines = new ArrayList<>();
        for (int i = 1; i < 200; i++) {
            lines.add(i);
        }

        Integer columnPosition = null;
        for (int i = 0; i < columns.size(); i++) {
            if (columns.get(i).equals(startColumn)) {
                columnPosition = i;
                break;
            }
        }

        Integer linePosition = null;
        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).equals(startLine)) {
                linePosition = i;
                break;
            }
        }

        CellBuilder<Integer, String> builder = new CellBuilder<>();
        Integer columnPositionIterate = null;
        for (int row = 0; row < newMatrix.length; row++) {
            columnPositionIterate = columnPosition;
            for (int column = 0; column < newMatrix[row].length; column++) {
                builder.withColumn(columns.get(columnPositionIterate));
                builder.withLine(lines.get(linePosition));
                builder.withValue(newMatrix[row][column]);
                Cell<Integer, String> cell = builder.build();
                output.add(cell);

                columnPositionIterate++;
            }
            linePosition++;
        }
        return output;
    }

}
