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
import org.iStat.api.iDomain.Cell.CellBuilder;
import org.iStat.api.iDomain.Dataset.DatasetBuilder;
import org.iStat.api.iDomain.DocumentiStat.DocumentiStatBuilder;
import org.iStat.api.iStatTransform.request.RequestiStatTransformCell;
import org.iStat.api.iStatTransform.request.RequestiStatTransformDataset;
import org.iStat.api.iStatTransform.request.RequestiStatTransform;

public class ConverteriStatTransformRequest implements Converter<RequestiStatTransform, DocumentiStat> {

	private static final Function<RequestiStatTransformDataset, Dataset> API_TO_DATASET = new Function<RequestiStatTransformDataset, Dataset>() {

		@Override
		public Dataset apply(RequestiStatTransformDataset dataset) {
			return new DatasetBuilder().withName(dataset.getName())
					.withCells(
							dataset.getCells().stream().map(apiToCell(dataset.getName())).collect(Collectors.toList()))
					.build();

		}
	};

	private static final Function<RequestiStatTransformCell, Cell<Integer, String>> apiToCell(String datasetName) {
		return new Function<RequestiStatTransformCell, Cell<Integer, String>>() {

			@Override
			public Cell<Integer, String> apply(RequestiStatTransformCell cell) {
				
            	Objects.requireNonNull(cell.getLine(), "line must be not null!");
            	Objects.requireNonNull(cell.getColumn(), "column must be not null!");
            	Objects.requireNonNull(cell.getValue(), "value must be not null!");
				
				return new CellBuilder<Integer, String>().withLine(Integer.valueOf(cell.getLine()))
						.withColumn(cell.getColumn()).withValue(cell.getValue()).withParentDatasetName(datasetName)
						.build();
			}
		};
	}

	@Override
	public DocumentiStat convert(RequestiStatTransform from) throws ConvertException {

		if (!ObjectUtils.allNotNull(from, from.getDatasets())) {
			throw new ConvertException("'operation=convert', 'from=" + from + "'");
		}

		try {
			return new DocumentiStatBuilder()
					.withDatasets(from.getDatasets()
							.stream().map(API_TO_DATASET).collect(Collectors.toList()))
					.build();
		} catch (NullPointerException ex) {
			throw new ConvertException("'operation=convert', 'from=" + from + "'", ex);
		}

	}

}
