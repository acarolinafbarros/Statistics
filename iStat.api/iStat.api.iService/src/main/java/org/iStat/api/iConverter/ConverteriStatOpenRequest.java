package org.iStat.api.iConverter;

import org.apache.commons.lang3.ObjectUtils;
import org.iStat.api.iCommon.converter.Converter;
import org.iStat.api.iCommon.converter.exception.ConvertException;
import org.iStat.api.iDomain.DocumentiStat;
import org.iStat.api.iDomain.DocumentiStat.DocumentiStatBuilder;
import org.iStat.api.iStatDataset.request.RequestiStatOpen;

public class ConverteriStatOpenRequest implements Converter<RequestiStatOpen, DocumentiStat> {

	@Override
	public DocumentiStat convert(RequestiStatOpen from) throws ConvertException {

		if (!ObjectUtils.allNotNull(from, from.getName())) {
			throw new ConvertException("'operation=convert', 'from=" + from + "'");
		}
		try {
			return new DocumentiStatBuilder().withId(from.getName()).build();
		} catch (NullPointerException ex) {
			throw new ConvertException("'operation=convert', 'from=" + from + "'", ex);
		}
	}

}
