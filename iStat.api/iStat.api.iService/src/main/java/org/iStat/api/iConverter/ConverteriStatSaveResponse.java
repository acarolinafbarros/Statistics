package org.iStat.api.iConverter;

import org.iStat.api.common.converter.Converter;
import org.iStat.api.iStatIO.response.ResponseiStatSave;

public class ConverteriStatSaveResponse
        implements Converter<Boolean, ResponseiStatSave> {

    @Override
    public ResponseiStatSave convert(Boolean from) {
        return new ResponseiStatSave(from);
    }

}
