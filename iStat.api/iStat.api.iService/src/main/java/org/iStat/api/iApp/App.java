package org.iStat.api.iApp;

import org.iStat.api.common.converter.Converter;
import org.iStat.api.iConverter.ConverteriStatCalResponse;
import org.iStat.api.iConverter.ConverteriStatTransformRequest;
import org.iStat.api.iConverter.ConverteriStatTransformResponse;
import org.iStat.api.iConverter.ConverteriStatCalRequest;
import org.iStat.api.iEntity.DocumentiStat;
import org.iStat.api.iStatCalc.request.RequestiStatCalc;
import org.iStat.api.iStatCalc.response.ResponseiStatCalc;
import org.iStat.api.iStatTransform.request.RequestiStatTransform;
import org.iStat.api.iStatTransform.response.ResponseiStatTransform;
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
        return new ConverteriStatCalRequest();
    }

    @Bean
    public Converter<Float, ResponseiStatCalc> converterResponseiStatCalc() {
        return new ConverteriStatCalResponse();
    }

    @Bean
    public Converter<RequestiStatTransform, DocumentiStat> converterRequestiStatTransform() {
        return new ConverteriStatTransformRequest();
    }

    @Bean
    public Converter<DocumentiStat, ResponseiStatTransform> converterResponseiStatTransform() {
        return new ConverteriStatTransformResponse();
    }

}
