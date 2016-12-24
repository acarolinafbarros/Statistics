package org.iStat.api.iConverter;

import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.iStat.api.iCommon.converter.Converter;
import org.iStat.api.iDomain.Cell;
import org.iStat.api.iDomain.Dataset;
import org.iStat.api.iDomain.DocumentiStat;
import org.iStat.api.iStatTransform.response.ResponseiStatTransform;
import org.iStat.api.iStatTransform.response.ResponseiStatTransformCell;
import org.iStat.api.iStatTransform.response.ResponseiStatTransformDataset;

public class ConverteriStatTransformResponse implements Converter<DocumentiStat, ResponseiStatTransform> {

	private static final Function<Dataset, ResponseiStatTransformDataset> API_TO_DATASET = new Function<Dataset, ResponseiStatTransformDataset>() {

		@Override
		public ResponseiStatTransformDataset apply(Dataset dataset) {

			ResponseiStatTransformDataset response = new ResponseiStatTransformDataset();

			response.setName(dataset.getName());
			response.setCells(
					dataset.getCells().stream().map(apiToCell(dataset.getName())).collect(Collectors.toList()));

			return response;
		}
	};

	private static final Function<Cell<Integer, String>, ResponseiStatTransformCell> apiToCell(String datasetName) {
		return new Function<Cell<Integer, String>, ResponseiStatTransformCell>() {

			@Override
			public ResponseiStatTransformCell apply(Cell<Integer, String> cell) {

				ResponseiStatTransformCell response = new ResponseiStatTransformCell();
				response.setColumn(cell.getColumn());
				response.setLine(cell.getLine());
				response.setValue(cell.getValue());

				return response;
			}
		};
	}

	@Override
	public ResponseiStatTransform convert(DocumentiStat from) {

		Objects.requireNonNull(from, "from must be not null!");
		Objects.requireNonNull(from.getDatasets(), "getDatasets must be not null!");

		ResponseiStatTransform response = new ResponseiStatTransform();
		response.setDatasets(from.getDatasets().stream().map(API_TO_DATASET).collect(Collectors.toList()));

		return response;

	}

}
