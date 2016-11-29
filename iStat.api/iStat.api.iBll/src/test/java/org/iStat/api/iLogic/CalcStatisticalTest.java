package org.iStat.api.iLogic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class CalcStatisticalTest {
    
    private static CalcStatistical calcStatisical = new CalcStatistical();
    
    /* ----------------------------------------------------------------------
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
        Float received = calcStatisical.calculateMedian(new ArrayList<>());
        assertNull(received);
    }
    
    @Test
    public void calculateMedianInputValid() {
        List<Float> input = new ArrayList<>();
        input.add(new Float("1.1"));
        input.add(new Float("1.3"));
        Float received = calcStatisical.calculateMedian(input);
        Float expected = new Float("1.2");
        assertEquals(expected, received);
    }
    
    @Test
    public void calculateMedianInputWithNegatives() {
        List<Float> input = new ArrayList<>();
        input.add(new Float("-1.0"));
        input.add(new Float("1.0"));
        Float received = calcStatisical.calculateMedian(input);
        Float expected = new Float("0");
        assertEquals(expected, received);
    }
    
    /* ----------------------------------------------------------------------
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
        Float received = calcStatisical.calculateGeometricMean(new ArrayList<>());
        assertNull(received);
    }
    
    @Test
    public void calculateGeometricMeanInputValid() {
        List<Float> input = new ArrayList<>();
        input.add(new Float("4.0"));
        input.add(new Float("2.0"));
        input.add(new Float("1.0"));
        Float received = calcStatisical.calculateGeometricMean(input);
        Float expected = new Float("2.0");
        assertEquals(expected, received);
    }
    
    /* ----------------------------------------------------------------------
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
        Float received = calcStatisical.calculateMode(new ArrayList<>());
        assertNull(received);
    }
    
    @Test
    public void calculateModeInputValid() {
        List<Float> input = new ArrayList<>();
        input.add(new Float("1.0"));
        input.add(new Float("4.0"));
        input.add(new Float("4.0"));
        input.add(new Float("4.0"));
        input.add(new Float("1.0"));
        input.add(new Float("6.0"));
        Float received = calcStatisical.calculateMode(input);
        Float expected = new Float("4.0");
        assertEquals(expected, received);
    }
    
    /* ----------------------------------------------------------------------
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
        Float received = calcStatisical.calculateMidrange(new ArrayList<>());
        assertNull(received);
    }
    
    @Test
    public void calculateMidrangeInputValid() {
        List<Float> input = new ArrayList<>();
        input.add(new Float("3.0"));
        input.add(new Float("4.0"));
        input.add(new Float("10.0"));
        input.add(new Float("11.0"));
        Float received = calcStatisical.calculateMidrange(input);
        Float expected = new Float("7.0");
        assertEquals(expected, received);
    }
    
    /* ----------------------------------------------------------------------
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
        Float received = calcStatisical.calculateVariance(new ArrayList<>());
        assertNull(received);
    }
    
    @Test
    public void calculateVarianceInputValid() {
        List<Float> input = new ArrayList<>();
        input.add(new Float("17.0"));
        input.add(new Float("15.0"));
        input.add(new Float("23.0"));
        input.add(new Float("7.0"));
        input.add(new Float("9.0"));
        input.add(new Float("13.0"));
        Float received = calcStatisical.calculateVariance(input);
        Float expected = new Float("33.2");
        assertEquals(expected, received);
    }
    
    /* ----------------------------------------------------------------------
     * EN - StandardDeviation
     * ----------------------------------------------------------------------
    */
    
    @Test
    public void calculateStandardDeviationInputNull() {
        Float received = calcStatisical.calculateStandardDeviation(null);
        assertNull(received);
    }
    
    @Test
    public void calculateStandardDeviationInputEmpty() {
        Float received = calcStatisical.calculateStandardDeviation(new ArrayList<>());
        assertNull(received);
    }
    
    @Test
    public void calculateStandardDeviationInputValid() {
        List<Float> input = new ArrayList<>();
        input.add(new Float("17.0"));
        input.add(new Float("15.0"));
        input.add(new Float("23.0"));
        input.add(new Float("7.0"));
        input.add(new Float("9.0"));
        input.add(new Float("13.0"));
        Float received = calcStatisical.calculateStandardDeviation(input);
        Float expected = new Float("5.76");
        assertEquals(expected, received);
    }
}
