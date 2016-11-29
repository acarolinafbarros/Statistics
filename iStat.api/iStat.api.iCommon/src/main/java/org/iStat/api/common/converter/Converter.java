package org.iStat.api.common.converter;

@FunctionalInterface
public interface Converter<From, To> {

    public To convert(From from);

}
