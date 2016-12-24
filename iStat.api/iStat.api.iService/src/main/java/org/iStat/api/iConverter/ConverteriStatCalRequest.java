package org.iStat.api.iConverter;

import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.iStat.api.iCommon.converter.Converter;
import org.iStat.api.iCommon.converter.exception.ConvertException;
import org.iStat.api.iDomain.Cell;
import org.iStat.api.iDomain.Dataset;
import org.iStat.api.iDomain.DocumentiStat;
import org.iStat.api.iDomain.Cell.CellBuilder;
import org.iStat.api.iDomain.Dataset.DatasetBuilder;
import org.iStat.api.iDomain.DocumentiStat.DocumentiStatBuilder;
import org.iStat.api.iStatCalc.request.RequestiStatCalc;
import org.iStat.api.iStatCalc.request.RequestiStatCalcCell;
import org.iStat.api.iStatCalc.request.RequestiStatCalcDataset;

public class ConverteriStatCalRequest
        implements Converter<RequestiStatCalc, DocumentiStat> {

    private static final Function<RequestiStatCalcDataset, Dataset> API_TO_DATASET = new Function<RequestiStatCalcDataset, Dataset>() {

        @Override
        public Dataset apply(RequestiStatCalcDataset dataset) {
            return new DatasetBuilder()
                .withName(dataset.getName())
                .withCells(dataset
                    .getCells().stream()
                    .map(apiToCell(dataset.getName()))
                    .collect(Collectors.toList()))
                .build();

        }
    };

    private static final Function<RequestiStatCalcCell, Cell<Integer, String>> apiToCell(String datasetName) {
        return new Function<RequestiStatCalcCell, Cell<Integer, String>>() {

            @Override
            public Cell<Integer, String> apply(RequestiStatCalcCell cell) {
                return new CellBuilder<Integer, String>()
                    .withLine(Integer.valueOf(cell.getLine()))
                    .withColumn(cell.getColumn())
                    .withValue(cell.getValue())
                    .withParentDatasetName(datasetName).build();
            }
        };
    }

    @Override
    public DocumentiStat convert(RequestiStatCalc from) {
    	
		Objects.requireNonNull(from, "from must be not null!");
		Objects.requireNonNull(from.getDatasets(), "getDatasets must be not null!");
		
        return new DocumentiStatBuilder()
            .withDatasets(from
                .getDatasets().stream().map(API_TO_DATASET)
                .collect(Collectors.toList()))
            .build();

    }

}
