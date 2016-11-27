package org.iStat.api.iController;

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
            calcResponse = new CalcResponse(StatusEnum.Success, result);
        }
        return calcResponse;
    }

}
