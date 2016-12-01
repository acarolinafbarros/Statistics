package org.iStat.api.iController;

import java.math.BigDecimal;
import java.util.List;

import org.iStat.api.iResponse.TransformResponse;
import org.iStat.api.iResponse.StatusEnum;
import org.iStat.api.iService.TransformService;
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
@RequestMapping(value = "/iStatTransform")
public class IStatTransform {

	private final Logger LOG = LoggerFactory.getLogger(IStatTransform.class);

    @Autowired
    private TransformService transformService;

    /**
     * WebService responsible for transpose the dataset.
     * 
     * URL example:
     * http://localhost:8080/iStatTransform/transformTranspose?valuesToCalc=1;3.0
     * 
     * @param valuesToCalc
     *            - List of floats separated with ;
     * @return JSON of status and result
     */
    
	@RequestMapping(value = "/transformTranspose", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public @ResponseBody TransformResponse transformTranspose(@RequestParam(value = "valuesToCalc") String valuesToCalc) {
		TransformResponse transformResponse = null;
        if (!ObjectUtils.isEmpty(valuesToCalc)) {
            List<Float> input = IConvertUtil.convertStringIntoListFloat(valuesToCalc);
            Float result = transformService.transformTranspose(input);
            transformResponse = new TransformResponse(BigDecimal.valueOf(result));
        }
        return transformResponse;
    }
	
}
