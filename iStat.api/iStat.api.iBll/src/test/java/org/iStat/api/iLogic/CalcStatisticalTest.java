package org.iStat.api.iLogic;

import static com.google.common.collect.Lists.newArrayList;
import static com.natpryce.makeiteasy.MakeItEasy.a;
import static com.natpryce.makeiteasy.MakeItEasy.make;
import static org.iStat.api.iDomain.makeit.DocumentiStatMakeIt._documentiStat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.iStat.api.iDomain.Cell;
import org.iStat.api.iDomain.DocumentiStat;
import org.iStat.api.iDomain.DocumentiStat.DocumentiStatBuilder;
import org.iStat.api.iExceptions.CalcException;
import org.junit.Test;

public class CalcStatisticalTest extends AbstractUtilsiLogicTest {

    private static CalcStatistical calcStatisical = new CalcStatistical();

    /*
     * ----------------------------------------------------------------------
     * EN - Median
     * ----------------------------------------------------------------------
     */

    @Test
    public void calculateMedianInputNull() {
        Float received = calcStatisical.calculateMedian(null);
        assertNull(received);
    }

    @Test
    public void calculateMedianInputEmpty() {
        Float received = calcStatisical
            .calculateMedian(make(a(_documentiStat)));
        assertNull(received);
    }

    @Test
    public void calculateMedianInputValid() {

        List<Cell<Integer, String>> listOfCells = newArrayList(
                makeCell(3, "A", 1.1f), makeCell(4, "A", 1.3f));
        DocumentiStat documentiStat = makeDocumentiStat(
                newArrayList(makeDataset("dataset1", listOfCells)));

        Float received = calcStatisical
            .calculateMedian(documentiStat);
        Float expected = new Float("1.2");
        assertEquals(expected, received);
    }

    @Test
    public void calculateMedianInputValidWithThreeValues() {

        List<Cell<Integer, String>> listOfCells = newArrayList(
                makeCell(3, "A", -10.0f), makeCell(4, "A", 20f),
                makeCell(4, "A", 5f), makeCell(5, "A", 3f));
        DocumentiStat documentiStat = makeDocumentiStat(
                newArrayList(makeDataset("dataset1", listOfCells)));

        Float received = calcStatisical
            .calculateMedian(documentiStat);

        assertEquals(Float.valueOf(4.5f), received);
    }

    @Test
    public void calculateMedianInputValidWithOneValue() {

        List<Cell<Integer, String>> listOfCells = newArrayList(
                makeCell(3, "A", 1.0f));
        DocumentiStat documentiStat = makeDocumentiStat(
                newArrayList(makeDataset("dataset1", listOfCells)));

        Float received = calcStatisical
            .calculateMedian(documentiStat);

        assertEquals(Float.valueOf(1.0f), received);
    }

    @Test
    public void calculateMedianInputValidWithZeroValue() {

        List<Cell<Integer, String>> listOfCells = newArrayList(
                makeCell(3, "A", 0.0f), makeCell(4, "A", 0.0f));
        DocumentiStat documentiStat = makeDocumentiStat(
                newArrayList(makeDataset("dataset1", listOfCells)));

        Float received = calcStatisical
            .calculateMedian(documentiStat);

        assertEquals(Float.valueOf(0.0f), received);
    }

    @Test
    public void calculateMedianInputWithNegatives() {

        List<Cell<Integer, String>> listOfCells = newArrayList(
                makeCell(3, "A", -10.0f), makeCell(4, "A", -10.0f));
        DocumentiStat documentiStat = makeDocumentiStat(
                newArrayList(makeDataset("dataset1", listOfCells)));

        Float received = calcStatisical
            .calculateMedian(documentiStat);

        assertEquals(Float.valueOf(-10.0f), received);
    }

    /*
     * ----------------------------------------------------------------------
     * EN - Geometric Mean
     * ----------------------------------------------------------------------
     */

    @Test
    public void calculateGeometricMeanInputNull() throws Exception {
        Float received = calcStatisical.calculateGeometricMean(null);
        assertNull(received);
    }

    @Test
    public void calculateGeometricMeanInputEmpty() throws Exception {
        Float received = calcStatisical
            .calculateGeometricMean(make(a(_documentiStat)));
        assertNull(received);
    }

    @Test
    public void calculateGeometricMeanInputValid() throws Exception {
        List<Cell<Integer, String>> listOfCells = newArrayList(
                makeCell(3, "A", 4.0f), makeCell(4, "A", 2.0f),
                makeCell(5, "A", 1.0f));
        DocumentiStat documentiStat = makeDocumentiStat(
                newArrayList(makeDataset("dataset1", listOfCells)));
        Float received = calcStatisical
            .calculateGeometricMean(documentiStat);
        Float expected = new Float("2.0");
        assertEquals(expected, received);
    }

    @Test
    public void calculateGeometricMeanInputValidWith3Values()
            throws Exception {
        List<Cell<Integer, String>> listOfCells = newArrayList(
                makeCell(3, "A", 3.0f), makeCell(4, "A", 4.0f),
                makeCell(5, "A", 8.0f));
        DocumentiStat documentiStat = makeDocumentiStat(
                newArrayList(makeDataset("dataset1", listOfCells)));
        Float received = calcStatisical
            .calculateGeometricMean(documentiStat);

        assertEquals(Float.valueOf(4.58f), received);
    }

    @Test
    public void calculateGeometricMeanInputValidWith1Value()
            throws Exception {
        List<Cell<Integer, String>> listOfCells = newArrayList(
                makeCell(3, "A", 3.0f));
        DocumentiStat documentiStat = makeDocumentiStat(
                newArrayList(makeDataset("dataset1", listOfCells)));
        Float received = calcStatisical
            .calculateGeometricMean(documentiStat);

        assertEquals(Float.valueOf(3.0f), received);
    }

    @Test(expected = CalcException.class)
    public void calculateGeometricMeanInputValidWithNegativeValue()
            throws Exception {
        List<Cell<Integer, String>> listOfCells = newArrayList(
                makeCell(3, "A", -20.0f));
        DocumentiStat documentiStat = makeDocumentiStat(
                newArrayList(makeDataset("dataset1", listOfCells)));
        Float received = calcStatisical
            .calculateGeometricMean(documentiStat);
    }

    /*
     * ----------------------------------------------------------------------
     * EN - Mode
     * ----------------------------------------------------------------------
     */

    @Test
    public void calculateModeInputNull() {
        Float received = calcStatisical.calculateMode(null);
        assertNull(received);
    }

    @Test
    public void calculateModeInputEmpty() {
        Float received = calcStatisical
            .calculateMode(make(a(_documentiStat)));
        assertNull(received);
    }

    @Test
    public void calculateModeInputValid() {
        List<Cell<Integer, String>> listOfCells = newArrayList(
                makeCell(3, "A", 1.0f), makeCell(4, "A", 4.0f),
                makeCell(5, "A", 4.0f), makeCell(6, "A", 4.0f),
                makeCell(7, "A", 1.0f), makeCell(8, "A", 6.0f));
        DocumentiStat documentiStat = makeDocumentiStat(
                newArrayList(makeDataset("dataset1", listOfCells)));
        Float received = calcStatisical.calculateMode(documentiStat);
        Float expected = new Float("4.0");
        assertEquals(expected, received);
    }

    @Test
    public void calculateModeInputValidWithFiveValues() {
        List<Cell<Integer, String>> listOfCells = newArrayList(
                makeCell(3, "A", 4.0f), makeCell(4, "A", 5.0f),
                makeCell(5, "A", 4.0f), makeCell(6, "A", 3.0f),
                makeCell(7, "A", 5.0f));
        DocumentiStat documentiStat = makeDocumentiStat(
                newArrayList(makeDataset("dataset1", listOfCells)));
        Float received = calcStatisical.calculateMode(documentiStat);

        assertEquals(Float.valueOf(4), received);
    }

    @Test
    public void calculateModeInputValidWithFourValues() {
        List<Cell<Integer, String>> listOfCells = newArrayList(
                makeCell(3, "A", 1.0f), makeCell(4, "A", 2.0f),
                makeCell(5, "A", 3.0f), makeCell(6, "A", 3.0f));
        DocumentiStat documentiStat = makeDocumentiStat(
                newArrayList(makeDataset("dataset1", listOfCells)));
        Float received = calcStatisical.calculateMode(documentiStat);

        assertEquals(Float.valueOf(3.0f), received);
    }

    @Test
    public void calculateModeInputValidWithNegativeValues() {
        List<Cell<Integer, String>> listOfCells = newArrayList(
                makeCell(3, "A", 0.0f), makeCell(4, "A", 1.0f),
                makeCell(5, "A", -10.0f), makeCell(6, "A", -10.0f),
                makeCell(6, "A", -10.0f));
        DocumentiStat documentiStat = makeDocumentiStat(
                newArrayList(makeDataset("dataset1", listOfCells)));
        Float received = calcStatisical.calculateMode(documentiStat);

        assertEquals(Float.valueOf(-10.0f), received);
    }

    @Test
    public void calculateModeInputValidWithFiveNegativeValues() {
        List<Cell<Integer, String>> listOfCells = newArrayList(
                makeCell(3, "A", -4.0f), makeCell(4, "A", -1.0f),
                makeCell(5, "A", -1.0f), makeCell(6, "A", -5.0f),
                makeCell(6, "A", -5.0f));
        DocumentiStat documentiStat = makeDocumentiStat(
                newArrayList(makeDataset("dataset1", listOfCells)));
        Float received = calcStatisical.calculateMode(documentiStat);

        assertEquals(Float.valueOf(-5.0f), received);
    }

    /*
     * ----------------------------------------------------------------------
     * EN - Midrange
     * ----------------------------------------------------------------------
     */

    @Test
    public void calculateMidrangeInputNull() {
        Float received = calcStatisical.calculateMidrange(null);
        assertNull(received);
    }

    @Test
    public void calculateMidrangeInputEmpty() {
        Float received = calcStatisical
            .calculateMidrange(make(a(_documentiStat)));
        assertNull(received);
    }

    @Test
    public void calculateMidrangeInputValid() {
        List<Cell<Integer, String>> listOfCells = newArrayList(
                makeCell(3, "A", 3.0f), makeCell(4, "A", 4.0f),
                makeCell(5, "A", 10.0f), makeCell(6, "A", 11.0f));
        DocumentiStat documentiStat = makeDocumentiStat(
                newArrayList(makeDataset("dataset1", listOfCells)));
        Float received = calcStatisical
            .calculateMidrange(documentiStat);
        Float expected = new Float("7.0");
        assertEquals(expected, received);
    }

    @Test
    public void calculateMidrangeInputValidWithThreeValues() {
        List<Cell<Integer, String>> listOfCells = newArrayList(
                makeCell(3, "A", 60.0f), makeCell(4, "A", 70.0f),
                makeCell(5, "A", 20.0f));
        DocumentiStat documentiStat = makeDocumentiStat(
                newArrayList(makeDataset("dataset1", listOfCells)));
        Float received = calcStatisical
            .calculateMidrange(documentiStat);

        assertEquals(Float.valueOf(45.0f), received);
    }

    @Test
    public void calculateMidrangeInputValidWithFourValues() {
        List<Cell<Integer, String>> listOfCells = newArrayList(
                makeCell(3, "A", 0.0f), makeCell(4, "A", 20.0f),
                makeCell(5, "A", 10.0f), makeCell(5, "A", 3.0f));
        DocumentiStat documentiStat = makeDocumentiStat(
                newArrayList(makeDataset("dataset1", listOfCells)));
        Float received = calcStatisical
            .calculateMidrange(documentiStat);

        assertEquals(Float.valueOf(10.0f), received);
    }

    @Test
    public void calculateMidrangeInputValidWithNegativeValues() {
        List<Cell<Integer, String>> listOfCells = newArrayList(
                makeCell(3, "A", -10.0f), makeCell(4, "A", 10.0f),
                makeCell(5, "A", 100.0f));
        DocumentiStat documentiStat = makeDocumentiStat(
                newArrayList(makeDataset("dataset1", listOfCells)));
        Float received = calcStatisical
            .calculateMidrange(documentiStat);

        assertEquals(Float.valueOf(45.0f), received);
    }

    @Test
    public void calculateMidrangeInputValidWithZeroValues() {
        List<Cell<Integer, String>> listOfCells = newArrayList(
                makeCell(3, "A", 0.0f), makeCell(4, "A", 0.0f),
                makeCell(5, "A", 0.0f));
        DocumentiStat documentiStat = makeDocumentiStat(
                newArrayList(makeDataset("dataset1", listOfCells)));
        Float received = calcStatisical
            .calculateMidrange(documentiStat);

        assertEquals(Float.valueOf(0.0f), received);
    }

    /*
     * ----------------------------------------------------------------------
     * EN - Variance
     * ----------------------------------------------------------------------
     */

    @Test
    public void calculateVarianceInputNull() {
        Float received = calcStatisical.calculateVariance(null);
        assertNull(received);
    }

    @Test
    public void calculateVarianceInputEmpty() {

        Float received = calcStatisical
            .calculateVariance(make(a(_documentiStat)));
        assertNull(received);
    }

    @Test
    public void calculateVarianceInputValid() {
        List<Cell<Integer, String>> listOfCells = newArrayList(
                makeCell(3, "A", 17.0f), makeCell(4, "A", 15.0f),
                makeCell(5, "A", 23.0f), makeCell(6, "A", 7.0f),
                makeCell(7, "A", 9.0f), makeCell(8, "A", 13.0f));
        DocumentiStat documentiStat = makeDocumentiStat(
                newArrayList(makeDataset("dataset1", listOfCells)));

        Float received = calcStatisical
            .calculateVariance(documentiStat);
        Float expected = new Float("27.67");
        assertEquals(expected, received);
    }

    @Test
    public void calculateVarianceInputValidWithFiveValues() {
        List<Cell<Integer, String>> listOfCells = newArrayList(
                makeCell(3, "A", 4.0f), makeCell(4, "A", 9.0f),
                makeCell(5, "A", 10.0f), makeCell(6, "A", 1.0f),
                makeCell(7, "A", 6.0f));
        DocumentiStat documentiStat = makeDocumentiStat(
                newArrayList(makeDataset("dataset1", listOfCells)));

        Float received = calcStatisical
            .calculateVariance(documentiStat);

        assertEquals(Float.valueOf(10.8f), received);
    }
    
    @Test
    public void calculateVarianceInputValidWithFourValuesPDF() {
        List<Cell<Integer, String>> listOfCells = newArrayList(
                makeCell(3, "A", 68.0f), makeCell(4, "A", 20.0f),
                makeCell(5, "A", 12.0f), makeCell(6, "A", 58.0f));
        DocumentiStat documentiStat = makeDocumentiStat(
                newArrayList(makeDataset("dataset1", listOfCells)));

        Float received = calcStatisical
            .calculateVariance(documentiStat);

        assertEquals(Float.valueOf(572.75f), received);
    }
    
    @Test
    public void calculateVarianceInputValidWithFourValuesPDF2() {
        List<Cell<Integer, String>> listOfCells = newArrayList(
                makeCell(3, "A", 3.0f), makeCell(4, "A", 11.0f),
                makeCell(5, "A", 11.0f), makeCell(6, "A", 3.0f));
        DocumentiStat documentiStat = makeDocumentiStat(
                newArrayList(makeDataset("dataset1", listOfCells)));

        Float received = calcStatisical
            .calculateVariance(documentiStat);

        assertEquals(Float.valueOf(16.0f), received);
    }

    @Test
    public void calculateVarianceInputValidWithZeroValues() {
        List<Cell<Integer, String>> listOfCells = newArrayList(
                makeCell(3, "A", 0.0f), makeCell(4, "A", 0.0f),
                makeCell(5, "A", 0.0f));
        DocumentiStat documentiStat = makeDocumentiStat(
                newArrayList(makeDataset("dataset1", listOfCells)));

        Float received = calcStatisical
            .calculateVariance(documentiStat);

        assertEquals(Float.valueOf(0.0f), received);
    }

    /*
     * ----------------------------------------------------------------------
     * EN - StandardDeviation
     * ----------------------------------------------------------------------
     */

    @Test
    public void calculateStandardDeviationInputNull() {
        Float received = calcStatisical
            .calculateStandardDeviation(null);
        assertNull(received);
    }

    @Test
    public void calculateStandardDeviationInputEmpty() {
        Float received = calcStatisical.calculateStandardDeviation(
                new DocumentiStatBuilder().build());
        assertNull(received);
    }

    @Test
    public void calculateStandardDeviationInputValid() {
        List<Cell<Integer, String>> listOfCells = newArrayList(
                makeCell(3, "A", 17.0f), makeCell(4, "A", 15.0f),
                makeCell(5, "A", 23.0f), makeCell(6, "A", 7.0f),
                makeCell(7, "A", 9.0f), makeCell(8, "A", 13.0f));
        DocumentiStat documentiStat = makeDocumentiStat(
                newArrayList(makeDataset("dataset1", listOfCells)));
        Float received = calcStatisical
            .calculateStandardDeviation(documentiStat);
        Float expected = new Float("5.26");
        assertEquals(expected, received);
    }
    
    @Test
    public void calculateStandardDeviationInputValidWithFourValuesPDF() {
        List<Cell<Integer, String>> listOfCells = newArrayList(
                makeCell(3, "A", 68.0f), makeCell(4, "A", 20.0f),
                makeCell(5, "A", 12.0f), makeCell(6, "A", 58.0f));
        DocumentiStat documentiStat = makeDocumentiStat(
                newArrayList(makeDataset("dataset1", listOfCells)));

        Float received = calcStatisical
            .calculateStandardDeviation(documentiStat);

        assertEquals(Float.valueOf(23.93f), received);
    }
    
    @Test
    public void calculateStandardDeviationInputValidWithFourValuesPDF2() {
        List<Cell<Integer, String>> listOfCells = newArrayList(
                makeCell(3, "A", 3.0f), makeCell(4, "A", 11.0f),
                makeCell(5, "A", 11.0f), makeCell(6, "A", 3.0f));
        DocumentiStat documentiStat = makeDocumentiStat(
                newArrayList(makeDataset("dataset1", listOfCells)));

        Float received = calcStatisical
            .calculateStandardDeviation(documentiStat);

        assertEquals(Float.valueOf(4.00f), received);
    }

    @Test
    public void calculateStandardDeviationInputValidWithSixValues() {
        List<Cell<Integer, String>> listOfCells = newArrayList(
                makeCell(3, "A", 9.0f), makeCell(4, "A", 2.0f),
                makeCell(5, "A", 5.0f), makeCell(6, "A", 4.0f),
                makeCell(7, "A", 12.0f), makeCell(8, "A", 7.0f));
        DocumentiStat documentiStat = makeDocumentiStat(
                newArrayList(makeDataset("dataset1", listOfCells)));
        Float received = calcStatisical
            .calculateStandardDeviation(documentiStat);

        assertEquals(Float.valueOf(3.30f), received);
    }

    @Test
    public void calculateStandardDeviationInputValidWithSixValues2() {
        List<Cell<Integer, String>> listOfCells = newArrayList(
                makeCell(3, "A", 3.0f), makeCell(4, "A", 2.0f),
                makeCell(5, "A", 4.0f), makeCell(6, "A", 1.0f),
                makeCell(7, "A", 4.0f), makeCell(8, "A", 4.0f));
        DocumentiStat documentiStat = makeDocumentiStat(
                newArrayList(makeDataset("dataset1", listOfCells)));
        Float received = calcStatisical
            .calculateStandardDeviation(documentiStat);

        assertEquals(Float.valueOf(1.15f), received);
    }

    @Test
    public void calculateStandardDeviationInputValidWithNegativeValues() {
        List<Cell<Integer, String>> listOfCells = newArrayList(
                makeCell(3, "A", -3.0f), makeCell(4, "A", 2.0f),
                makeCell(5, "A", -4.0f), makeCell(6, "A", 1.0f),
                makeCell(7, "A", 4.0f), makeCell(8, "A", 4.0f));
        DocumentiStat documentiStat = makeDocumentiStat(
                newArrayList(makeDataset("dataset1", listOfCells)));
        Float received = calcStatisical
            .calculateStandardDeviation(documentiStat);

        assertEquals(Float.valueOf(3.14f), received);
    }

    @Test
    public void calculateStandardDeviationInputValidWithFourValues() {
        List<Cell<Integer, String>> listOfCells = newArrayList(
                makeCell(3, "A", 5.0f), makeCell(4, "A", 5.0f),
                makeCell(5, "A", 5.0f), makeCell(6, "A", 5.0f));
        DocumentiStat documentiStat = makeDocumentiStat(
                newArrayList(makeDataset("dataset1", listOfCells)));
        Float received = calcStatisical
            .calculateStandardDeviation(documentiStat);

        assertEquals(Float.valueOf(0.0f), received);
    }

    @Test
    public void calculateStandardDeviationInputValidWithThreeValues() {
        List<Cell<Integer, String>> listOfCells = newArrayList(
                makeCell(3, "A", -1.0f), makeCell(4, "A", -1.0f),
                makeCell(5, "A", -1.0f));
        DocumentiStat documentiStat = makeDocumentiStat(
                newArrayList(makeDataset("dataset1", listOfCells)));
        Float received = calcStatisical
            .calculateStandardDeviation(documentiStat);

        assertEquals(Float.valueOf(0.0f), received);
    }

    @Test
    public void calculateStandardDeviationInputValidWithZeroValues() {
        List<Cell<Integer, String>> listOfCells = newArrayList(
                makeCell(3, "A", 0.0f), makeCell(4, "A", 0.0f),
                makeCell(5, "A", 0.0f));
        DocumentiStat documentiStat = makeDocumentiStat(
                newArrayList(makeDataset("dataset1", listOfCells)));
        Float received = calcStatisical
            .calculateStandardDeviation(documentiStat);

        assertEquals(Float.valueOf(0.0f), received);
    }

    /*
     * ----------------------------------------------------------------------
     * EN - Total Rows/Columns
     * ----------------------------------------------------------------------
     */

    @Test
    public void calculateRowColumnTotalInputNull() {
        Float received = calcStatisical.calculateMedian(null);
        assertNull(received);
    }

    @Test
    public void calculateRowColumnTotalInputEmpty() {
        Float received = calcStatisical
            .calculateMedian(make(a(_documentiStat)));
        assertNull(received);
    }

    @Test
    public void calculateRowColumnTotalInputValid() {
        List<Cell<Integer, String>> listOfCells = newArrayList(
                makeCell(3, "A", 2.5f), makeCell(4, "A", 2.5f),
                makeCell(5, "A", 1.7f));
        DocumentiStat documentiStat = makeDocumentiStat(
                newArrayList(makeDataset("dataset1", listOfCells)));
        Float received = calcStatisical
            .calculateRowColumnTotal(documentiStat);

        assertEquals(Float.valueOf(6.7f), received);
    }

    @Test
    public void calculateRowColumnTotalInputWithNegatives() {
        List<Cell<Integer, String>> listOfCells = newArrayList(
                makeCell(3, "A", -2.5f), makeCell(4, "A", 2.5f),
                makeCell(5, "A", 1.5f));
        DocumentiStat documentiStat = makeDocumentiStat(
                newArrayList(makeDataset("dataset1", listOfCells)));
        Float received = calcStatisical
            .calculateRowColumnTotal(documentiStat);

        assertEquals(Float.valueOf(1.5f), received);
    }
}
