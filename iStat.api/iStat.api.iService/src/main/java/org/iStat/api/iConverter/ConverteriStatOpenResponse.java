package org.iStat.api.iConverter;

import java.util.function.Function;
import java.util.stream.Collectors;

import org.iStat.api.iCommon.converter.Converter;
import org.iStat.api.iDomain.Cell;
import org.iStat.api.iDomain.Dataset;
import org.iStat.api.iDomain.DocumentiStat;
import org.iStat.api.iStatIO.response.ResponseiStatOpen;
import org.iStat.api.iStatIO.response.ResponseiStatOpenCell;
import org.iStat.api.iStatIO.response.ResponseiStatOpenDataset;

public class ConverteriStatOpenResponse
        implements Converter<DocumentiStat, ResponseiStatOpen> {

    private static final Function<Dataset, ResponseiStatOpenDataset> API_TO_DATASET = new Function<Dataset, ResponseiStatOpenDataset>() {

        @Override
        public ResponseiStatOpenDataset apply(Dataset dataset) {

            ResponseiStatOpenDataset response = new ResponseiStatOpenDataset();

            response.setName(dataset.getName());
            response.setCells(dataset
                .getCells().stream().map(apiToCell(dataset.getName()))
                .collect(Collectors.toList()));

            return response;
        }
    };

    private static final Function<Cell<Integer, String>, ResponseiStatOpenCell> apiToCell(String datasetName) {
        return new Function<Cell<Integer, String>, ResponseiStatOpenCell>() {

            @Override
            public ResponseiStatOpenCell apply(Cell<Integer, String> cell) {

                ResponseiStatOpenCell response = new ResponseiStatOpenCell();
                response.setColumn(cell.getColumn());
                response.setLine(cell.getLine());
                response.setValue(cell.getValue());

                return response;
            }
        };
    }

    @Override
    public ResponseiStatOpen convert(DocumentiStat from) {

        ResponseiStatOpen response = new ResponseiStatOpen();
        response.setDatasets(from
            .getDatasets().stream().map(API_TO_DATASET)
            .collect(Collectors.toList()));

        return response;

    }

}
