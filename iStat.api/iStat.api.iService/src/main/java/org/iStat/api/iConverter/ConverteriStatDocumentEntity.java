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
import org.iStat.api.iModel.CellModel;
import org.iStat.api.iModel.DatasetModel;
import org.iStat.api.iModel.DocumentIStatModel;

public class ConverteriStatDocumentEntity implements Converter<DocumentIStatModel, DocumentiStat> {

    private static final Function<DatasetModel, Dataset> API_TO_DATASET = new Function<DatasetModel, Dataset>() {

        @Override
        public Dataset apply(DatasetModel dataset) {
            return new DatasetBuilder().withName(dataset.getName())
                    .withCells(dataset.getCells().stream().map(apiToCell(dataset.getName())).collect(Collectors.toList())).build();

        }
    };

    private static final Function<CellModel, Cell<Integer, String>> apiToCell(String datasetName) {
        return new Function<CellModel, Cell<Integer, String>>() {

            @Override
            public Cell<Integer, String> apply(CellModel cell) {
                return new CellBuilder<Integer, String>().withLine(Integer.valueOf(cell.getLine())).withColumn(cell.getColumn())
                        .withValue(cell.getValue()).withParentDatasetName(datasetName).build();
            }
        };
    }

    @Override
    public DocumentiStat convert(DocumentIStatModel from) {
        return new DocumentiStatBuilder().withId(from.getName())
                .withDatasets(from.getDatasets().stream().map(API_TO_DATASET).collect(Collectors.toList())).build();

    }

}
