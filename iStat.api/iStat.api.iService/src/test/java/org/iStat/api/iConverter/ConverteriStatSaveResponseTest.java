package org.iStat.api.iConverter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.iStat.api.iCommon.converter.Converter;
import org.iStat.api.iConverter.ConverteriStatSaveResponse;
import org.iStat.api.iStatDataset.response.ResponseiStatSave;
import org.junit.Test;

public class ConverteriStatSaveResponseTest {

    public Converter<Boolean, ResponseiStatSave> converter = new ConverteriStatSaveResponse();

    @Test
    public void shouldConverteriStatSaveResponse() throws Exception{

        ResponseiStatSave response = converter.convert(true);

        assertNotNull(response);
        assertEquals(true, response.isSuccess());

    }

}
