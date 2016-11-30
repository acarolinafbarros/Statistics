package org.iStat.api.common.converter;

import java.math.BigDecimal;

import org.iStat.api.iResponse.CalcResponse;

public class ConverterCalResponseDataset implements Converter<BigDecimal, CalcResponse> {

    @Override
    public CalcResponse convert(BigDecimal from) {
        throw new UnsupportedOperationException("Not support this operation!");
    }

}
