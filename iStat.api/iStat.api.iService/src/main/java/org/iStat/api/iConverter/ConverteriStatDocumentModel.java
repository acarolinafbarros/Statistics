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
import org.iStat.api.iEntity.CellEntity;
import org.iStat.api.iEntity.DatasetEntity;
import org.iStat.api.iEntity.DocumentIStatEntity;

public class ConverteriStatDocumentModel implements Converter<DocumentiStat, DocumentIStatEntity> {

	private static final Function<Dataset, DatasetEntity> API_TO_DATASET = new Function<Dataset, DatasetEntity>() {

		@Override
		public DatasetEntity apply(Dataset dataset) {
			return new DatasetEntity(dataset.getName(),
					dataset.getCells().stream().map(apiToCell(dataset.getName())).collect(Collectors.toList()));
		}
	};

	private static final Function<Cell<Integer, String>, CellEntity> apiToCell(String datasetName) {
		return new Function<Cell<Integer, String>, CellEntity>() {

			@Override
			public CellEntity apply(Cell<Integer, String> cell) {
				
            	Objects.requireNonNull(cell.getLine(), "line must be not null!");
            	Objects.requireNonNull(cell.getColumn(), "column must be not null!");
            	Objects.requireNonNull(cell.getValue(), "value must be not null!");
				
				return new CellEntity(datasetName, Integer.valueOf(cell.getLine()), cell.getColumn(), cell.getValue());
			}
		};
	}

	@Override
	public DocumentIStatEntity convert(DocumentiStat from) throws ConvertException {

		if (!ObjectUtils.allNotNull(from, from.getDatasets())) {
			throw new ConvertException("'operation=convert', 'from=" + from + "'");
		}
		try {
			return new DocumentIStatEntity(from.getId(),
					from.getDatasets().stream().map(API_TO_DATASET).collect(Collectors.toList()));
		} catch (NullPointerException ex) {
			throw new ConvertException("'operation=convert', 'from=" + from + "'", ex);
		}

	}

}
