package org.iStat.api.iConverter;

import java.util.Objects;

import org.apache.commons.lang3.ObjectUtils;
import org.iStat.api.iCommon.converter.Converter;
import org.iStat.api.iCommon.converter.exception.ConvertException;
import org.iStat.api.iStatDataset.response.ResponseiStatSave;

public class ConverteriStatSaveResponse implements Converter<Boolean, ResponseiStatSave> {

	@Override
	public ResponseiStatSave convert(Boolean from) throws ConvertException {

		if (!ObjectUtils.allNotNull(from)) {
			throw new ConvertException("'operation=convert', 'from=" + from + "'");
		}

		if (Objects.nonNull(from)) {
			return new ResponseiStatSave(from);
		}

		try {
			return new ResponseiStatSave();
		} catch (NullPointerException ex) {
			throw new ConvertException("'operation=convert', 'from=" + from + "'", ex);
		}
	}

}
