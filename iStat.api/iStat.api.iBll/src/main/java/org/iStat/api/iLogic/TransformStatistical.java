package org.iStat.api.iLogic;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.iStat.api.iEntity.Cell;
import org.iStat.api.iEntity.DocumentiStat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TransformStatistical {

	private final Logger LOG = LoggerFactory.getLogger(TransformStatistical.class);

	private Function<Cell<Integer, String>, Float> EXTRACT_VALUE_OF_CELL = new Function<Cell<Integer, String>, Float>() {

        @Override
        public Float apply(Cell<Integer, String> cell) {
            return cell.getValue() == null ? 0.0f : cell.getValue();
        }
    };

    public Float transformScale(DocumentiStat documentiStat) {
        Float result = null;

        if (ObjectUtils.allNotNull(documentiStat)) {
            if (CollectionUtils.isNotEmpty(documentiStat.getDatasets())) {

                List<Cell<Integer, String>> input = documentiStat.getDatasets().get(0).getCells();

                if (!CollectionUtils.isEmpty(input)) {
                    Integer length = input.size();
                    if (length > 0) {
                        Float sum = (float) input.stream().mapToDouble(i -> i.getValue()).sum();
                        result = sum / length;
                    }
                }
            }
        }

        return result;
    }

	/*
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
    */

}
