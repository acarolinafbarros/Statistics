package org.iStat.api.iLogic;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
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

    /**
     * Method to fill the null values of matrix with the value given on the
     * input.
     * 
     * @param valueToFill
     * @param matrixToChange
     * @return Float[][]
     */
    public Float[][] fillMatrixEmptyValues(Integer valueToFill, Float[][] matrixToChange) {
        for (int i = 0; i < matrixToChange.length; i++) {
            for (int j = 1; j < matrixToChange[i].length; j++) {
                if (matrixToChange[i][j] == null) {
                    matrixToChange[i][j] = new Float(valueToFill);
                }
            }
        }

        return matrixToChange;
    }

    /**
     * Method to convert the list of cells from a dataset to a matrix.
     * 
     * @param dataset
     * @return Float[][]
     */
    public Float[][] convertListToMatrix(Dataset dataset) {
        List<String> orderedColumns = new ArrayList<>(dataset.getAllColumnsOfCells());
        List<Integer> orderedLines = new ArrayList<>(dataset.getAllLinesOfCells());

        Float[][] matrix = new Float[orderedLines.size()][orderedColumns.size()];

        for (int row = 0; row < orderedLines.size(); row++) {
            for (int column = 0; column < orderedColumns.size(); column++) {
                matrix[row][column] = dataset.getValueOfColumnLine(column, row);
            }
        }

        return fillMatrixEmptyValues(0, matrix);

    }

    /**
     * Method to convert a matrix into a list of cells.
     * 
     * @param input
     * @param startColumn
     * @param startLine
     * @return List<Cell<Integer,String>>
     */
    public List<Cell<Integer, String>> convertMatrixtToList(Float[][] input, String startColumn, Integer startLine) {
        List<Cell<Integer, String>> output = new ArrayList<>();

        // A - Z columns
        char[] columnArray = IntStream.rangeClosed('A', 'Z').mapToObj(c -> "" + (char) c).collect(Collectors.joining()).toCharArray();
        List<String> columns = new ArrayList<>();
        for (int i = 0; i < columnArray.length; i++) {
            columns.add(columnArray[i] + "");
        }

        // 1 - 200 lines
        List<Integer> lines = new ArrayList<>();
        for (int i = 1; i < 200; i++) {
            lines.add(i);
        }

        Integer columnPosition = null;
        for (int i = 0; i < columns.size(); i++) {
            if (columns.get(i).equals(startColumn)) {
                columnPosition = i;
                break;
            }
        }

        Integer linePosition = null;
        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).equals(startLine)) {
                linePosition = i;
                break;
            }
        }

        CellBuilder<Integer, String> builder = new CellBuilder<>();
        Integer columnPositionIterate = null;
        for (int row = 0; row < input.length; row++) {
            columnPositionIterate = columnPosition;
            for (int column = 0; column < input[row].length; column++) {
                builder.withColumn(columns.get(columnPositionIterate));
                builder.withLine(lines.get(linePosition));
                builder.withValue(input[row][column]);
                Cell<Integer, String> cell = builder.build();
                output.add(cell);

                columnPositionIterate++;
            }
            linePosition++;
        }
        return output;
    }

    private Function<Cell<Integer, String>, Float> EXTRACT_VALUE_OF_CELL = new Function<Cell<Integer, String>, Float>() {

        @Override
        public Float apply(Cell<Integer, String> cell) {
            return cell.getValue() == null ? 0.0f : cell.getValue();
        }
    };

    public DocumentiStat transformTranspose(DocumentiStat documentiStat) {
    	DocumentiStat result = null;
    	
    	if (ObjectUtils.allNotNull(documentiStat)) {
            if (CollectionUtils.isNotEmpty(documentiStat.getDatasets())) {
            	
            	Dataset input = documentiStat.getDatasets().get(0);             	
        		Float [][] matrix = convertListToMatrix(input);
        		
        		int line_size = matrix.length;
        		int column_size = matrix[0].length;	
        		Float [][] matrix_final = new Float[line_size][column_size];
        		        		
        		for (int line = 0; line < line_size; ++line) {
        			for (int column = 0; column < column_size; ++column) {
        				matrix_final[column][line] =  input.getValueOfColumnLine(line, column);        			
        			}
        		}
        		
        		List<Cell<Integer, String>> output = convertMatrixtToList(matrix_final, "A", 1);
        		
        		DatasetBuilder builderDataset = new DatasetBuilder();
                builderDataset.withCells(output);
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

                            CellBuilder<Integer, String> builderCell = new CellBuilder<Integer, String>();
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

    public DocumentiStat transformAddScalar(DocumentiStat documentiStat, Float scalar) {
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

                            sum = input.get(i).getValue() + scalar;

                            CellBuilder<Integer, String> builderCell = new CellBuilder<Integer, String>();
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

    public DocumentiStat transformAddTwoDatasets(DocumentiStat documentiStat) {
    	DocumentiStat result = null;
    	
    	if (ObjectUtils.allNotNull(documentiStat)) {
            if (CollectionUtils.isNotEmpty(documentiStat.getDatasets())) {
            	
            	Dataset input1 = documentiStat.getDatasets().get(0);             	
        		Float [][] matrix1 = convertListToMatrix(input1);
        		
            	Dataset input2 = documentiStat.getDatasets().get(1);             	
        		Float [][] matrix2 = convertListToMatrix(input2);
        		
        		int line_size = matrix1.length;
        		System.out.println("---- LINHA == " + line_size);
        		int column_size = matrix1[0].length;	
        		System.out.println("---- COLUNA == " + column_size);
        		Float [][] matrix_final = new Float[line_size][column_size];
        		        		
        		for (int line = 0; line < line_size; ++line) {
        			for (int column = 0; column < column_size; ++column) {
        				matrix_final[line][column] =  input1.getValueOfColumnLine(line, column) + input2.getValueOfColumnLine(line, column);      
        				System.out.println("---- MATRIX == " + matrix_final[line][column] + " || 1 - " + input1.getValueOfColumnLine(line, column) + " || 2 - " + input2.getValueOfColumnLine(line, column) + "/n");
        			}
        		}
        		
        		List<Cell<Integer, String>> output = convertMatrixtToList(matrix_final, "A", 1);
        		
        		DatasetBuilder builderDataset = new DatasetBuilder();
                builderDataset.withCells(output);
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

    public DocumentiStat transformMultiplyTwoDatasets(DocumentiStat documentiStat) {
    	DocumentiStat result = null;
    	
    	if (ObjectUtils.allNotNull(documentiStat)) {
            if (CollectionUtils.isNotEmpty(documentiStat.getDatasets())) {
            	
            	Dataset input1 = documentiStat.getDatasets().get(0);             	
        		Float [][] matrix1 = convertListToMatrix(input1);
        		int line_size1 = matrix1.length;
        		int column_size1 = matrix1[0].length;
        		
            	Dataset input2 = documentiStat.getDatasets().get(1);             	
        		Float [][] matrix2 = convertListToMatrix(input2);
        		int line_size2 = matrix2.length;
        		int column_size2 = matrix2[0].length;
        		
        		if (column_size1 != line_size2) {
        			System.out.println("The number of columns in 1 does not equal the number of rows in 2 \n");
        		} else {
        			
            		Float [][] matrix_final = new Float[line_size1][column_size2];
            		float sum = 0;
            		
            		for (int i = 0; i < line_size1; ++i) {          			
            			for (int j = 0; j < column_size2; ++j) {    				
            				for (int k = 0; k < column_size1; ++k) {
            					sum += input1.getValueOfColumnLine(i, k) * input2.getValueOfColumnLine(k, j);
            				}		
            				matrix_final[i][j] = sum;
            				sum = 0;
            			    System.out.println("---- MATRIX == " + matrix_final[i][j] + "\n");
            			}
            		}
            		          		
            		List<Cell<Integer, String>> output = convertMatrixtToList(matrix_final, "A", 1);
            		
            		DatasetBuilder builderDataset = new DatasetBuilder();
                    builderDataset.withCells(output);
                    Dataset dataset = builderDataset.build();
                    List<Dataset> datasets = new ArrayList<Dataset>();
                    datasets.add(dataset);
                    DocumentiStatBuilder builderDocument = new DocumentiStatBuilder();
                    builderDocument.withDatasets(datasets);

                    result = builderDocument.build();
        		}	      		
            }
    	}
    	
    	return result;
    }
    
    
}
