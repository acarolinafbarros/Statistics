package org.iStat.api.iConverter;

import java.util.Objects;

import org.iStat.api.iCommon.converter.Converter;
import org.iStat.api.iDomain.DocumentiStat;
import org.iStat.api.iDomain.DocumentiStat.DocumentiStatBuilder;
import org.iStat.api.iStatDataset.request.RequestiStatOpen;

public class ConverteriStatOpenRequest
        implements Converter<RequestiStatOpen, DocumentiStat> {

    @Override
    public DocumentiStat convert(RequestiStatOpen from) {
    	
		Objects.requireNonNull(from, "from must be not null!");
		Objects.requireNonNull(from.getName(), "getName must be not null!");
    	
        return new DocumentiStatBuilder()
            .withId(from.getName()).build();
    }

}
