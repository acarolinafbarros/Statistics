package org.iStat.api.iController;

import org.iStat.api.iService.TransformService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
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
     * http://localhost:8080/iStatTransform/transformScale?valuesToCalc=1;3.0
     * 
     * @param valuesToCalc
     *            - List of floats separated with ;
     * @return JSON of status and result
     */
    
    /*
	@RequestMapping(value = "/transformScale", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public @ResponseBody TransformResponse transformScale(@RequestParam(value = "valuesToCalc") String valuesToCalc) {
		TransformResponse transformResponse = null;
        if (!ObjectUtils.isEmpty(valuesToCalc)) {
            List<Float> input = IConvertUtil.convertStringIntoListFloat(valuesToCalc);
            Float value = IConvertUtil.convertStringIntoFloat(valuesToCalc);
            Float result = transformService.transformScale(input, value);
            transformResponse = new TransformResponse(BigDecimal.valueOf(result));
        }
        return transformResponse;
    }
	*/
}
