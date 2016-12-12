package org.iStat.api.iConverter;

import org.iStat.api.iCommon.converter.Converter;
import org.iStat.api.iStatCalc.response.ResponseiStatCalc;

public class ConverteriStatCalResponse
        implements Converter<Float, ResponseiStatCalc> {

    @Override
    public ResponseiStatCalc convert(Float from) {
        ResponseiStatCalc response = new ResponseiStatCalc();
        response.setResult(from);

        return response;
    }

}
