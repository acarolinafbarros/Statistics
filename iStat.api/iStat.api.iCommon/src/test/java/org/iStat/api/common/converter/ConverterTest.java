package org.iStat.api.common.converter;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import org.iStat.api.iCommon.converter.Converter;
import org.iStat.api.iCommon.converter.exception.ConvertException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class ConverterTest {

    @Mock
    private Converter<String, Integer> converter;

    @Before
    public void beforeMethod() {
        initMocks(this);
    }

    @Test
    public void shouldConvertStringToInteger() throws Exception{

        when(converter.convert(anyString())).thenReturn(10);

        Integer result = converter.convert("10");
        Integer expected = Integer.valueOf(10);

        assertEquals(expected, result);

    }
    
    @Test(expected = ConvertException.class)
    public void shouldConvertStringToIntegerAndReciveException() throws Exception{
        when(converter.convert(anyString())).thenThrow(ConvertException.class);
        converter.convert("10");
    }
}
