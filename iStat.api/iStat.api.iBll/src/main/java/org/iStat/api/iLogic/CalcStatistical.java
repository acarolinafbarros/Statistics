package org.iStat.api.iLogic;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.iStat.api.iDomain.Cell;
import org.iStat.api.iDomain.DocumentiStat;
import org.iStat.api.iExceptions.CalcException;
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

        return roundResult(result);
    }

    // FIXME: Call the exception when the total result was null
    public Float calculateGeometricMean(DocumentiStat documentiStat)
            throws CalcException {
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
                        if (mult >= 0) {
                            double geoMean = Math.pow(mult,
                                    1.0 / length);
                            result = (float) geoMean;
                        } else {
                            throw new CalcException();
                        }
                    }
                }
            }
        }

        return roundResult(result);
    }

    public Float calculateMode(DocumentiStat documentiStat) {

        Float result = null;
        if (ObjectUtils.allNotNull(documentiStat)) {

            if (CollectionUtils
                .isNotEmpty(documentiStat.getDatasets())) {

                List<Cell<Integer, String>> input = documentiStat
                    .getDatasets().get(0).getCells();

                if (!CollectionUtils.isEmpty(input)) {

                    List<Float> values = new ArrayList<>();

                    input.stream().forEach(
                            cell -> values.add(cell.getValue()));

                    final Map<Float, Long> countFrequencies = values
                        .stream()
                        .collect(Collectors.groupingBy(
                                Function.identity(),
                                Collectors.counting()));

                    final long maxFrequency = countFrequencies
                        .values().stream().mapToLong(count -> count)
                        .max().orElse(-1);

                    for (Map.Entry<Float, Long> entry : countFrequencies
                        .entrySet()) {
                        Float cellValue = entry.getKey();

                        if (entry
                            .getValue().longValue() == maxFrequency) {

                            if (result == null
                                    || cellValue < result) {
                                result = cellValue;
                            }
                        }
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

        return roundResult(result);
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
                    result = temp / ((float) length);
                }
            }
        }
        return roundResult(result);
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
        return roundResult(result);
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

        return roundResult(result);
    }

    private Float roundResult(Float result) {
        if (ObjectUtils.allNotNull(result)) {
            return Math.round(result * 100.0f) / 100.0f;
        }

        return null;
    }

}
