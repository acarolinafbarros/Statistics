package org.iStat.api.iConverter;

import static org.junit.Assert.assertNotNull;

import org.iStat.api.iCommon.converter.Converter;
import org.iStat.api.iConverter.ConverteriStatOpenResponse;
import org.iStat.api.iDomain.DocumentiStat;
import org.iStat.api.iStatDataset.response.ResponseiStatOpen;
import org.junit.Test;

public class ConverteriStatOpenResponseTest
        extends AbstractUtilsiServiceTest {

    public Converter<DocumentiStat, ResponseiStatOpen> converter = new ConverteriStatOpenResponse();

    @Test
    public void shouldConverteriStatOpenResponse() throws Exception{

        String defaultValue = "default";

        DocumentiStat documentiStat = makeDocumentiStat(defaultValue);

        ResponseiStatOpen response = converter.convert(documentiStat);

        assertNotNull(response);

    }
}
