package org.iStat.api.iLogic;

import java.util.Comparator;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.iStat.api.iEntity.Dataset.DatasetBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TransformStatistical {

	private final Logger LOG = LoggerFactory.getLogger(TransformStatistical.class);

    public Float [] transformScale(List<Float> input, float scaleFactor) {
    	Float result [] = null;
        if (!CollectionUtils.isEmpty(input)) {
            Integer length = input.size();
            if (length > 0) {
            	result = new Float [length];
            	for (int i = 0; i < length; ++i) {
            		result[i] = result[i] * scaleFactor;
            	}           
            }
        }
        LOG.info("teste");
        return result;
    }

}
