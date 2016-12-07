package org.iStat.api.iConverter;

import org.iStat.api.common.converter.Converter;
import org.iStat.api.iEntity.DocumentiStat;
import org.iStat.api.iEntity.DocumentiStat.DocumentiStatBuilder;
import org.iStat.api.iStatIO.request.RequestiStatOpen;

public class ConverteriStatOpenRequest
        implements Converter<RequestiStatOpen, DocumentiStat> {

    @Override
    public DocumentiStat convert(RequestiStatOpen from) {
        return new DocumentiStatBuilder()
            .withId(from.getName()).build();
    }

}
