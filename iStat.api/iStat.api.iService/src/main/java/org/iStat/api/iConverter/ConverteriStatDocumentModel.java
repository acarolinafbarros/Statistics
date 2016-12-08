package org.iStat.api.iConverter;

import java.util.function.Function;
import java.util.stream.Collectors;

import org.iStat.api.common.converter.Converter;
import org.iStat.api.iEntity.Cell;
import org.iStat.api.iEntity.Dataset;
import org.iStat.api.iEntity.DocumentiStat;
import org.iStat.api.iModel.CellModel;
import org.iStat.api.iModel.DatasetModel;
import org.iStat.api.iModel.DocumentIStatModel;

public class ConverteriStatDocumentModel implements Converter<DocumentiStat, DocumentIStatModel> {

    private static final Function<Dataset, DatasetModel> API_TO_DATASET = new Function<Dataset, DatasetModel>() {

        @Override
        public DatasetModel apply(Dataset dataset) {
            return new DatasetModel(dataset.getName(), dataset.getCells().stream().map(apiToCell(dataset.getName())).collect(Collectors.toList()));
        }
    };

    private static final Function<Cell<Integer, String>, CellModel> apiToCell(String datasetName) {
        return new Function<Cell<Integer, String>, CellModel>() {

            @Override
            public CellModel apply(Cell<Integer, String> cell) {
                return new CellModel(datasetName, Integer.valueOf(cell.getLine()), cell.getColumn(), cell.getValue());
            }
        };
    }

    @Override
    public DocumentIStatModel convert(DocumentiStat from) {
        return new DocumentIStatModel(from.getId(), from.getDatasets().stream().map(API_TO_DATASET).collect(Collectors.toList()));
    }

}
