package org.iStat.api.iLogic;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.iStat.api.iEntity.Cell;
import org.iStat.api.iEntity.Dataset;
import org.junit.Test;

public class TransformStatisticalTest extends AbstractUtilsiLogicTest {

    private static TransformStatistical transformStatisical = new TransformStatistical();

    private static Float[][] matrixTransform;

    /*
     * ----------------------------------------------------------------------
     * EN -
     * ----------------------------------------------------------------------
     */
    public List<Cell<Integer, String>> fillCells() {
        List<Cell<Integer, String>> list = new ArrayList<>();
        Cell<Integer, String> cell7 = makeCell(3, "B", new Float(9));
        list.add(cell7);
        Cell<Integer, String> cell2 = makeCell(1, "B", new Float(4));
        list.add(cell2);
        Cell<Integer, String> cell1 = makeCell(1, "A", new Float(1));
        list.add(cell1);
        Cell<Integer, String> cell3 = makeCell(2, "A", new Float(3));
        list.add(cell3);
        Cell<Integer, String> cell5 = makeCell(2, "C", new Float(11));
        list.add(cell5);
        Cell<Integer, String> cell4 = makeCell(2, "B", new Float(5));
        list.add(cell4);;
        Cell<Integer, String> cell6 = makeCell(3, "A", new Float(7));
        list.add(cell6);
        Cell<Integer, String> cell8 = makeCell(4, "A", new Float(13));
        list.add(cell8);
        Cell<Integer, String> cell9 = makeCell(4, "B", new Float(12));
        list.add(cell9);
        Cell<Integer, String> cell10 = makeCell(4, "C", new Float(15));
        list.add(cell10);
        return list;
    }

    public Float[][] createMatrix() {
        Float[][] matrix = new Float[4][3];
        matrix[0][0] = new Float(1);
        matrix[0][1] = new Float(4);
        matrix[0][2] = new Float(0);
        matrix[1][0] = new Float(3);
        matrix[1][1] = new Float(5);
        matrix[1][2] = new Float(11);
        matrix[2][0] = new Float(7);
        matrix[2][1] = new Float(9);
        matrix[2][2] = new Float(0);
        matrix[3][0] = new Float(13);
        matrix[3][1] = new Float(12);
        matrix[3][2] = new Float(15);
        return matrix;
    }

    public Float[][] createMatrixWithNulls() {
        Float[][] matrix = new Float[4][3];
        matrix[0][0] = new Float(1);
        matrix[0][1] = new Float(4);
        matrix[0][2] = null;
        matrix[1][0] = new Float(3);
        matrix[1][1] = new Float(5);
        matrix[1][2] = new Float(11);
        matrix[2][0] = new Float(7);
        matrix[2][1] = new Float(9);
        matrix[2][2] = null;
        matrix[3][0] = new Float(13);
        matrix[3][1] = new Float(12);
        matrix[3][2] = new Float(15);
        return matrix;
    }

    @Test
    public void transformListIntoMatrix() {
        List<Cell<Integer, String>> listCells = fillCells();
        Dataset dataset = makeDataset("teste", listCells);
        Float[][] matrixResult = transformStatisical.convertListToMatrix(dataset);
        Float[][] matrixExpected = createMatrix();

        assertEquals(matrixExpected[0][0], matrixResult[0][0]);
        assertEquals(matrixExpected[0][2], matrixResult[0][2]);
        assertEquals(matrixExpected[1][1], matrixResult[1][1]);
        assertEquals(matrixExpected[3][2], matrixResult[3][2]);
    }

    @Test
    public void transformMatrixIntoList() {
        Float[][] input = createMatrix();
        String startColumn = "A";
        Integer startLine = 1;

        List<Cell<Integer, String>> cellsResult = transformStatisical.convertMatrixtToList(input, startColumn, startLine);
        List<Cell<Integer, String>> cellsExpected = fillCells();

        assertEquals(cellsExpected.size() + 2, cellsResult.size());
    }

    @Test
    public void fillMatrixEmptyValues() {
        Float[][] matrixResult = transformStatisical.fillMatrixEmptyValues(0, createMatrixWithNulls());
        Float[][] matrixExpected = createMatrix();

        assertEquals(matrixExpected[0][2], matrixResult[0][2]);
        assertEquals(matrixExpected[2][2], matrixResult[2][2]);
        assertEquals(new Float(0), matrixResult[2][2]);
        assertEquals(new Float(0), matrixResult[0][2]);
    }

    /*
     * @Test
     * public void transformScaleInputNull() {
     * Float received = transformStatisical.transformScale(null);
     * assertNull(received);
     * }
     * 
     * @Test
     * public void transformScaleInputEmpty() {
     * Float received = transformStatisical.transformScale(new ArrayList<>());
     * assertNull(received);
     * }
     * 
     * @Test
     * public void transformScaleInputValid() {
     * List<Float> input = new ArrayList<>();
     * input.add(new Float("1.1"));
     * input.add(new Float("1.3"));
     * Float received = transformStatisical.transformTranspose(input);
     * Float expected = new Float("1.2");
     * assertEquals(expected, received);
     * }
     * 
     * @Test
     * public void transformScaleInputWithNegatives() {
     * List<Float> input = new ArrayList<>();
     * input.add(new Float("-1.0"));
     * input.add(new Float("1.0"));
     * Float received = transformStatisical.transformTranspose(input);
     * Float expected = new Float("0");
     * assertEquals(expected, received);
     * }
     */
}
