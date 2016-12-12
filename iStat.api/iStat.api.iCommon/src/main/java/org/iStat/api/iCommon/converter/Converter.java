package org.iStat.api.iCommon.converter;

@FunctionalInterface
public interface Converter<From, To> {

    public To convert(From from);

}
