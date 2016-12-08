package org.iStat.api.iApp;

import org.iStat.api.common.converter.Converter;
import org.iStat.api.iConverter.ConverteriStatCalRequest;
import org.iStat.api.iConverter.ConverteriStatCalResponse;
import org.iStat.api.iConverter.ConverteriStatDocumentEntity;
import org.iStat.api.iConverter.ConverteriStatDocumentModel;
import org.iStat.api.iConverter.ConverteriStatOpenRequest;
import org.iStat.api.iConverter.ConverteriStatOpenResponse;
import org.iStat.api.iConverter.ConverteriStatSaveRequest;
import org.iStat.api.iConverter.ConverteriStatSaveResponse;
import org.iStat.api.iConverter.ConverteriStatTransformRequest;
import org.iStat.api.iConverter.ConverteriStatTransformResponse;
import org.iStat.api.iEntity.DocumentiStat;
import org.iStat.api.iModel.DocumentIStatModel;
import org.iStat.api.iStatCalc.request.RequestiStatCalc;
import org.iStat.api.iStatCalc.response.ResponseiStatCalc;
import org.iStat.api.iStatIO.request.RequestiStatOpen;
import org.iStat.api.iStatIO.request.RequestiStatSave;
import org.iStat.api.iStatIO.response.ResponseiStatOpen;
import org.iStat.api.iStatIO.response.ResponseiStatSave;
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

    @Bean
    public Converter<RequestiStatSave, DocumentiStat> converterRequestiStatSave() {
        return new ConverteriStatSaveRequest();
    }

    @Bean
    public Converter<Boolean, ResponseiStatSave> converterResponseiStatSave() {
        return new ConverteriStatSaveResponse();
    }    
    
    @Bean
    public Converter<DocumentiStat, DocumentIStatModel> converterDocumentiStatModel() {
        return new ConverteriStatDocumentModel();
    }   
    
    @Bean
    public Converter<RequestiStatOpen, DocumentiStat> converterRequestiStatOpen() {
        return new ConverteriStatOpenRequest();
    }

    @Bean
    public Converter<DocumentiStat, ResponseiStatOpen> converterResponseiStatOpen() {
        return new ConverteriStatOpenResponse();
    }    
    
    @Bean
    public Converter<DocumentIStatModel,DocumentiStat> converterDocumentiStatEntity() {
        return new ConverteriStatDocumentEntity();
    }   
    
}
