package org.iStat.api.iLogic;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.IntStream;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.iStat.api.iEntity.Cell;
import org.iStat.api.iEntity.Cell.CellBuilder;
import org.iStat.api.iEntity.Dataset;
import org.iStat.api.iEntity.Dataset.DatasetBuilder;
import org.iStat.api.iEntity.DocumentiStat;
import org.iStat.api.iEntity.DocumentiStat.DocumentiStatBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TransformStatistical {

	private final Logger LOG = LoggerFactory.getLogger(TransformStatistical.class);
	
	public float [][] convertListToMatrix(List<Cell<Integer, String>> list){

		int size = list.size();
		ArrayList<String> nOfColumns = new ArrayList<String>();
		ArrayList<Integer> nOfLines = new ArrayList<Integer>();
		
		// PT - Calcular numero de colunas	
		nOfColumns.add(list.get(0).getColumn()); 
		
		for (int i = 0; i < size ; ++i) {		
			if (!nOfColumns.contains(list.get(i).getColumn())){
				nOfColumns.add(list.get(i).getColumn());
			}	
		}		
		
		int countColumns = nOfColumns.size();

		// PT - Calcular numero de linhas		
		nOfLines.add(list.get(0).getLine()); 
		
		for (int i = 0; i < size ; ++i) {		
			if (!nOfLines.contains(list.get(i).getLine())){
				nOfLines.add(list.get(i).getLine());
			}	
		}		
		int countLines = nOfLines.size();
		
		// PT - Construir a matriz			
		float [][] matrix  = new float [countLines][countColumns] ;
		
		for (int line = 0; line < countLines; ++line) {		
			for (int column = 0; column < countColumns; ++column) {
				matrix[line][0] = list.get(line).getValue();
				matrix[0][column] = list.get(column).getValue();
			}
		}
		
		return matrix;
		
	}

	public List<Cell<Integer, String>> convertMatrixtToList(float [][] matrix){
		
		List<Cell<Integer, String>> output = new ArrayList<>();
		
		for (int line = 0; line < matrix.length; ++line) {		
			for (int column = 0; column < matrix[line].length; ++column) {

				String l_column = Float.toString(column);
				float l_value = matrix[line][column];
				
				CellBuilder<Integer, String> builderCell = new CellBuilder<>();
				builderCell.withLine(line);
				builderCell.withColumn(l_column);
				builderCell.withValue(l_value);
                Cell<Integer, String> cell = builderCell.build();
                output.set(line,cell);			
			}
		}
		
		return output;
	}
	
	private Function<Cell<Integer, String>, Float> EXTRACT_VALUE_OF_CELL = new Function<Cell<Integer, String>, Float>() {

        @Override
        public Float apply(Cell<Integer, String> cell) {
            return cell.getValue() == null ? 0.0f : cell.getValue();
        }
    };

    public DocumentiStat transformScale(DocumentiStat documentiStat, Float scalar) {
        DocumentiStat result = null;
        Float sum = null;
        
        if (ObjectUtils.allNotNull(documentiStat)) {
            if (CollectionUtils.isNotEmpty(documentiStat.getDatasets())) {
            	
                List<Cell<Integer, String>> input = documentiStat.getDatasets().get(0).getCells();
                List<Cell<Integer, String>> dataset_final = new ArrayList<>();

                if (!CollectionUtils.isEmpty(input)) {
                    Integer length = input.size();
                    if (length > 0) {
                    	for (int i = 0; i < length; ++i) {
                    		
                    		sum = input.get(i).getValue() * scalar;
                    				
                            CellBuilder<Integer, String> builderCell = new CellBuilder<Integer,String>();
                            builderCell.withLine(input.get(i).getLine());
                            builderCell.withColumn(input.get(i).getColumn());
                            builderCell.withValue(sum);
                            Cell<Integer, String> cell = builderCell.build();
                            dataset_final.add(cell); 
                    	}               	
                    }
                }  
                DatasetBuilder builderDataset = new DatasetBuilder();
                builderDataset.withCells(dataset_final);
                Dataset dataset = builderDataset.build();
                List<Dataset> datasets = new ArrayList<Dataset>();
                datasets.add(dataset);
                DocumentiStatBuilder builderDocument = new DocumentiStatBuilder();
                builderDocument.withDatasets(datasets);
                
                result = builderDocument.build();
            }    
        }
        return result;
    }	
}
