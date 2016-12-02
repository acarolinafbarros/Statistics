package org.iStat.api.iLogic;

import static com.google.common.collect.Lists.newArrayList;
import static com.natpryce.makeiteasy.MakeItEasy.a;
import static com.natpryce.makeiteasy.MakeItEasy.make;
import static org.iStat.api.iEntity.makeit.DocumentiStatMakeIt._documentiStat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.iStat.api.iEntity.Cell;
import org.iStat.api.iEntity.DocumentiStat;
import org.iStat.api.iEntity.DocumentiStat.DocumentiStatBuilder;
import org.junit.Ignore;
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
    public void calculateMedianInputWithNegatives() {

        List<Cell<Integer, String>> listOfCells = newArrayList(
                makeCell(3, "A", -1.0f), makeCell(4, "A", 1.0f));
        DocumentiStat documentiStat = makeDocumentiStat(
                newArrayList(makeDataset("dataset1", listOfCells)));

        Float received = calcStatisical
            .calculateMedian(documentiStat);
        Float expected = new Float("0");
        assertEquals(expected, received);
    }

    /*
     * ----------------------------------------------------------------------
     * EN - Geometric Mean
     * ----------------------------------------------------------------------
     */

    @Test
    public void calculateGeometricMeanInputNull() {
        Float received = calcStatisical.calculateGeometricMean(null);
        assertNull(received);
    }

    @Test
    public void calculateGeometricMeanInputEmpty() {
        Float received = calcStatisical
            .calculateGeometricMean(make(a(_documentiStat)));
        assertNull(received);
    }

    @Test
    public void calculateGeometricMeanInputValid() {
        List<Cell<Integer, String>> listOfCells = newArrayList(
                makeCell(3, "A", 4.0f), makeCell(4, "A", 2.0f), makeCell(5, "A", 1.0f));
        DocumentiStat documentiStat = makeDocumentiStat(
                newArrayList(makeDataset("dataset1", listOfCells)));
        Float received = calcStatisical.calculateGeometricMean(documentiStat);
        Float expected = new Float("2.0");
        assertEquals(expected, received);
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

    @Ignore
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
        Float received = calcStatisical.calculateMidrange(documentiStat);
        Float expected = new Float("7.0");
        assertEquals(expected, received);
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
        Float expected = new Float("33.2");
        assertEquals(expected, received);
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

    @Ignore
    public void calculateStandardDeviationInputValid() {
        List<Cell<Integer, String>> listOfCells = newArrayList(
                makeCell(3, "A", 17.0f), makeCell(4, "A", 15.0f),
                makeCell(5, "A", 23.0f), makeCell(6, "A", 7.0f),
                makeCell(7, "A", 9.0f), makeCell(8, "A", 13.0f));
        DocumentiStat documentiStat = makeDocumentiStat(
                newArrayList(makeDataset("dataset1", listOfCells)));
        Float received = calcStatisical
            .calculateStandardDeviation(documentiStat);
        Float expected = new Float("5.76");
        assertEquals(expected, received);
    }
}
