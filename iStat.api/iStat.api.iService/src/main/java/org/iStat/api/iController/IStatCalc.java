package org.iStat.api.iController;

import java.math.BigDecimal;
import java.util.List;

import org.iStat.api.iResponse.CalcResponse;
import org.iStat.api.iResponse.StatusEnum;
import org.iStat.api.iService.CalcService;
import org.iStat.api.iUtil.IConvertUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/iStatCalc")
public class IStatCalc {

    private final Logger LOG = LoggerFactory.getLogger(IStatCalc.class);

    @Autowired
    private CalcService calcService;

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
    @RequestMapping(value = "/calculateMedian", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public @ResponseBody CalcResponse calculateMedian(@RequestParam(value = "valuesToCalc") String valuesToCalc) {
        CalcResponse calcResponse = null;
        if (!ObjectUtils.isEmpty(valuesToCalc)) {
            List<Float> input = IConvertUtil.convertStringIntoListFloat(valuesToCalc);
            Float result = calcService.calculateMedian(input);
            calcResponse = new CalcResponse(BigDecimal.valueOf(result));
        }
        return calcResponse;
    }
    
    /**
     * WebService responsible for calculate the geometric mean of list of floats.
     * 
     * URL example:
     * http://localhost:8080/iStatCalc/calculateGeometricMean?valuesToCalc=4;2;1.0
     * 
     * @param valuesToCalc
     *            - List of floats separated with ;
     * @return JSON of status and result
     */
    @RequestMapping(value = "/calculateGeometricMean", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public @ResponseBody CalcResponse calculateGeometricMean(@RequestParam(value = "valuesToCalc") String valuesToCalc) {
        CalcResponse calcResponse = null;
        if (!ObjectUtils.isEmpty(valuesToCalc)) {
            List<Float> input = IConvertUtil.convertStringIntoListFloat(valuesToCalc);
            Float result = calcService.calculateGeometricMean(input);
            calcResponse = new CalcResponse(BigDecimal.valueOf(result));
        }
        return calcResponse;
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
    @RequestMapping(value = "/calculateMode", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public @ResponseBody CalcResponse calculateMode(@RequestParam(value = "valuesToCalc") String valuesToCalc) {
        CalcResponse calcResponse = null;
        if (!ObjectUtils.isEmpty(valuesToCalc)) {
            List<Float> input = IConvertUtil.convertStringIntoListFloat(valuesToCalc);
            Float result = calcService.calculateMode(input);
            calcResponse = new CalcResponse(BigDecimal.valueOf(result));
        }
        return calcResponse;
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
    @RequestMapping(value = "/calculateMidrange", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public @ResponseBody CalcResponse calculateMidrange(@RequestParam(value = "valuesToCalc") String valuesToCalc) {
        CalcResponse calcResponse = null;
        if (!ObjectUtils.isEmpty(valuesToCalc)) {
            List<Float> input = IConvertUtil.convertStringIntoListFloat(valuesToCalc);
            Float result = calcService.calculateMidrange(input);
            calcResponse = new CalcResponse(BigDecimal.valueOf(result));
        }
        return calcResponse;
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
    @RequestMapping(value = "/calculateVariance", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public @ResponseBody CalcResponse calculateVariance(@RequestParam(value = "valuesToCalc") String valuesToCalc) {
        CalcResponse calcResponse = null;
        if (!ObjectUtils.isEmpty(valuesToCalc)) {
            List<Float> input = IConvertUtil.convertStringIntoListFloat(valuesToCalc);
            Float result = calcService.calculateVariance(input);
            calcResponse = new CalcResponse(BigDecimal.valueOf(result));
        }
        return calcResponse;
    }
    
    /**
     * WebService responsible for calculate the standard deviation of list of floats.
     * 
     * URL example:
     * http://localhost:8080/iStatCalc/calculateStandardDeviation?valuesToCalc=17;15;23;7;9;13
     * 
     * @param valuesToCalc
     *            - List of floats separated with ;
     * @return JSON of status and result
     */
    @RequestMapping(value = "/calculateStandardDeviation", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public @ResponseBody CalcResponse calculateStandardDeviation(@RequestParam(value = "valuesToCalc") String valuesToCalc) {
        CalcResponse calcResponse = null;
        if (!ObjectUtils.isEmpty(valuesToCalc)) {
            List<Float> input = IConvertUtil.convertStringIntoListFloat(valuesToCalc);
            Float result = calcService.calculateStandardDeviation(input);
            calcResponse = new CalcResponse(BigDecimal.valueOf(result));
        }
        return calcResponse;
    }

}
