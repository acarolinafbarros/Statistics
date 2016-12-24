package org.iStat.api.iLogic;

import static com.google.common.collect.Lists.newArrayList;
import static com.natpryce.makeiteasy.MakeItEasy.a;
import static com.natpryce.makeiteasy.MakeItEasy.make;
import static org.iStat.api.iDomain.makeit.DocumentiStatMakeIt._documentiStat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.iStat.api.iDomain.Cell;
import org.iStat.api.iDomain.DocumentiStat;
import org.iStat.api.iExceptions.TransformException;
import org.iStat.api.iLogic.TransformStatistical.TransformType;
import org.junit.Test;

public class TransformStatisticalTest extends AbstractUtilsiLogicTest {

    private TransformStatistical transformStatisical = new TransformStatistical();

    @Test
    public void shouldTestTransposeWithNull() throws Exception {
        assertNull(transformStatisical.transformTranspose(null));
    }

    @Test
    public void shouldTestTransposeWithEmpty() throws Exception {
        assertNull(transformStatisical.transformTranspose(make(a(_documentiStat))));
    }

    @Test
    public void shouldTestTransposeLineToColumnWithOneLine() throws Exception {

        List<Cell<Integer, String>> listOfCells = newArrayList(makeCell(1, "A", 2.0f), makeCell(1, "B", 3.0f),
                makeCell(1, "C", 1.0f));
        DocumentiStat documentiStat = makeDocumentiStat(newArrayList(makeDataset("dataset1", listOfCells)));

        DocumentiStat received = transformStatisical.transformTranspose(documentiStat);

        assertNotNull(received);
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(1, "A", Float.valueOf(2.0f))));
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(2, "A", Float.valueOf(3.0f))));
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(3, "A", Float.valueOf(1.0f))));

    }

    @Test
    public void shouldTestTransposeLineToColumnWithOneLineZeroValues() throws Exception {

        List<Cell<Integer, String>> listOfCells = newArrayList(makeCell(1, "A", 0.0f));
        DocumentiStat documentiStat = makeDocumentiStat(newArrayList(makeDataset("dataset1", listOfCells)));

        DocumentiStat received = transformStatisical.transformTranspose(documentiStat);

        assertNotNull(received);
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(1, "A", Float.valueOf(0.0f))));

    }

    @Test
    public void shouldTestTransposeLineToColumnWithOneLineNegativeValues() throws Exception {

        List<Cell<Integer, String>> listOfCells = newArrayList(makeCell(1, "A", -2.0f), makeCell(1, "B", -4.0f),
                makeCell(1, "C", -3.0f), makeCell(1, "D", -1.0f));
        DocumentiStat documentiStat = makeDocumentiStat(newArrayList(makeDataset("dataset1", listOfCells)));

        DocumentiStat received = transformStatisical.transformTranspose(documentiStat);

        assertNotNull(received);
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(1, "A", Float.valueOf(-2.0f))));
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(2, "A", Float.valueOf(-4.0f))));
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(3, "A", Float.valueOf(-3.0f))));
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(4, "A", Float.valueOf(-1.0f))));

    }

    @Test
    public void shouldTestTransposeLineToColumnWithTwoLine() throws Exception {

        List<Cell<Integer, String>> listOfCells = newArrayList(makeCell(1, "A", -2.0f), makeCell(1, "B", -4.0f),
                makeCell(1, "C", -3.0f), makeCell(1, "D", -1.0f), makeCell(2, "A", -2.0f), makeCell(2, "B", -4.0f),
                makeCell(2, "C", -3.0f), makeCell(2, "D", -1.0f));
        DocumentiStat documentiStat = makeDocumentiStat(newArrayList(makeDataset("dataset1", listOfCells)));

        DocumentiStat received = transformStatisical.transformTranspose(documentiStat);

        assertNotNull(received);
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(1, "A", Float.valueOf(-2.0f))));
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(2, "A", Float.valueOf(-4.0f))));
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(3, "A", Float.valueOf(-3.0f))));
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(4, "A", Float.valueOf(-1.0f))));

        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(1, "B", Float.valueOf(-2.0f))));
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(2, "B", Float.valueOf(-4.0f))));
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(3, "B", Float.valueOf(-3.0f))));
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(4, "B", Float.valueOf(-1.0f))));

    }

    @Test
    public void shouldTestTransposeColumnToLineWithOneLine() throws Exception {

        List<Cell<Integer, String>> listOfCells = newArrayList(makeCell(1, "A", 2.0f), makeCell(2, "A", 3.0f),
                makeCell(3, "A", 1.0f));
        DocumentiStat documentiStat = makeDocumentiStat(newArrayList(makeDataset("dataset1", listOfCells)));

        DocumentiStat received = transformStatisical.transformTranspose(documentiStat);

        assertNotNull(received);
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(1, "A", Float.valueOf(2.0f))));
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(1, "B", Float.valueOf(3.0f))));
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(1, "C", Float.valueOf(1.0f))));

    }

    @Test
    public void shouldTestTransposeColumnToLineWithOneLineZeroValues() throws Exception {

        List<Cell<Integer, String>> listOfCells = newArrayList(makeCell(1, "A", 0.0f));
        DocumentiStat documentiStat = makeDocumentiStat(newArrayList(makeDataset("dataset1", listOfCells)));

        DocumentiStat received = transformStatisical.transformTranspose(documentiStat);

        assertNotNull(received);
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(1, "A", Float.valueOf(0.0f))));

    }

    @Test
    public void shouldTestTransposeColumnToLineWithOneLineNegativeValues() throws Exception {

        List<Cell<Integer, String>> listOfCells = newArrayList(makeCell(1, "A", -2.0f), makeCell(2, "A", -4.0f),
                makeCell(3, "A", -3.0f), makeCell(4, "A", -1.0f));
        DocumentiStat documentiStat = makeDocumentiStat(newArrayList(makeDataset("dataset1", listOfCells)));

        DocumentiStat received = transformStatisical.transformTranspose(documentiStat);

        assertNotNull(received);
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(1, "A", Float.valueOf(-2.0f))));
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(1, "B", Float.valueOf(-4.0f))));
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(1, "C", Float.valueOf(-3.0f))));
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(1, "D", Float.valueOf(-1.0f))));

    }

    @Test
    public void shouldTestTransposeColumnToLineWithTwoLine() throws Exception {

        List<Cell<Integer, String>> listOfCells = newArrayList(makeCell(1, "A", -2.0f), makeCell(2, "A", -4.0f),
                makeCell(3, "A", -3.0f), makeCell(4, "A", -1.0f), makeCell(1, "B", -2.0f), makeCell(2, "B", -4.0f),
                makeCell(3, "B", -3.0f), makeCell(4, "B", -1.0f));
        DocumentiStat documentiStat = makeDocumentiStat(newArrayList(makeDataset("dataset1", listOfCells)));

        DocumentiStat received = transformStatisical.transformTranspose(documentiStat);

        assertNotNull(received);
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(1, "A", Float.valueOf(-2.0f))));
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(1, "B", Float.valueOf(-4.0f))));
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(1, "C", Float.valueOf(-3.0f))));
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(1, "D", Float.valueOf(-1.0f))));

        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(2, "A", Float.valueOf(-2.0f))));
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(2, "B", Float.valueOf(-4.0f))));
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(2, "C", Float.valueOf(-3.0f))));
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(2, "D", Float.valueOf(-1.0f))));

    }

    @Test
    public void shouldTestScaleWithNull() {
        assertNull(transformStatisical.transformScale(null, 0.0f));
    }

    @Test
    public void shouldTestScaleWithEmpty() {
        assertNull(transformStatisical.transformScale(make(a(_documentiStat)), 0.0f));
    }

    @Test
    public void shouldTestScaleWithOneLine() {

        List<Cell<Integer, String>> listOfCells = newArrayList(makeCell(1, "A", 2.0f), makeCell(1, "B", 3.0f),
                makeCell(1, "C", 1.0f));
        DocumentiStat documentiStat = makeDocumentiStat(newArrayList(makeDataset("dataset1", listOfCells)));

        DocumentiStat received = transformStatisical.transformScale(documentiStat, 2.0f);

        assertNotNull(received);
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(1, "A", Float.valueOf(4.0f))));
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(1, "B", Float.valueOf(6.0f))));
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(1, "C", Float.valueOf(2.0f))));

    }

    @Test
    public void shouldTestScaleWithMatrix() {

        List<Cell<Integer, String>> listOfCells = newArrayList(makeCell(1, "A", 2.0f), makeCell(1, "B", 3.0f),
                makeCell(1, "C", 1.0f), makeCell(2, "A", 2.0f), makeCell(2, "B", 3.0f), makeCell(2, "C", 1.0f));
        DocumentiStat documentiStat = makeDocumentiStat(newArrayList(makeDataset("dataset1", listOfCells)));

        DocumentiStat received = transformStatisical.transformScale(documentiStat, 2.0f);

        assertNotNull(received);
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(1, "A", Float.valueOf(4.0f))));
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(1, "B", Float.valueOf(6.0f))));
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(1, "C", Float.valueOf(2.0f))));
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(2, "A", Float.valueOf(4.0f))));
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(2, "B", Float.valueOf(6.0f))));
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(2, "C", Float.valueOf(2.0f))));

    }

    @Test
    public void shouldTestScaleWithOneLineZeroValue() {

        List<Cell<Integer, String>> listOfCells = newArrayList(makeCell(1, "A", 0.0f));
        DocumentiStat documentiStat = makeDocumentiStat(newArrayList(makeDataset("dataset1", listOfCells)));

        DocumentiStat received = transformStatisical.transformScale(documentiStat, 10.0f);

        assertNotNull(received);
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(1, "A", Float.valueOf(0.0f))));

    }

    @Test
    public void shouldTestScaleWithOneLineNegativeValue() {

        List<Cell<Integer, String>> listOfCells = newArrayList(makeCell(1, "A", -10.0f), makeCell(1, "B", -3.0f),
                makeCell(1, "C", 1.0f));
        DocumentiStat documentiStat = makeDocumentiStat(newArrayList(makeDataset("dataset1", listOfCells)));

        DocumentiStat received = transformStatisical.transformScale(documentiStat, 2.0f);

        assertNotNull(received);
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(1, "A", Float.valueOf(-20.0f))));
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(1, "B", Float.valueOf(-6.0f))));
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(1, "C", Float.valueOf(2.0f))));

    }

    @Test
    public void shouldTestAddScaleWithNull() {
        assertNull(transformStatisical.transformAddScalar(null, 0.0f));
    }

    @Test
    public void shouldTestAddScaleWithEmpty() {
        assertNull(transformStatisical.transformAddScalar(make(a(_documentiStat)), 0.0f));
    }

    @Test
    public void shouldTestAddScaleWithOneLine() {

        List<Cell<Integer, String>> listOfCells = newArrayList(makeCell(1, "A", 2.0f), makeCell(1, "B", 3.0f),
                makeCell(1, "C", 1.0f));
        DocumentiStat documentiStat = makeDocumentiStat(newArrayList(makeDataset("dataset1", listOfCells)));

        DocumentiStat received = transformStatisical.transformAddScalar(documentiStat, 2.0f);

        assertNotNull(received);
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(1, "A", Float.valueOf(4.0f))));
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(1, "B", Float.valueOf(5.0f))));
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(1, "C", Float.valueOf(3.0f))));

    }

    @Test
    public void shouldTestAddScaleWithMatrix() {

        List<Cell<Integer, String>> listOfCells = newArrayList(makeCell(1, "A", 2.0f), makeCell(1, "B", 3.0f),
                makeCell(1, "C", 1.0f), makeCell(2, "A", 2.0f), makeCell(2, "B", 3.0f), makeCell(2, "C", 1.0f));
        DocumentiStat documentiStat = makeDocumentiStat(newArrayList(makeDataset("dataset1", listOfCells)));

        DocumentiStat received = transformStatisical.transformAddScalar(documentiStat, 2.0f);

        assertNotNull(received);
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(1, "A", Float.valueOf(4.0f))));
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(1, "B", Float.valueOf(5.0f))));
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(1, "C", Float.valueOf(3.0f))));
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(2, "A", Float.valueOf(4.0f))));
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(2, "B", Float.valueOf(5.0f))));
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(2, "C", Float.valueOf(3.0f))));

    }

    @Test
    public void shouldTestAddScaleWithOneValue() {

        List<Cell<Integer, String>> listOfCells = newArrayList(makeCell(1, "A", 0.0f));
        DocumentiStat documentiStat = makeDocumentiStat(newArrayList(makeDataset("dataset1", listOfCells)));

        DocumentiStat received = transformStatisical.transformAddScalar(documentiStat, 2.0f);

        assertNotNull(received);
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(1, "A", Float.valueOf(2.0f))));

    }

    @Test
    public void shouldTestAddScaleWithOneLineNegativeValue() {

        List<Cell<Integer, String>> listOfCells = newArrayList(makeCell(1, "A", -10.0f), makeCell(1, "B", -3.0f),
                makeCell(1, "C", 1.0f));
        DocumentiStat documentiStat = makeDocumentiStat(newArrayList(makeDataset("dataset1", listOfCells)));

        DocumentiStat received = transformStatisical.transformAddScalar(documentiStat, -2.0f);

        assertNotNull(received);
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(1, "A", Float.valueOf(-12.0f))));
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(1, "B", Float.valueOf(-5.0f))));
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(1, "C", Float.valueOf(-1.0f))));
    }

    @Test
    public void shouldTestAddTwoDatasetsWithNull() throws Exception {
        assertNull(transformStatisical.transformAddTwoDatasets(null));
    }

    @Test
    public void shouldTestAddTwoDatasetsWithEmpty() throws Exception {
        assertNull(transformStatisical.transformAddTwoDatasets(make(a(_documentiStat))));
    }

    @Test(expected = TransformException.class)
    public void shouldTestAddTwoDatasetsWithDifferentSize() throws Exception {

        List<Cell<Integer, String>> listOfCellsDataset1 = newArrayList(makeCell(1, "A", 1.0f), makeCell(1, "B", 2.0f),
                makeCell(1, "C", 3.0f), makeCell(2, "A", 3.0f), makeCell(2, "B", 2.0f), makeCell(2, "C", 1.0f));
        List<Cell<Integer, String>> listOfCellsDataset2 = newArrayList(makeCell(1, "A", 1.0f), makeCell(1, "B", 2.0f),
                makeCell(1, "C", 3.0f));
        DocumentiStat documentiStat = makeDocumentiStat(
                newArrayList(makeDataset("dataset1", listOfCellsDataset1), makeDataset("dataset2", listOfCellsDataset2)));

        transformStatisical.transformAddTwoDatasets(documentiStat);
    }

    @Test
    public void shouldTestAddTwoDatasetsWithZeroValues() throws Exception {

        List<Cell<Integer, String>> listOfCellsDataset1 = newArrayList(makeCell(1, "A", 0.0f), makeCell(1, "B", 0.0f),
                makeCell(1, "C", 0.0f), makeCell(2, "A", 0.0f), makeCell(2, "B", 0.0f), makeCell(2, "C", 0.0f));
        List<Cell<Integer, String>> listOfCellsDataset2 = newArrayList(makeCell(1, "A", 0.0f), makeCell(1, "B", 0.0f),
                makeCell(1, "C", 0.0f), makeCell(2, "A", 0.0f), makeCell(2, "B", 0.0f), makeCell(2, "C", 0.0f));
        DocumentiStat documentiStat = makeDocumentiStat(
                newArrayList(makeDataset("dataset1", listOfCellsDataset1), makeDataset("dataset2", listOfCellsDataset2)));

        DocumentiStat received = transformStatisical.transformAddTwoDatasets(documentiStat);

        assertNotNull(received);
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(1, "A", Float.valueOf(0.0f))));
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(1, "B", Float.valueOf(0.0f))));
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(1, "C", Float.valueOf(0.0f))));
    }

    @Test
    public void shouldTestAddTwoDatasetsWithFourValues() throws Exception {

        List<Cell<Integer, String>> listOfCellsDataset1 = newArrayList(makeCell(1, "A", 1.0f), makeCell(1, "B", 2.0f),
                makeCell(1, "C", 3.0f), makeCell(1, "D", 4.0f), makeCell(2, "A", 5.0f), makeCell(2, "B", 6.0f),
                makeCell(2, "C", 7.0f), makeCell(2, "D", 8.0f));
        List<Cell<Integer, String>> listOfCellsDataset2 = newArrayList(makeCell(1, "A", 5.0f), makeCell(1, "B", 6.0f),
                makeCell(1, "C", 7.0f), makeCell(1, "D", 8.0f), makeCell(2, "A", 1.0f), makeCell(2, "B", 2.0f),
                makeCell(2, "C", 3.0f), makeCell(2, "D", 4.0f));
        DocumentiStat documentiStat = makeDocumentiStat(
                newArrayList(makeDataset("dataset1", listOfCellsDataset1), makeDataset("dataset2", listOfCellsDataset2)));

        DocumentiStat received = transformStatisical.transformAddTwoDatasets(documentiStat);

        assertNotNull(received);
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(1, "A", Float.valueOf(6.0f))));
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(1, "B", Float.valueOf(8.0f))));
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(1, "C", Float.valueOf(10.0f))));
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(1, "D", Float.valueOf(12.0f))));

        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(2, "A", Float.valueOf(6.0f))));
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(2, "B", Float.valueOf(8.0f))));
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(2, "C", Float.valueOf(10.0f))));
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(2, "D", Float.valueOf(12.0f))));
    }

    @Test
    public void shouldTestAddTwoDatasetsWithNegativeValues() throws Exception {

        List<Cell<Integer, String>> listOfCellsDataset1 = newArrayList(makeCell(1, "A", 1.0f), makeCell(1, "B", -2.0f),
                makeCell(1, "C", 3.0f), makeCell(2, "A", 5.0f), makeCell(2, "B", 6.0f), makeCell(2, "C", 7.0f));
        List<Cell<Integer, String>> listOfCellsDataset2 = newArrayList(makeCell(1, "A", 5.0f), makeCell(1, "B", 6.0f),
                makeCell(1, "C", -7.0f), makeCell(2, "A", 1.0f), makeCell(2, "B", 2.0f), makeCell(2, "C", 3.0f));
        DocumentiStat documentiStat = makeDocumentiStat(
                newArrayList(makeDataset("dataset1", listOfCellsDataset1), makeDataset("dataset2", listOfCellsDataset2)));

        DocumentiStat received = transformStatisical.transformAddTwoDatasets(documentiStat);

        assertNotNull(received);
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(1, "A", Float.valueOf(6.0f))));
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(1, "B", Float.valueOf(4.0f))));
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(1, "C", Float.valueOf(-4.0f))));

        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(2, "A", Float.valueOf(6.0f))));
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(2, "B", Float.valueOf(8.0f))));
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(2, "C", Float.valueOf(10.0f))));
    }

    @Test
    public void shouldTestMultiplyTwoDatasetsWithNull() throws Exception {
        assertNull(transformStatisical.transformAddTwoDatasets(null));
    }

    @Test
    public void shouldTestMultiplyTwoDatasetsWithEmpty() throws Exception {
        assertNull(transformStatisical.transformAddTwoDatasets(make(a(_documentiStat))));
    }

    @Test(expected = TransformException.class)
    public void shouldTestMultiplyTwoDatasetsWithDifferentSize() throws Exception {

        List<Cell<Integer, String>> listOfCellsDataset1 = newArrayList(makeCell(1, "A", 1.0f), makeCell(1, "B", 2.0f),
                makeCell(1, "C", 3.0f), makeCell(2, "A", 3.0f), makeCell(2, "B", 2.0f), makeCell(2, "C", 1.0f));
        List<Cell<Integer, String>> listOfCellsDataset2 = newArrayList(makeCell(1, "A", 1.0f), makeCell(1, "B", 2.0f),
                makeCell(1, "C", 3.0f));
        DocumentiStat documentiStat = makeDocumentiStat(
                newArrayList(makeDataset("dataset1", listOfCellsDataset1), makeDataset("dataset2", listOfCellsDataset2)));

        transformStatisical.transformMultiplyTwoDatasets(documentiStat);
    }

    @Test
    public void shouldTestMultiplyTwoDatasetsWithZeros() throws Exception {

        List<Cell<Integer, String>> listOfCellsDataset1 = newArrayList(makeCell(1, "A", 0.0f), makeCell(1, "B", 0.0f),
                makeCell(1, "C", 0.0f), makeCell(2, "A", 0.0f), makeCell(2, "B", 0.0f), makeCell(2, "C", 0.0f));
        List<Cell<Integer, String>> listOfCellsDataset2 = newArrayList(makeCell(1, "A", 0.0f), makeCell(1, "B", 0.0f),
                makeCell(1, "C", 0.0f), makeCell(2, "A", 0.0f), makeCell(2, "B", 0.0f), makeCell(2, "C", 0.0f),
                makeCell(3, "A", 0.0f), makeCell(3, "B", 0.0f), makeCell(3, "C", 0.0f));
        DocumentiStat documentiStat = makeDocumentiStat(
                newArrayList(makeDataset("dataset1", listOfCellsDataset1), makeDataset("dataset2", listOfCellsDataset2)));

        DocumentiStat received = transformStatisical.transformMultiplyTwoDatasets(documentiStat);

        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(1, "A", Float.valueOf(0.0f))));
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(1, "B", Float.valueOf(0.0f))));
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(1, "C", Float.valueOf(0.0f))));

        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(2, "A", Float.valueOf(0.0f))));
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(2, "B", Float.valueOf(0.0f))));
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(2, "C", Float.valueOf(0.0f))));

    }

    @Test
    public void shouldTestMultiplyTwoDatasetsWithDifferenteSize() throws Exception {

        List<Cell<Integer, String>> listOfCellsDataset1 = newArrayList(makeCell(1, "A", 1.0f), makeCell(1, "B", 2.0f),
                makeCell(1, "C", 3.0f), makeCell(2, "A", 4.0f), makeCell(2, "B", 5.0f), makeCell(2, "C", 6.0f));
        List<Cell<Integer, String>> listOfCellsDataset2 = newArrayList(makeCell(1, "A", 7.0f), makeCell(1, "B", 8.0f),
                makeCell(2, "A", 9.0f), makeCell(2, "B", 10.0f), makeCell(3, "A", 11.0f), makeCell(3, "B", 12.0f));
        DocumentiStat documentiStat = makeDocumentiStat(
                newArrayList(makeDataset("dataset1", listOfCellsDataset1), makeDataset("dataset2", listOfCellsDataset2)));

        DocumentiStat received = transformStatisical.transformMultiplyTwoDatasets(documentiStat);

        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(1, "A", Float.valueOf(58.0f))));
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(1, "B", Float.valueOf(64.0f))));

        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(2, "A", Float.valueOf(139.0f))));
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(2, "B", Float.valueOf(154.0f))));
    }

    @Test
    public void shouldTestMultiplyTwoDatasetsWithNegativeValue() throws Exception {

        List<Cell<Integer, String>> listOfCellsDataset1 = newArrayList(makeCell(1, "A", 1.0f), makeCell(1, "B", -2.0f),
                makeCell(2, "A", 3.0f), makeCell(2, "B", 4.0f));
        List<Cell<Integer, String>> listOfCellsDataset2 = newArrayList(makeCell(1, "A", 2.0f), makeCell(1, "B", 0.0f),
                makeCell(2, "A", -1.0f), makeCell(2, "B", 2.0f));
        DocumentiStat documentiStat = makeDocumentiStat(
                newArrayList(makeDataset("dataset1", listOfCellsDataset1), makeDataset("dataset2", listOfCellsDataset2)));

        DocumentiStat received = transformStatisical.transformMultiplyTwoDatasets(documentiStat);

        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(1, "A", Float.valueOf(4.0f))));
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(1, "B", Float.valueOf(-4.0f))));

        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(2, "A", Float.valueOf(2.0f))));
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(2, "B", Float.valueOf(8.0f))));
    }

    @Test
    public void shouldTestInterpolationTwoDatasetsWithNull() throws Exception {
        assertNull(transformStatisical.transformInterpolation(null, TransformType.LINE));
        assertNull(transformStatisical.transformInterpolation(null, TransformType.COLUMN));
    }

    @Test
    public void shouldTestInterpolationTwoDatasetsWithEmpty() throws Exception {
        assertNull(transformStatisical.transformInterpolation(make(a(_documentiStat)), TransformType.LINE));
        assertNull(transformStatisical.transformInterpolation(make(a(_documentiStat)), TransformType.COLUMN));
    }

    @Test
    public void shouldTestInterpolationLineTwoDatasetsWithTwoLines() throws Exception {

        List<Cell<Integer, String>> listOfCellsDataset1 = newArrayList(makeCell(1, "A", 1.0f), makeCell(1, "B", 2.0f),
                makeCell(1, "C", 3.0f), makeCell(2, "A", 3.0f), makeCell(2, "B", 2.0f), makeCell(2, "C", 1.0f));

        DocumentiStat documentiStat = makeDocumentiStat(newArrayList(makeDataset("dataset1", listOfCellsDataset1)));

        DocumentiStat received = transformStatisical.transformInterpolation(documentiStat, TransformType.LINE);

        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(1, "A", Float.valueOf(1.0f))));
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(1, "B", Float.valueOf(2.0f))));
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(1, "C", Float.valueOf(3.0f))));

        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(2, "A", Float.valueOf(2.0f))));
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(2, "B", Float.valueOf(2.0f))));
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(2, "C", Float.valueOf(2.0f))));

        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(3, "A", Float.valueOf(3.0f))));
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(3, "B", Float.valueOf(2.0f))));
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(3, "C", Float.valueOf(1.0f))));

    }

    @Test
    public void shouldTestInterpolationLineTwoDatasetsWithThreeLines() throws Exception {

        List<Cell<Integer, String>> listOfCellsDataset1 = newArrayList(makeCell(1, "A", 1.0f), makeCell(1, "B", 2.0f),
                makeCell(1, "C", 3.0f), makeCell(2, "A", 3.0f), makeCell(2, "B", 2.0f), makeCell(2, "C", 1.0f),
                makeCell(3, "A", 1.0f), makeCell(3, "B", 2.0f), makeCell(3, "C", 3.0f));

        DocumentiStat documentiStat = makeDocumentiStat(newArrayList(makeDataset("dataset1", listOfCellsDataset1)));

        DocumentiStat received = transformStatisical.transformInterpolation(documentiStat, TransformType.LINE);

        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(1, "A", Float.valueOf(1.0f))));
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(1, "B", Float.valueOf(2.0f))));
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(1, "C", Float.valueOf(3.0f))));

        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(2, "A", Float.valueOf(2.0f))));
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(2, "B", Float.valueOf(2.0f))));
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(2, "C", Float.valueOf(2.0f))));

        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(3, "A", Float.valueOf(3.0f))));
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(3, "B", Float.valueOf(2.0f))));
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(3, "C", Float.valueOf(1.0f))));

        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(4, "A", Float.valueOf(2.0f))));
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(4, "B", Float.valueOf(2.0f))));
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(4, "C", Float.valueOf(2.0f))));

        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(5, "A", Float.valueOf(1.0f))));
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(5, "B", Float.valueOf(2.0f))));
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(5, "C", Float.valueOf(3.0f))));

    }

    @Test
    public void shouldTestInterpolationLineTwoDatasetsWithZeroValues() throws Exception {

        List<Cell<Integer, String>> listOfCellsDataset1 = newArrayList(makeCell(1, "A", 0.0f), makeCell(1, "B", 0.0f),
                makeCell(1, "C", 0.0f), makeCell(2, "A", 0.0f), makeCell(2, "B", 0.0f), makeCell(2, "C", 0.0f));
        DocumentiStat documentiStat = makeDocumentiStat(newArrayList(makeDataset("dataset1", listOfCellsDataset1)));

        DocumentiStat received = transformStatisical.transformInterpolation(documentiStat, TransformType.LINE);

        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(1, "A", Float.valueOf(0.0f))));
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(1, "B", Float.valueOf(0.0f))));
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(1, "C", Float.valueOf(0.0f))));

        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(2, "A", Float.valueOf(0.0f))));
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(2, "B", Float.valueOf(0.0f))));
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(2, "C", Float.valueOf(0.0f))));

        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(3, "A", Float.valueOf(0.0f))));
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(3, "B", Float.valueOf(0.0f))));
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(3, "C", Float.valueOf(0.0f))));

    }

    @Test
    public void shouldTestInterpolationLineTwoDatasetsWithNegativeValues() throws Exception {

        List<Cell<Integer, String>> listOfCellsDataset1 = newArrayList(makeCell(1, "A", 1.0f), makeCell(1, "B", -2.0f),
                makeCell(2, "A", -3.0f), makeCell(2, "B", 2.0f));
        DocumentiStat documentiStat = makeDocumentiStat(newArrayList(makeDataset("dataset1", listOfCellsDataset1)));

        DocumentiStat received = transformStatisical.transformInterpolation(documentiStat, TransformType.LINE);

        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(1, "A", Float.valueOf(1.0f))));
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(1, "B", Float.valueOf(-2.0f))));

        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(2, "A", Float.valueOf(-1.0f))));
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(2, "B", Float.valueOf(0.0f))));

        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(3, "A", Float.valueOf(-3.0f))));
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(3, "B", Float.valueOf(2.0f))));

    }

    @Test
    public void shouldTestInterpolationColumnTwoDatasetsWithThreeColumns() throws Exception {

        List<Cell<Integer, String>> listOfCellsDataset1 = newArrayList(makeCell(1, "A", 1.0f), makeCell(1, "B", 2.0f),
                makeCell(1, "C", 3.0f), makeCell(2, "A", 3.0f), makeCell(2, "B", 2.0f), makeCell(2, "C", 1.0f));

        DocumentiStat documentiStat = makeDocumentiStat(newArrayList(makeDataset("dataset1", listOfCellsDataset1)));

        DocumentiStat received = transformStatisical.transformInterpolation(documentiStat, TransformType.COLUMN);

        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(1, "A", Float.valueOf(1.0f))));
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(1, "B", Float.valueOf(1.5f))));
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(1, "C", Float.valueOf(2.0f))));
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(1, "D", Float.valueOf(2.5f))));

        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(2, "A", Float.valueOf(3.0f))));
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(2, "B", Float.valueOf(2.5f))));
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(2, "C", Float.valueOf(2.0f))));
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(2, "D", Float.valueOf(1.5f))));

    }

    @Test
    public void shouldTestInterpolationColumnTwoDatasetsWithZeroValues() throws Exception {

        List<Cell<Integer, String>> listOfCellsDataset1 = newArrayList(makeCell(1, "A", 0.0f), makeCell(1, "B", 0.0f),
                makeCell(1, "C", 0.0f));
        DocumentiStat documentiStat = makeDocumentiStat(newArrayList(makeDataset("dataset1", listOfCellsDataset1)));

        DocumentiStat received = transformStatisical.transformInterpolation(documentiStat, TransformType.COLUMN);

        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(1, "A", Float.valueOf(0.0f))));
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(1, "B", Float.valueOf(0.0f))));
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(1, "C", Float.valueOf(0.0f))));
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(1, "D", Float.valueOf(0.0f))));
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(1, "E", Float.valueOf(0.0f))));

    }

    @Test
    public void shouldTestInterpolationColumnTwoDatasetsWithNegativeValues() throws Exception {

        List<Cell<Integer, String>> listOfCellsDataset1 = newArrayList(makeCell(1, "A", 1.0f), makeCell(1, "B", -2.0f),
                makeCell(2, "A", -3.0f), makeCell(2, "B", 2.0f));
        DocumentiStat documentiStat = makeDocumentiStat(newArrayList(makeDataset("dataset1", listOfCellsDataset1)));

        DocumentiStat received = transformStatisical.transformInterpolation(documentiStat, TransformType.COLUMN);

        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(1, "A", Float.valueOf(1.0f))));
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(1, "B", Float.valueOf(-0.5f))));
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(1, "C", Float.valueOf(-2.0f))));

        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(2, "A", Float.valueOf(-3.0f))));
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(2, "B", Float.valueOf(-0.5f))));
        assertTrue(received.getDatasets().stream().anyMatch(assertHasCell(2, "C", Float.valueOf(2.0f))));
    }

}
