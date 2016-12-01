package org.iStat.api.iLogic;

import java.util.Comparator;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.iStat.api.iEntity.Dataset.DatasetBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TransformStatistical {

	private final Logger LOG = LoggerFactory.getLogger(TransformStatistical.class);

    public Float transformTranspose(List<Float> input) {
        Float result = null;
        if (!CollectionUtils.isEmpty(input)) {
            Integer length = input.size();
            if (length > 0) {
                Float sum = (float) input.stream().mapToDouble(i -> i.floatValue()).sum();
                result = sum / length;
            }
        }
        LOG.info("teste");
        return result;
    }

}
