package org.iStat.api.iController;

import org.iStat.api.common.converter.Converter;
import org.iStat.api.iEntity.DocumentiStat;
import org.iStat.api.iService.TransformService;
import org.iStat.api.iStatTransform.request.RequestiStatTransform;
import org.iStat.api.iStatTransform.response.ResponseiStatTransform;
import org.iStat.api.response.StatusEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/iStatTransform")
public class IStatTransform {

    private final Logger LOG = LoggerFactory
        .getLogger(IStatTransform.class);

    @Autowired
    private TransformService transformService;

    @Autowired
    private Converter<RequestiStatTransform, DocumentiStat> converterRequestiStatTransform;

    @Autowired
    private Converter<DocumentiStat, ResponseiStatTransform> converterResponseiStatTransform;

    /**
     * WebService responsible for scale the dataset.
     * 
     * URL example:
     * http://localhost:8080/iStatTransform/transformScale
     * 
     * @param valuesToCalc
     *            - List of floats separated with ;
     * @return JSON of status and result
     */
    @RequestMapping(value = "/transformScale", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public @ResponseBody ResponseiStatTransform transformScale(@RequestBody RequestiStatTransform request, @RequestParam(required = false) Integer finalLine, @RequestParam(required = false) String finalColumn) {

        LOG.info("Request: {}", request);
        LOG.info("The final line is {} and the column is {}",
                finalLine, finalColumn);

        DocumentiStat documentiStat = converterRequestiStatTransform
            .convert(request);
        
        Float scalar = request.getScalar();
      
        // FIXME: Need to catch the errors and throw an exception
        DocumentiStat result = transformService.transformScale(documentiStat,scalar);

        ResponseiStatTransform response = converterResponseiStatTransform
            .convert(result);

        response.setStatus(StatusEnum.Success);

        return response;

    }
}


