package org.iStat.api.iController;

import org.iStat.api.common.converter.Converter;
import org.iStat.api.iEntity.DocumentiStat;
import org.iStat.api.iService.CalcService;
import org.iStat.api.iStatCalc.request.RequestiStatCalc;
import org.iStat.api.iStatCalc.response.ResponseiStatCalc;
import org.iStat.api.response.StatusEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/iStatCalc")
public class IStatCalc {

    private final Logger LOG = LoggerFactory
        .getLogger(IStatCalc.class);

    @Autowired
    private CalcService calcService;

    @Autowired
    private Converter<RequestiStatCalc, DocumentiStat> converterRequestiStatCalc;

    @Autowired
    private Converter<Float, ResponseiStatCalc> converterResponseiStatCalc;

    /**
     * WebService responsible for calculate the median of list of floats.
     * 
     * URL example:
     * http://localhost:8080/iStatCalc/calculateMedian?valuesToCalc=1;3.0
     * 
     * @param valuesToCalc
     *            - List of floats separated with ;
     * @return JSON of status and result
     */
    @RequestMapping(value = "/calculateMedian", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public @ResponseBody ResponseiStatCalc calculateMedian(@RequestBody RequestiStatCalc request) {

        DocumentiStat documentiStat = converterRequestiStatCalc
            .convert(request);

        // FIXME: Need to catch the errors and throw an exception
        Float result = calcService.calculateMedian(documentiStat);

        ResponseiStatCalc response = converterResponseiStatCalc
            .convert(result);
        response.setStatus(StatusEnum.Success);

        return response;

    }

    /**
     * WebService responsible for calculate the geometric mean of list of
     * floats.
     * 
     * URL example:
     * http://localhost:8080/iStatCalc/calculateGeometricMean?valuesToCalc=4;2;1.0
     * 
     * @param valuesToCalc
     *            - List of floats separated with ;
     * @return JSON of status and result
     */
    @RequestMapping(value = "/calculateGeometricMean", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public @ResponseBody ResponseiStatCalc calculateGeometricMean(@RequestBody RequestiStatCalc request) {

        DocumentiStat documentiStat = converterRequestiStatCalc
            .convert(request);

        // FIXME: Need to catch the errors and throw an exception
        Float result = calcService
            .calculateGeometricMean(documentiStat);

        ResponseiStatCalc response = converterResponseiStatCalc
            .convert(result);
        response.setStatus(StatusEnum.Success);

        return response;
    }

    /**
     * WebService responsible for calculate the mode of list of floats.
     * 
     * URL example:
     * http://localhost:8080/iStatCalc/calculateMode?valuesToCalc=3;4;11;11;11;10
     * 
     * @param valuesToCalc
     *            - List of floats separated with ;
     * @return JSON of status and result
     */
    @RequestMapping(value = "/calculateMode", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public @ResponseBody ResponseiStatCalc calculateMode(@RequestBody RequestiStatCalc request) {

        DocumentiStat documentiStat = converterRequestiStatCalc
            .convert(request);

        // FIXME: Need to catch the errors and throw an exception
        Float result = calcService.calculateMode(documentiStat);

        ResponseiStatCalc response = converterResponseiStatCalc
            .convert(result);
        response.setStatus(StatusEnum.Success);

        return response;
    }

    /**
     * WebService responsible for calculate the midrange of list of floats.
     * 
     * URL example:
     * http://localhost:8080/iStatCalc/calculateMidrange?valuesToCalc=3;4;11;10
     * 
     * @param valuesToCalc
     *            - List of floats separated with ;
     * @return JSON of status and result
     */
    @RequestMapping(value = "/calculateMidrange", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public @ResponseBody ResponseiStatCalc calculateMidrange(@RequestBody RequestiStatCalc request) {

        DocumentiStat documentiStat = converterRequestiStatCalc
            .convert(request);

        // FIXME: Need to catch the errors and throw an exception
        Float result = calcService.calculateMidrange(documentiStat);
        ResponseiStatCalc response = converterResponseiStatCalc
            .convert(result);
        response.setStatus(StatusEnum.Success);

        return response;
    }

    /**
     * WebService responsible for calculate the variance of list of floats.
     * 
     * URL example:
     * http://localhost:8080/iStatCalc/calculateVariance?valuesToCalc=17;15;23;7;9;13
     * 
     * @param valuesToCalc
     *            - List of floats separated with ;
     * @return JSON of status and result
     */
    @RequestMapping(value = "/calculateVariance", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public @ResponseBody ResponseiStatCalc calculateVariance(@RequestBody RequestiStatCalc request) {

        DocumentiStat documentiStat = converterRequestiStatCalc
            .convert(request);

        // FIXME: Need to catch the errors and throw an exception
        Float result = calcService.calculateVariance(documentiStat);
        ResponseiStatCalc response = converterResponseiStatCalc
            .convert(result);
        response.setStatus(StatusEnum.Success);

        return response;
    }

    /**
     * WebService responsible for calculate the standard deviation of list of
     * floats.
     * 
     * URL example:
     * http://localhost:8080/iStatCalc/calculateStandardDeviation?valuesToCalc=17;15;23;7;9;13
     * 
     * @param valuesToCalc
     *            - List of floats separated with ;
     * @return JSON of status and result
     */
    @RequestMapping(value = "/calculateStandardDeviation", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public @ResponseBody ResponseiStatCalc calculateStandardDeviation(@RequestBody RequestiStatCalc request) {
        DocumentiStat documentiStat = converterRequestiStatCalc
            .convert(request);

        // FIXME: Need to catch the errors and throw an exception
        Float result = calcService
            .calculateStandardDeviation(documentiStat);
        ResponseiStatCalc response = converterResponseiStatCalc
            .convert(result);
        response.setStatus(StatusEnum.Success);

        return response;
    }

    /**
     * WebService responsible for calculate row's and column's total .
     * 
     * URL example:
     * http://localhost:8080/iStatCalc/calculateRowColumnTotal?valuesToCalc=2;10;5;1.0;4.0
     * 
     * @param valuesToCalc
     *            - List of floats separated with ;
     * @return JSON of status and result
     */
    @RequestMapping(value = "/calculateRowColumnTotal", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public @ResponseBody ResponseiStatCalc calculateRowColumnTotal(@RequestBody RequestiStatCalc request) {

        DocumentiStat documentiStat = converterRequestiStatCalc
            .convert(request);

        // FIXME: Need to catch the errors and throw an exception
        Float result = calcService
            .calculateRowColumnTotal(documentiStat);

        ResponseiStatCalc response = converterResponseiStatCalc
            .convert(result);
        response.setStatus(StatusEnum.Success);

        return response;
    }

}
