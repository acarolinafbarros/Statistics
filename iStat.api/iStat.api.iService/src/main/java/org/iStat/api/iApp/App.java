package org.iStat.api.iApp;

import org.iStat.api.common.converter.Converter;
import org.iStat.api.iConverter.ConverterDomainToResponseiStatCal;
import org.iStat.api.iConverter.ConverterRequestiStatCalToDomain;
import org.iStat.api.iEntity.DocumentiStat;
import org.iStat.api.iResponse.iStatCalc.RequestiStatCalc;
import org.iStat.api.iResponse.iStatCalc.ResponseiStatCalc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = "org.iStat.api")
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    public Converter<RequestiStatCalc, DocumentiStat> converterRequestiStatCalc() {
        return new ConverterRequestiStatCalToDomain();
    }

    @Bean
    public Converter<Float, ResponseiStatCalc> converterResponseiStatCalc() {
        return new ConverterDomainToResponseiStatCal();
    }

}
