package org.iStat.api.iConverter;

import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.lang3.ObjectUtils;
import org.iStat.api.iCommon.converter.Converter;
import org.iStat.api.iCommon.converter.exception.ConvertException;
import org.iStat.api.iDomain.Cell;
import org.iStat.api.iDomain.Dataset;
import org.iStat.api.iDomain.DocumentiStat;
import org.iStat.api.iStatDataset.response.ResponseiStatOpen;
import org.iStat.api.iStatDataset.response.ResponseiStatOpenCell;
import org.iStat.api.iStatDataset.response.ResponseiStatOpenDataset;

public class ConverteriStatOpenResponse implements Converter<DocumentiStat, ResponseiStatOpen> {

	private static final Function<Dataset, ResponseiStatOpenDataset> API_TO_DATASET = new Function<Dataset, ResponseiStatOpenDataset>() {

		@Override
		public ResponseiStatOpenDataset apply(Dataset dataset) {

			ResponseiStatOpenDataset response = new ResponseiStatOpenDataset();

			response.setName(dataset.getName());
			response.setCells(
					dataset.getCells().stream().map(apiToCell(dataset.getName())).collect(Collectors.toList()));

			return response;
		}
	};

	private static final Function<Cell<Integer, String>, ResponseiStatOpenCell> apiToCell(String datasetName) {
		return new Function<Cell<Integer, String>, ResponseiStatOpenCell>() {

			@Override
			public ResponseiStatOpenCell apply(Cell<Integer, String> cell) {

            	Objects.requireNonNull(cell.getLine(), "line must be not null!");
            	Objects.requireNonNull(cell.getColumn(), "column must be not null!");
				
				ResponseiStatOpenCell response = new ResponseiStatOpenCell();
				response.setColumn(cell.getColumn());
				response.setLine(cell.getLine());
				response.setValue(cell.getValue());

				return response;
			}
		};
	}

	@Override
	public ResponseiStatOpen convert(DocumentiStat from) throws ConvertException {

		if (!ObjectUtils.allNotNull(from, from.getDatasets())) {
			throw new ConvertException("'operation=convert', 'from=" + from + "'");
		}

		try {
			ResponseiStatOpen response = new ResponseiStatOpen();
			response.setDatasets(from.getDatasets().stream().map(API_TO_DATASET).collect(Collectors.toList()));

			return response;
		} catch (NullPointerException ex) {
			throw new ConvertException("'operation=convert', 'from=" + from + "'", ex);
		}

	}

}
