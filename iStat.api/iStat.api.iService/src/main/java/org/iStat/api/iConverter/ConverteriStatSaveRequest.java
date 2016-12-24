package org.iStat.api.iConverter;

import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.iStat.api.iCommon.converter.Converter;
import org.iStat.api.iCommon.converter.exception.ConvertException;
import org.iStat.api.iDomain.Cell;
import org.iStat.api.iDomain.Cell.CellBuilder;
import org.iStat.api.iDomain.Dataset;
import org.iStat.api.iDomain.Dataset.DatasetBuilder;
import org.iStat.api.iDomain.DocumentiStat;
import org.iStat.api.iDomain.DocumentiStat.DocumentiStatBuilder;
import org.iStat.api.iStatDataset.request.RequestiStatSave;
import org.iStat.api.iStatDataset.request.RequestiStatSaveCell;
import org.iStat.api.iStatDataset.request.RequestiStatSaveDataset;

public class ConverteriStatSaveRequest implements Converter<RequestiStatSave, DocumentiStat> {

	private static final Function<RequestiStatSaveDataset, Dataset> API_TO_DATASET = new Function<RequestiStatSaveDataset, Dataset>() {

		@Override
		public Dataset apply(RequestiStatSaveDataset dataset) {
			return new DatasetBuilder().withName(dataset.getName())
					.withCells(
							dataset.getCells().stream().map(apiToCell(dataset.getName())).collect(Collectors.toList()))
					.build();

		}
	};

	private static final Function<RequestiStatSaveCell, Cell<Integer, String>> apiToCell(String datasetName) {
		return new Function<RequestiStatSaveCell, Cell<Integer, String>>() {

			@Override
			public Cell<Integer, String> apply(RequestiStatSaveCell cell) {
				return new CellBuilder<Integer, String>().withLine(Integer.valueOf(cell.getLine()))
						.withColumn(cell.getColumn()).withValue(cell.getValue()).withParentDatasetName(datasetName)
						.build();
			}
		};
	}

	@Override
	public DocumentiStat convert(RequestiStatSave from) throws ConvertException {

		Objects.requireNonNull(from, "from must be not null!");
		Objects.requireNonNull(from.getName(), "name must be not null!");
		Objects.requireNonNull(from.getDatasets(), "dataset must be not null!");

		if (StringUtils.isEmpty(from.getName())) {
			throw new ConvertException("Missing the name of document!");
		}

		return new DocumentiStatBuilder().withId(from.getName())
				.withDatasets(from.getDatasets().stream().map(API_TO_DATASET).collect(Collectors.toList())).build();

	}

}
