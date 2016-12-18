package org.iStat.api.iCommon.converter;

import org.iStat.api.iCommon.converter.exception.ConvertException;

@FunctionalInterface
public interface Converter<From, To> {

    public To convert(From from) throws ConvertException;

}
