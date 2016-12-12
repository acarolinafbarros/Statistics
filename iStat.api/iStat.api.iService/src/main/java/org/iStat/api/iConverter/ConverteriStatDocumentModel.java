package org.iStat.api.iConverter;

import java.util.function.Function;
import java.util.stream.Collectors;

import org.iStat.api.iCommon.converter.Converter;
import org.iStat.api.iDomain.Cell;
import org.iStat.api.iDomain.Dataset;
import org.iStat.api.iDomain.DocumentiStat;
import org.iStat.api.iEntity.CellEntity;
import org.iStat.api.iEntity.DatasetEntity;
import org.iStat.api.iEntity.DocumentIStatEntity;

public class ConverteriStatDocumentModel implements Converter<DocumentiStat, DocumentIStatEntity> {

    private static final Function<Dataset, DatasetEntity> API_TO_DATASET = new Function<Dataset, DatasetEntity>() {

        @Override
        public DatasetEntity apply(Dataset dataset) {
            return new DatasetEntity(dataset.getName(), dataset.getCells().stream().map(apiToCell(dataset.getName())).collect(Collectors.toList()));
        }
    };

    private static final Function<Cell<Integer, String>, CellEntity> apiToCell(String datasetName) {
        return new Function<Cell<Integer, String>, CellEntity>() {

            @Override
            public CellEntity apply(Cell<Integer, String> cell) {
                return new CellEntity(datasetName, Integer.valueOf(cell.getLine()), cell.getColumn(), cell.getValue());
            }
        };
    }

    @Override
    public DocumentIStatEntity convert(DocumentiStat from) {
        return new DocumentIStatEntity(from.getId(), from.getDatasets().stream().map(API_TO_DATASET).collect(Collectors.toList()));
    }

}
