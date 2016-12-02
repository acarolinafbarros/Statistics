package org.iStat.api.iConverter;

import org.iStat.api.common.converter.Converter;
import org.iStat.api.iResponse.iStatCalc.ResponseiStatCalc;

public class ConverterDomainToResponseiStatCal
        implements Converter<Float, ResponseiStatCalc> {

    @Override
    public ResponseiStatCalc convert(Float from) {
        ResponseiStatCalc response = new ResponseiStatCalc();
        response.setResult(from);

        return response;
    }

}
