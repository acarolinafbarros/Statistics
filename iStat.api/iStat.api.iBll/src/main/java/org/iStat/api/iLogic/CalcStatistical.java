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

public class CalcStatistical {

    private final Logger LOG = LoggerFactory
        .getLogger(CalcStatistical.class);

    private Function<Cell<Integer, String>, Float> EXTRACT_VALUE_OF_CELL = new Function<Cell<Integer, String>, Float>() {

        @Override
        public Float apply(Cell<Integer, String> cell) {
            return cell.getValue() == null ? 0.0f : cell.getValue();
        }
    };

    public Float calculateMedian(DocumentiStat documentiStat) {
        Float result = null;

        if (ObjectUtils.allNotNull(documentiStat)) {
            if (CollectionUtils
                .isNotEmpty(documentiStat.getDatasets())) {

                List<Cell<Integer, String>> input = documentiStat
                    .getDatasets().get(0).getCells();

                if (!CollectionUtils.isEmpty(input)) {
                    Integer length = input.size();
                    if (length > 0) {
                        Float sum = (float) input
                            .stream().mapToDouble(i -> i.getValue())
                            .sum();
                        result = sum / length;
                    }
                }
            }
        }

        return result;
    }

    public Float calculateGeometricMean(DocumentiStat documentiStat) {
        Float result = null;
        if (ObjectUtils.allNotNull(documentiStat)) {

            if (CollectionUtils
                .isNotEmpty(documentiStat.getDatasets())) {

                List<Cell<Integer, String>> input = documentiStat
                    .getDatasets().get(0).getCells();

                if (!CollectionUtils.isEmpty(input)) {
                    Integer length = input.size();
                    if (length > 0) {
                        Float mult = (float) input
                            .stream().mapToDouble(i -> i.getValue())
                            .reduce(1, (a, b) -> a * b);
                        double geoMean = Math.pow(mult, 1.0 / length);
                        result = (float) geoMean;
                    }
                }
            }
        }

        return result;
    }

    public Float calculateMode(DocumentiStat documentiStat) {
        Float result = null;
        if (ObjectUtils.allNotNull(documentiStat)) {

            if (CollectionUtils
                .isNotEmpty(documentiStat.getDatasets())) {

                List<Cell<Integer, String>> input = documentiStat
                    .getDatasets().get(0).getCells();

                if (!CollectionUtils.isEmpty(input)) {
                    Integer length = input.size();
                    if (length > 0) {
                        float keyValue = 0;
                        float maxCounts = 0;
                        float[] counts = new float[length];

                        for (int i = 0; i < length; ++i) {
                            float value = input.get(i).getValue();
                            counts[(int) value]++;

                            if (maxCounts < counts[(int) value]) {
                                maxCounts = counts[(int) value];
                                keyValue = input.get(i).getValue();
                            }
                        }
                        result = keyValue;
                    }
                }
            }
        }
        return result;
    }

    public Float calculateMidrange(DocumentiStat documentiStat) {
        Float result = null;
        if (ObjectUtils.allNotNull(documentiStat)) {

            if (CollectionUtils
                .isNotEmpty(documentiStat.getDatasets())) {

                List<Cell<Integer, String>> input = documentiStat
                    .getDatasets().get(0).getCells();

                if (!CollectionUtils.isEmpty(input)) {
                    Integer length = input.size();
                    if (length > 0) {
                        Float max = (float) input
                            .stream()
                            .max(Comparator
                                .comparing(EXTRACT_VALUE_OF_CELL))
                            .get().getValue();
                        Float min = (float) input
                            .stream()
                            .min(Comparator
                                .comparing(EXTRACT_VALUE_OF_CELL))
                            .get().getValue();

                        result = (max + min) / 2;
                    }
                }
            }
        }

        return result;
    }

    public Float calculateVariance(DocumentiStat documentiStat) {
        Float result = null;

        if (ObjectUtils.allNotNull(documentiStat)) {
            if (CollectionUtils
                .isNotEmpty(documentiStat.getDatasets())) {

                List<Cell<Integer, String>> input = documentiStat
                    .getDatasets().get(0).getCells();

                Integer length = input.size();
                if (length > 0) {
                    float median = Float
                        .valueOf(calculateMedian(documentiStat));
                    float temp = 0;
                    for (Cell<Integer, String> value : input) {
                        temp += (value.getValue() - median)
                                * (value.getValue() - median);
                    }
                    result = temp / ((float) length - 1);
                }
            }
        }
        return result;
    }

    public Float calculateStandardDeviation(DocumentiStat documentiStat) {
        Float result = null;
        if (ObjectUtils.allNotNull(documentiStat)) {

            if (CollectionUtils
                .isNotEmpty(documentiStat.getDatasets())) {

                List<Cell<Integer, String>> input = documentiStat
                    .getDatasets().get(0).getCells();

                if (!CollectionUtils.isEmpty(input)) {
                    Integer length = input.size();
                    if (length > 0) {
                        float variance = calculateVariance(
                                documentiStat);
                        result = (float) Math.sqrt(variance);
                    }
                }
            }
        }
        return result;
    }

    public Float calculateRowColumnTotal(DocumentiStat documentiStat) {
        Float result = null;
        if (ObjectUtils.allNotNull(documentiStat)) {

            if (CollectionUtils
                .isNotEmpty(documentiStat.getDatasets())) {

                List<Cell<Integer, String>> input = documentiStat
                    .getDatasets().get(0).getCells();

                Integer length = input.size();
                if (length > 0) {
                    result = (float) input
                        .stream().mapToDouble(i -> i.getValue())
                        .sum();
                }
            }
        }
       
        return result;
    }

}
