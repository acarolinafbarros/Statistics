package org.iStat.api.iConverter;

import java.util.function.Function;
import java.util.stream.Collectors;

import org.iStat.api.common.converter.Converter;
import org.iStat.api.iEntity.Cell;
import org.iStat.api.iEntity.Cell.CellBuilder;
import org.iStat.api.iEntity.Dataset;
import org.iStat.api.iEntity.Dataset.DatasetBuilder;
import org.iStat.api.iEntity.DocumentiStat;
import org.iStat.api.iEntity.DocumentiStat.DocumentiStatBuilder;
import org.iStat.api.iStatTransform.request.RequestiStatTransformCell;
import org.iStat.api.iStatTransform.request.RequestiStatTransformDataset;
import org.iStat.api.iStatTransform.request.RequestiStatTransform;

public class ConverteriStatTransformRequest
        implements Converter<RequestiStatTransform, DocumentiStat> {

    private static final Function<RequestiStatTransformDataset, Dataset> API_TO_DATASET = new Function<RequestiStatTransformDataset, Dataset>() {

        @Override
        public Dataset apply(RequestiStatTransformDataset dataset) {
            return new DatasetBuilder()
                .withName(dataset.getName())
                .withCells(dataset
                    .getCells().stream()
                    .map(apiToCell(dataset.getName()))
                    .collect(Collectors.toList()))
                .build();

        }
    };

    private static final Function<RequestiStatTransformCell, Cell<Integer, String>> apiToCell(String datasetName) {
        return new Function<RequestiStatTransformCell, Cell<Integer, String>>() {

            @Override
            public Cell<Integer, String> apply(RequestiStatTransformCell cell) {
                return new CellBuilder<Integer, String>()
                    .withLine(Integer.valueOf(cell.getLine()))
                    .withColumn(cell.getColumn())
                    .withValue(cell.getValue())
                    .withParentDatasetName(datasetName).build();
            }
        };
    }

    @Override
    public DocumentiStat convert(RequestiStatTransform from) {
        return new DocumentiStatBuilder()
            .withDatasets(from
                .getDatasets().stream().map(API_TO_DATASET)
                .collect(Collectors.toList()))
            .build();

    }

}
