package org.iStat.api.converter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.iStat.api.common.converter.Converter;
import org.iStat.api.iConverter.ConverterDomainToResponseiStatCal;
import org.iStat.api.iResponse.iStatCalc.ResponseiStatCalc;
import org.junit.Test;

public class ConverterDomainToResponseiStatCalTest {

    public Converter<Float, ResponseiStatCalc> converter = new ConverterDomainToResponseiStatCal();

    @Test
    public void shouldConvertDomainToResponseiStatCal() {

        ResponseiStatCalc responseExpected = new ResponseiStatCalc(
                Float.valueOf(10.0f));

        ResponseiStatCalc response = converter
            .convert(Float.valueOf(10.0f));

        assertEquals(responseExpected.getResult(),
                response.getResult());
        assertNull(response.getStatus());

    }

}
