package org.iStat.api.iBLL;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class CalcStatisticalTest {
    
    private static CalcStatistical calcStatisical = new CalcStatistical();
    
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

}
