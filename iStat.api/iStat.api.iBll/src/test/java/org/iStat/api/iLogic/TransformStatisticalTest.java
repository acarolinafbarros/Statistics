package org.iStat.api.iLogic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

public class TransformStatisticalTest {
    
    private static TransformStatistical transformStatisical = new TransformStatistical();
    
    /* ----------------------------------------------------------------------
     * EN - 
     * ----------------------------------------------------------------------
    */
    
    @Test
    public void transformTransposeInputNull() {
        Float received = transformStatisical.transformTranspose(null);
        assertNull(received);
    }
    
    @Test
    public void transformTransposeInputEmpty() {
        Float received = transformStatisical.transformTranspose(new ArrayList<>());
        assertNull(received);
    }
    
    /*
    @Test
    public void transformTransposeInputValid() {
        List<Float> input = new ArrayList<>();
        input.add(new Float("1.1"));
        input.add(new Float("1.3"));
        Float received = transformStatisical.transformTranspose(input);
        Float expected = new Float("1.2");
        assertEquals(expected, received);
    }
    
    @Test
    public void transformTransposeInputWithNegatives() {
        List<Float> input = new ArrayList<>();
        input.add(new Float("-1.0"));
        input.add(new Float("1.0"));
        Float received = transformStatisical.transformTranspose(input);
        Float expected = new Float("0");
        assertEquals(expected, received);
    }
    */   
}
