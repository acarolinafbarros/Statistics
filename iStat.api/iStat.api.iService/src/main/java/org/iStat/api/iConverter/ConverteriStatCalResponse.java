package org.iStat.api.iConverter;

import java.util.Objects;

import org.iStat.api.iCommon.converter.Converter;
import org.iStat.api.iStatCalc.response.ResponseiStatCalc;

public class ConverteriStatCalResponse
        implements Converter<Float, ResponseiStatCalc> {

    @Override
    public ResponseiStatCalc convert(Float from) {
    	
		Objects.requireNonNull(from, "from must be not null!");
    	
        ResponseiStatCalc response = new ResponseiStatCalc();
        response.setResult(from);

        return response;
    }

}
