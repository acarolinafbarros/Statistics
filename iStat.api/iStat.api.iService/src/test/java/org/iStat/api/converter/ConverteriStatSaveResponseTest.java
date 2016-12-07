package org.iStat.api.converter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.iStat.api.common.converter.Converter;
import org.iStat.api.iConverter.ConverteriStatSaveResponse;
import org.iStat.api.iStatIO.response.ResponseiStatSave;
import org.junit.Test;

public class ConverteriStatSaveResponseTest {

    public Converter<Boolean, ResponseiStatSave> converter = new ConverteriStatSaveResponse();

    @Test
    public void shouldConverteriStatSaveResponse() {

        ResponseiStatSave response = converter.convert(true);

        assertNotNull(response);
        assertEquals(true, response.getName());

    }

}
