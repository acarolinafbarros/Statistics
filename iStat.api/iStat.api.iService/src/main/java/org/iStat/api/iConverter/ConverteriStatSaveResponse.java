package org.iStat.api.iConverter;

import java.util.Objects;

import org.iStat.api.iCommon.converter.Converter;
import org.iStat.api.iStatDataset.response.ResponseiStatSave;

public class ConverteriStatSaveResponse implements Converter<Boolean, ResponseiStatSave> {

	@Override
	public ResponseiStatSave convert(Boolean from) {

		Objects.requireNonNull(from, "from must be not null!");
		
		if (Objects.nonNull(from)) {
			return new ResponseiStatSave(from);
		}

		return new ResponseiStatSave();
	}

}
