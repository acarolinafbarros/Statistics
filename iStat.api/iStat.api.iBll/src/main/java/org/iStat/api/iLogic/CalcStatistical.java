package org.iStat.api.iLogic;

import java.util.Comparator;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CalcStatistical {

    private final Logger LOG = LoggerFactory.getLogger(CalcStatistical.class);

    public Float calculateMedian(List<Float> input) {
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

    public Float calculateGeometricMean(List<Float> input) {
        Float result = null;
        if (!CollectionUtils.isEmpty(input)) {
            Integer length = input.size();
            if (length > 0) {
                Float mult = (float) input.stream().mapToDouble(i -> i.floatValue()).reduce(1, (a, b) -> a * b);
                double geoMean = Math.pow(mult, 1.0 / length);
                result = (float) geoMean;
            }
        }

        return result;
    }

    public Float calculateMode(List<Float> input) {
        Float result = null;
        if (!CollectionUtils.isEmpty(input)) {
            Integer length = input.size();
            if (length > 0) {
                float keyValue = 0;
                float maxCounts = 0;
                float[] counts = new float[length];

                for (int i = 0; i < length; ++i) {
                    float value = input.get(i);
                    counts[(int) value]++;

                    if (maxCounts < counts[(int) value]) {
                        maxCounts = counts[(int) value];
                        keyValue = input.get(i);
                    }
                }
                result = keyValue;
            }
        }
        return result;
    }

    public Float calculateMidrange(List<Float> input) {
        Float result = null;
        if (!CollectionUtils.isEmpty(input)) {
            Integer length = input.size();
            if (length > 0) {
                Float max = (float) input.stream().max(Comparator.comparing(i -> i)).get();
                Float min = (float) input.stream().min(Comparator.comparing(i -> i)).get();

                result = (max + min) / 2;
            }
        }

        return result;
    }

    public Float calculateVariance(List<Float> input) {
        Float result = null;
        if (!CollectionUtils.isEmpty(input)) {
            Integer length = input.size();
            if (length > 0) {
                float median = calculateMedian(input);
                float temp = 0;
                for (float value : input) {
                    temp += (value - median) * (value - median);
                }
                result = temp / ((float) length - 1);
            }
        }
        return result;
    }

    public Float calculateStandardDeviation(List<Float> input) {
        Float result = null;
        if (!CollectionUtils.isEmpty(input)) {
            Integer length = input.size();
            if (length > 0) {
                float variance = calculateVariance(input);
                result = (float) Math.sqrt(variance);
            }
        }
        return result;
    }

}
