package org.iStat.api.iConverter;

import org.iStat.api.iCommon.converter.Converter;
import org.iStat.api.iDomain.DocumentiStat;
import org.iStat.api.iDomain.DocumentiStat.DocumentiStatBuilder;
import org.iStat.api.iStatDataset.request.RequestiStatOpen;

public class ConverteriStatOpenRequest
        implements Converter<RequestiStatOpen, DocumentiStat> {

    @Override
    public DocumentiStat convert(RequestiStatOpen from) {
        return new DocumentiStatBuilder()
            .withId(from.getName()).build();
    }

}
