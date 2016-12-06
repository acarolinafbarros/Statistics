package org.iStat.api.iConverter;

import java.util.function.Function;
import java.util.stream.Collectors;

import org.iStat.api.common.converter.Converter;
import org.iStat.api.iEntity.Cell;
import org.iStat.api.iEntity.Dataset;
import org.iStat.api.iEntity.DocumentiStat;
import org.iStat.api.iStatIO.response.ResponseiStatIOOpen;
import org.iStat.api.iStatIO.response.ResponseiStatIOOpenCell;
import org.iStat.api.iStatIO.response.ResponseiStatIOOpenDataset;

public class ConverteriStatIOSaveRequest
        implements Converter<DocumentiStat, ResponseiStatIOOpen> {

    private static final Function<Dataset, ResponseiStatIOOpenDataset> API_TO_DATASET = new Function<Dataset, ResponseiStatIOOpenDataset>() {

        @Override
        public ResponseiStatIOOpenDataset apply(Dataset dataset) {

            ResponseiStatIOOpenDataset response = new ResponseiStatIOOpenDataset();

            response.setName(dataset.getName());
            response.setCells(dataset
                .getCells().stream().map(apiToCell(dataset.getName()))
                .collect(Collectors.toList()));

            return response;
        }
    };

    private static final Function<Cell<Integer, String>, ResponseiStatIOOpenCell> apiToCell(String datasetName) {
        return new Function<Cell<Integer, String>, ResponseiStatIOOpenCell>() {

            @Override
            public ResponseiStatIOOpenCell apply(Cell<Integer, String> cell) {

                ResponseiStatIOOpenCell response = new ResponseiStatIOOpenCell();
                response.setColumn(cell.getColumn());
                response.setLine(cell.getLine());
                response.setValue(cell.getValue());

                return response;
            }
        };
    }

    @Override
    public ResponseiStatIOOpen convert(DocumentiStat from) {

        ResponseiStatIOOpen response = new ResponseiStatIOOpen();
        response.setDatasets(from
            .getDatasets().stream().map(API_TO_DATASET)
            .collect(Collectors.toList()));

        return response;

    }

}
