package org.iStat.api.iUtil;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class IConvertUtilTest {

    @Test
    public void convertStringIntoListFloatInputNull() {
        List<Float> received = IConvertUtil.convertStringIntoListFloat(null);
        List<Float> excepted = null;
        assertThat(received, is(excepted));
    }

    @Test
    public void convertStringIntoListFloatInputRight() {
        List<Float> received = IConvertUtil.convertStringIntoListFloat("1.1;2;3.5");
        List<Float> excepted = new ArrayList<>();
        excepted.add(new Float("1.1"));
        excepted.add(new Float("2"));
        excepted.add(new Float("3.5"));
        assertThat(received, is(excepted));
    }

    @Test
    public void convertStringIntoListFloatInputWrong() {
        try {
            List<Float> received = IConvertUtil.convertStringIntoListFloat("1,1;2;3,5");
            assertTrue(false);
        } catch (Exception e) {
            assertTrue(true);
        }
    }

    @Test
    public void convertStringIntoListFloatInputEmpty() {
        List<Float> received = IConvertUtil.convertStringIntoListFloat("");
        List<Float> excepted = null;
        assertThat(received, is(excepted));
    }

    @Test
    public void convertStringIntoListFloatInputWithoutSemicolon() {
        try {
            List<Float> received = IConvertUtil.convertStringIntoListFloat("1.1,2.3,3.4");
            assertTrue(false);
        } catch (Exception e) {
            assertTrue(true);
        }
    }

}
