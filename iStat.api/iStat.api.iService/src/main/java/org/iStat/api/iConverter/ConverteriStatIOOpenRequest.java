package org.iStat.api.iConverter;

import org.iStat.api.common.converter.Converter;
import org.iStat.api.iEntity.MongoFile;
import org.iStat.api.iEntity.MongoFile.MongoFileBuilder;
import org.iStat.api.iStatIO.request.RequestiStatIOOpen;

public class ConverteriStatIOOpenRequest
        implements Converter<RequestiStatIOOpen, MongoFile> {

    @Override
    public MongoFile convert(RequestiStatIOOpen from) {
        return new MongoFileBuilder()
            .withName(from.getName()).build();
    }

}
