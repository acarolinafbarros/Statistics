package org.iStat.api.iController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/iStatCalc")
public class IStatCalc {
	
	private final Logger LOG = LoggerFactory.getLogger(IStatCalc.class);
	
	@RequestMapping(value="/calculateMedian",produces=MediaType.APPLICATION_JSON_VALUE, method= RequestMethod.GET)
	public String calculateMedian(){
		LOG.info("logger info test");
		return "teste";
	}

}
