package org.iStat.api.Domain;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.newHashSet;
import static com.natpryce.makeiteasy.MakeItEasy.a;
import static com.natpryce.makeiteasy.MakeItEasy.make;
import static com.natpryce.makeiteasy.MakeItEasy.with;
import static org.iStat.api.iDomain.makeit.CellMakeIt._cell;
import static org.iStat.api.iDomain.makeit.CellMakeIt.column;
import static org.iStat.api.iDomain.makeit.CellMakeIt.line;
import static org.iStat.api.iDomain.makeit.CellMakeIt.value;
import static org.iStat.api.iDomain.makeit.DatasetMakeIt._dataset;
import static org.iStat.api.iDomain.makeit.DatasetMakeIt.cells;
import static org.iStat.api.iDomain.makeit.DatasetMakeIt.name;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.iStat.api.iDomain.Cell;
import org.iStat.api.iDomain.Dataset;
import org.junit.Before;
import org.junit.Test;

public class DatasetTest {

    private Dataset datasetToTest;

    private Dataset datasetWithOneColumn;

    private Dataset datasetWithOneLine;

    @Before
    public void setupTest() {

        Cell<Integer, String> A1Cell = make(a(_cell, with(line, 1), with(column, "A"), with(value, 1.0f)));
        Cell<Integer, String> A2Cell = make(a(_cell, with(line, 2), with(column, "A"), with(value, 10.0f)));
        Cell<Integer, String> A3Cell = make(a(_cell, with(line, 3), with(column, "A"), with(value, 5.0f)));

        Cell<Integer, String> B1Cell = make(a(_cell, with(line, 1), with(column, "B"), with(value, 7.0f)));
        Cell<Integer, String> B2Cell = make(a(_cell, with(line, 2), with(column, "B"), with(value, 11.0f)));
        Cell<Integer, String> B3Cell = make(a(_cell, with(line, 3), with(column, "B"), with(value, 5.0f)));

        Cell<Integer, String> C1Cell = make(a(_cell, with(line, 1), with(column, "C"), with(value, 0.0f)));
        Cell<Integer, String> C2Cell = make(a(_cell, with(line, 2), with(column, "C"), with(value, 0.0f)));
        Cell<Integer, String> C3Cell = make(a(_cell, with(line, 3), with(column, "C"), with(value, 0.0f)));

        Cell<Integer, String> D1Cell = make(a(_cell, with(line, 1), with(column, "D"), with(value, 9.0f)));
        Cell<Integer, String> D2Cell = make(a(_cell, with(line, 2), with(column, "D"), with(value, 12.0f)));
        Cell<Integer, String> D3Cell = make(a(_cell, with(line, 3), with(column, "D"), with(value, 0.0f)));

        datasetToTest = make(a(_dataset, with(name, "datasetTest"), with(cells,
                newArrayList(A1Cell, A2Cell, A3Cell, B1Cell, B2Cell, B3Cell, C1Cell, C2Cell, C3Cell, D1Cell, D2Cell, D3Cell))));

        datasetWithOneColumn = make(a(_dataset, with(name, "datasetTest"), with(cells, newArrayList(A1Cell, A2Cell, A3Cell))));

        datasetWithOneLine = make(a(_dataset, with(name, "datasetTest"), with(cells, newArrayList(A1Cell, B1Cell, C1Cell))));

    }

    @Test
    public void shouldGetAllColumnsOfCellsWithEmptyDataset() {

        Set<String> columns = make(a(_dataset)).getAllColumnsOfCells();

        assertNotNull(columns);
        assertEquals(0, columns.size());

    }

    @Test
    public void shouldGetAllColumnsOfCells() {

        Set<String> expectedColumns = newHashSet("A", "B", "C", "D");

        Set<String> columns = datasetToTest.getAllColumnsOfCells();

        assertNotNull(columns);
        assertEquals(4, columns.size());
        columns.stream().forEach(column -> {
            assertTrue(expectedColumns.contains(column));
        });;

    }

    @Test
    public void shouldGetAllLinesOfCellsWithEmptyDataset() {

        Set<Integer> lines = make(a(_dataset)).getAllLinesOfCells();

        assertNotNull(lines);
        assertEquals(0, lines.size());

    }

    @Test
    public void shouldGetAllLinesOfCells() {

        Set<Integer> expectedLines = newHashSet(1, 2, 3);

        Set<Integer> lines = datasetToTest.getAllLinesOfCells();

        assertNotNull(lines);
        assertEquals(3, lines.size());
        lines.stream().forEach(line -> {
            assertTrue(expectedLines.contains(line));
        });;

    }

    @Test(expected = NullPointerException.class)
    public void shouldGetValueOfColumnLineWithNullColumn() throws Exception {
        assertEquals(Float.valueOf(1.0f), datasetToTest.getValueOfColumnLine(null, 0));
    }

    @Test(expected = NullPointerException.class)
    public void shouldGetValueOfColumnLineWithNullLine() throws Exception {
        assertEquals(Float.valueOf(1.0f), datasetToTest.getValueOfColumnLine(0, null));
    }

    @Test
    public void shouldGetValueOfColumnLine() throws Exception {

        assertEquals(Float.valueOf(1.0f), datasetToTest.getValueOfColumnLine(0, 0));
        assertEquals(Float.valueOf(10.0f), datasetToTest.getValueOfColumnLine(0, 1));
        assertEquals(Float.valueOf(5.0f), datasetToTest.getValueOfColumnLine(0, 2));

        assertEquals(Float.valueOf(7.0f), datasetToTest.getValueOfColumnLine(1, 0));
        assertEquals(Float.valueOf(11.0f), datasetToTest.getValueOfColumnLine(1, 1));
        assertEquals(Float.valueOf(5.0f), datasetToTest.getValueOfColumnLine(1, 2));

        assertEquals(Float.valueOf(9.0f), datasetToTest.getValueOfColumnLine(3, 0));
        assertEquals(Float.valueOf(12.0f), datasetToTest.getValueOfColumnLine(3, 1));

    }

    @Test
    public void shouldConvertToMatrix() throws Exception {

        Float[][] matrix = datasetToTest.convertToMatrix();

        assertNotNull(matrix);
        assertEquals(3, matrix.length);
        assertEquals(4, matrix[0].length);
        assertEquals(Float.valueOf(1.0f), matrix[0][0]);
        assertEquals(Float.valueOf(7.0f), matrix[0][1]);
        assertEquals(Float.valueOf(0.0f), matrix[0][2]);
        assertEquals(Float.valueOf(9.0f), matrix[0][3]);
        assertEquals(Float.valueOf(10.0f), matrix[1][0]);
        assertEquals(Float.valueOf(11.0f), matrix[1][1]);
        assertEquals(Float.valueOf(0.0f), matrix[1][2]);
        assertEquals(Float.valueOf(12.0f), matrix[1][3]);
        assertEquals(Float.valueOf(5.0f), matrix[2][0]);
        assertEquals(Float.valueOf(5.0f), matrix[2][1]);
        assertEquals(Float.valueOf(0.0f), matrix[2][2]);
        assertEquals(Float.valueOf(0.0f), matrix[2][3]);
    }

    @Test
    public void shouldConvertToMatrixWithOneColumn() throws Exception {

        Float[][] matrix = datasetWithOneColumn.convertToMatrix();

        assertNotNull(matrix);
        assertEquals(3, matrix.length);
        assertEquals(1, matrix[0].length);
        assertEquals(Float.valueOf(1.0f), matrix[0][0]);
        assertEquals(Float.valueOf(10.0f), matrix[1][0]);
        assertEquals(Float.valueOf(5.0f), matrix[2][0]);
    }
    
    @Test
    public void shouldConvertToMatrixWithOneLine() throws Exception {

        Float[][] matrix = datasetWithOneLine.convertToMatrix();

        assertNotNull(matrix);
        assertEquals(1, matrix.length);
        assertEquals(3, matrix[0].length);
        assertEquals(Float.valueOf(1.0f), matrix[0][0]);
        assertEquals(Float.valueOf(7.0f), matrix[0][1]);
        assertEquals(Float.valueOf(0.0f), matrix[0][2]);
    }

}
