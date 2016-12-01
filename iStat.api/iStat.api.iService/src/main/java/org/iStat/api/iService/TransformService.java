package org.iStat.api.iService;

import java.util.List;

import org.iStat.api.iLogic.TransformStatistical;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TransformService {

	private final Logger LOG = LoggerFactory.getLogger(TransformService.class);

    public Float transformTranspose(List<Float> input) {
    	TransformStatistical transformStatistical = new TransformStatistical();
        return transformStatistical.transformTranspose(input);
    }
	
}
