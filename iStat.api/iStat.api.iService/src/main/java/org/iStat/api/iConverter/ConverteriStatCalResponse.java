package org.iStat.api.iConverter;

import java.util.Objects;

import org.apache.commons.lang3.ObjectUtils;
import org.iStat.api.iCommon.converter.Converter;
import org.iStat.api.iCommon.converter.exception.ConvertException;
import org.iStat.api.iStatCalc.response.ResponseiStatCalc;

public class ConverteriStatCalResponse implements Converter<Float, ResponseiStatCalc> {

	@Override
	public ResponseiStatCalc convert(Float from) throws ConvertException {

		if (!ObjectUtils.allNotNull(from)) {
			throw new ConvertException("'operation=convert', 'from=" + from + "'");
		}

		try {
			ResponseiStatCalc response = new ResponseiStatCalc();
			response.setResult(from);
			return response;
		} catch (NullPointerException ex) {
			throw new ConvertException("'operation=convert', 'from=" + from + "'", ex);
		}

	}

}
