package org.iStat.api.iConverter;

import java.util.function.Function;
import java.util.stream.Collectors;

import org.iStat.api.common.converter.Converter;
import org.iStat.api.iEntity.Cell;
import org.iStat.api.iEntity.Dataset;
import org.iStat.api.iEntity.DocumentiStat;
import org.iStat.api.iEntity.Cell.CellBuilder;
import org.iStat.api.iEntity.Dataset.DatasetBuilder;
import org.iStat.api.iEntity.DocumentiStat.DocumentiStatBuilder;
import org.iStat.api.iStatIO.request.RequestiStatSave;
import org.iStat.api.iStatIO.request.RequestiStatSaveCell;
import org.iStat.api.iStatIO.request.RequestiStatSaveDataset;
import org.iStat.api.iStatIO.response.ResponseiStatOpen;
import org.iStat.api.iStatIO.response.ResponseiStatOpenCell;
import org.iStat.api.iStatIO.response.ResponseiStatOpenDataset;
import org.iStat.api.iStatTransform.request.RequestiStatTransform;
import org.iStat.api.iStatTransform.request.RequestiStatTransformCell;
import org.iStat.api.iStatTransform.request.RequestiStatTransformDataset;

public class ConverteriStatSaveRequest
        implements Converter<RequestiStatSave, DocumentiStat> {

    private static final Function<RequestiStatSaveDataset, Dataset> API_TO_DATASET = new Function<RequestiStatSaveDataset, Dataset>() {

        @Override
        public Dataset apply(RequestiStatSaveDataset dataset) {
            return new DatasetBuilder()
                .withName(dataset.getName())
                .withCells(dataset
                    .getCells().stream()
                    .map(apiToCell(dataset.getName()))
                    .collect(Collectors.toList()))
                .build();

        }
    };

    private static final Function<RequestiStatSaveCell, Cell<Integer, String>> apiToCell(String datasetName) {
        return new Function<RequestiStatSaveCell, Cell<Integer, String>>() {

            @Override
            public Cell<Integer, String> apply(RequestiStatSaveCell cell) {
                return new CellBuilder<Integer, String>()
                    .withLine(Integer.valueOf(cell.getLine()))
                    .withColumn(cell.getColumn())
                    .withValue(cell.getValue())
                    .withParentDatasetName(datasetName).build();
            }
        };
    }

    @Override
    public DocumentiStat convert(RequestiStatSave from) {
        return new DocumentiStatBuilder()
            .withId(from.getName()).withDatasets(from
                .getDatasets().stream().map(API_TO_DATASET)
                .collect(Collectors.toList()))
            .build();

    }

}
