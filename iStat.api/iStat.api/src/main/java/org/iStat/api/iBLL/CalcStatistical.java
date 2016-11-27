package org.iStat.api.iBLL;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

public class CalcStatistical {

    private final Logger LOG = LoggerFactory.getLogger(CalcStatistical.class);

    public Float calculateMedian(List<Float> input) {
        Float result = null;
        if (!ObjectUtils.isEmpty(input)) {
            Integer length = input.size();
            if (length > 0) {
               Float sum=(float) input.stream().mapToDouble(i -> i.floatValue()).sum();
               result = sum/length;
            }
        }

        return result;
    }

}
