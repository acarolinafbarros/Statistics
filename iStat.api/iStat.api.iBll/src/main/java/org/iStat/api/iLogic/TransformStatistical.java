package org.iStat.api.iLogic;

import static com.google.common.collect.Lists.newArrayList;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.iStat.api.iCommon.utils.MatrixUtils;
import org.iStat.api.iDomain.Cell;
import org.iStat.api.iDomain.Cell.CellBuilder;
import org.iStat.api.iDomain.Dataset;
import org.iStat.api.iDomain.Dataset.DatasetBuilder;
import org.iStat.api.iDomain.DocumentiStat;
import org.iStat.api.iDomain.DocumentiStat.DocumentiStatBuilder;
import org.iStat.api.iExceptions.DomainException;
import org.iStat.api.iExceptions.TransformException;

public class TransformStatistical {

    public enum TransformType {
        LINE, COLUMN;
    }
    
    /**
     * Method responsible for transpose the documentiStat.
     * 
     * @param   documentiStat   List of datasets
     * @param 	finalLine		Cell of final line
     * @param 	finalColumn		Cell of final column
     * @return  result          DocumentiStat with tranpose tranformation   
     * @throws	DomainException    
     */
    public DocumentiStat transformTranspose(DocumentiStat documentiStat, int finalLine, String finalColumn) throws DomainException {
        DocumentiStat result = null;

        if (ObjectUtils.allNotNull(documentiStat)) {
            if (CollectionUtils.isNotEmpty(documentiStat.getDatasets())) {

                Dataset dataset = documentiStat.getDatasets().get(0);
                Float[][] matrix = dataset.convertToMatrix();

                int lineSize = matrix.length;
                int columnSize = matrix[0].length;
                Float[][] matrixFinal = new Float[columnSize][lineSize];

                for (int line = 0; line < lineSize; ++line) {
                    for (int column = 0; column < columnSize; ++column) {
                        matrixFinal[column][line] = matrix[line][column];
                    }
                }

                List<Cell<Integer, String>> output = dataset.convertMatrixtToList(matrixFinal, finalColumn, finalLine);

                result = new DocumentiStatBuilder()
                    .withId("document")
                    .withDatasets(newArrayList(new DatasetBuilder().withCells(output).withName("dataset").build())).build();

            }
        }

        return result;
    }

    /**
     * Method responsible for scale the documentiStat
     * (multiply all elements by a scalar – matrix algebra).
     * 
     * @param   documentiStat   List of datasets
     * @param 	scalar			Numeric (float)
     * @return  result          DocumentiStat with scale tranformation       
     */
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
    
    /**
     * Method responsible for add a scalar to every element from documentiStat.
     *
     * @param   documentiStat   List of datasets
     * @param 	scalar			Numeric value
     * @return  result          DocumentiStat with scalar tranformation       
     */
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
    
    /**
     * Method responsible for add two datasets from documentiStat (matrix algebra).
     *
     * @param   documentiStat   List of datasets
     * @param 	finalLine		Cell of final line
     * @param 	finalColumn		Cell of final column
     * @return  result          DocumentiStat with sum transformation  
     * @throws	DomainException   
     */
    public DocumentiStat transformAddTwoDatasets(DocumentiStat documentiStat, int finalLine, String finalColumn) throws DomainException, TransformException {

        DocumentiStat result = null;

        if (ObjectUtils.allNotNull(documentiStat)) {
            if (CollectionUtils.isNotEmpty(documentiStat.getDatasets())) {

                Dataset dataset1 = documentiStat.getDatasets().get(0);
                Float[][] matrixDataset1 = dataset1.convertToMatrix();

                Dataset dataset2 = documentiStat.getDatasets().get(1);
                Float[][] matrixDataset2 = dataset2.convertToMatrix();

                int matrixDataset1SizeLine = matrixDataset1.length;
                int matrixDataset1SizeColumn = matrixDataset1[0].length;

                int matrixDataset2SizeLine = matrixDataset2.length;
                int matrixDataset2SizeColumn = matrixDataset2[0].length;

                if (matrixDataset1SizeLine != matrixDataset2SizeLine || matrixDataset1SizeColumn != matrixDataset2SizeColumn) {
                    throw new TransformException("The size of input datasets must be the same!");
                } else {

                    Float[][] matrixFinal = new Float[matrixDataset1SizeLine][matrixDataset1SizeColumn];

                    for (int line = 0; line < matrixDataset1SizeLine; ++line) {
                        for (int column = 0; column < matrixDataset1SizeColumn; ++column) {

                            Float valueDataset1 = matrixDataset1[line][column];
                            Float valueDataset2 = matrixDataset2[line][column];

                            matrixFinal[line][column] = valueDataset1 + valueDataset2;

                        }
                    }

                    List<Cell<Integer, String>> output = dataset1.convertMatrixtToList(matrixFinal, finalColumn, finalLine);

                    result = new DocumentiStatBuilder()
                        .withId("document")
                        .withDatasets(newArrayList(new DatasetBuilder().withCells(output).withName("dataset").build())).build();

                }
            }
        }

        return result;
    }

    /**
     * Method responsible for multiply two datasets from documentiStat (matrix algebra).
     *
     * @param   documentiStat   List of datasets
     * @param 	finalLine		Cell of final line
     * @param 	finalColumn		Cell of final column
     * @return  result          DocumentiStat with multiply transformation 
     * @throws	DomainException    
     */
    public DocumentiStat transformMultiplyTwoDatasets(DocumentiStat documentiStat, int finalLine, String finalColumn) throws DomainException, TransformException {
        DocumentiStat result = null;

        if (ObjectUtils.allNotNull(documentiStat)) {
            if (CollectionUtils.isNotEmpty(documentiStat.getDatasets())) {

                Dataset dataset1 = documentiStat.getDatasets().get(0);
                Float[][] matrixDataset1 = dataset1.convertToMatrix();
                int matrixDataset1LineSize = matrixDataset1.length;
                int matrixDataset1ColumnSize = matrixDataset1[0].length;

                Dataset dataset2 = documentiStat.getDatasets().get(1);
                Float[][] matrixDataset2 = dataset2.convertToMatrix();
                int matrixDataset2LineSize = matrixDataset2.length;
                int matrixDataset2ColumnSize = matrixDataset2[0].length;

                if (matrixDataset1ColumnSize != matrixDataset2LineSize) {
                    throw new TransformException("The number of columns in 1 does not equal the number of rows in 2!");
                } else {

                    Float[][] matrixFinal = new Float[matrixDataset1LineSize][matrixDataset2ColumnSize];

                    for (int lineDataset1 = 0; lineDataset1 < matrixDataset1LineSize; ++lineDataset1) {
                        for (int columnDataset2 = 0; columnDataset2 < matrixDataset2ColumnSize; ++columnDataset2) {
                            float sum = 0;
                            for (int columnDataset1 = 0; columnDataset1 < matrixDataset1ColumnSize; ++columnDataset1) {

                                Float value1 = matrixDataset1[lineDataset1][columnDataset1];
                                Float value2 = matrixDataset2[columnDataset1][columnDataset2];

                                sum += value1 * value2;
                            }
                            matrixFinal[lineDataset1][columnDataset2] = sum;
                        }
                    }

                    List<Cell<Integer, String>> output = dataset1.convertMatrixtToList(matrixFinal, finalColumn, finalLine);

                    result = new DocumentiStatBuilder()
                        .withId("document")
                        .withDatasets(newArrayList(new DatasetBuilder().withCells(output).withName("dataset").build())).build();

                }
            }
        }

        return result;
    }

    /**
     * Help method to linear interpolation
     *
     * @param   documentiStat   List of datasets
     * @param	type			Transform type: line/columns
     * @param 	finalLine		Cell of final line
     * @param 	finalColumn		Cell of final column
     * @return  transformationInterpolationLine
     * @return  transformationInterpolationColumn
     * @throws  Exception
     */
    public DocumentiStat transformInterpolation(DocumentiStat documentiStat, TransformType type, int finalLine, String finalColumn) throws Exception {
        switch (type) {
            case LINE:
                return transformInterpolationLine(documentiStat, finalLine, finalColumn);
            case COLUMN:
                return transformInterpolationColumn(documentiStat, finalLine, finalColumn);
            default:
                throw new IllegalArgumentException();
        }
    }

    /**
     * Method responsible for augment the documentiStat using linear interpolation on rows
     *
     * @param   documentiStat   List of datasets
     * @param 	finalLine		Cell of final line
     * @param 	finalColumn		Cell of final column
     * @return  result          DocumentiStat with linear interpolation on rows  
     * @throws	DomainException   
     */
    private DocumentiStat transformInterpolationLine(DocumentiStat documentiStat, int finalLine, String finalColumn) throws DomainException {
        DocumentiStat result = null;

        if (ObjectUtils.allNotNull(documentiStat)) {
            if (CollectionUtils.isNotEmpty(documentiStat.getDatasets())) {

                Dataset input = documentiStat.getDatasets().get(0);
                Float[][] matrix = input.convertToMatrix();
                int matrixLineSize = matrix.length;
                int matrixColumnSize = matrix[0].length;

                Float[][] matrixFinal = new Float[(matrixLineSize * 2) - 1][matrixColumnSize];

                int linhasT = (matrixLineSize * 2) - 1;
                int colT = matrixColumnSize;

                for (int line = 0; line < matrixLineSize; line++) {
                    int aux = line * 2;
                    for (int column = 0; column < colT; ++column) {
                        if (line == 0) {
                            matrixFinal[line][column] = matrix[line][column];
                        } else {
                            matrixFinal[aux][column] = matrix[line][column];
                        }
                    }
                }

                matrixFinal = MatrixUtils.fillMatrixEmptyValues(0, matrixFinal);

                for (int line = 0; line < linhasT; line++) {
                    for (int column = 0; column < colT; ++column) {
                        if (line % 2 == 0 && line != linhasT - 1) {
                            float sum = matrixFinal[line][column] + matrixFinal[line + 2][column];
                            float total = sum / 2;
                            matrixFinal[line + 1][column] = total;
                        }
                    }
                }

                List<Cell<Integer, String>> output = input.convertMatrixtToList(matrixFinal, finalColumn, finalLine);

                result = new DocumentiStatBuilder()
                    .withId("document")
                    .withDatasets(newArrayList(new DatasetBuilder().withCells(output).withName("dataset").build())).build();

            }
        }
        return result;
    }

    /**
     * Method responsible for augment the documentiStat using linear interpolation on columns
     *
     * @param   documentiStat   List of datasets
     * @param 	finalLine		Cell of final line
     * @param 	finalColumn		Cell of final column
     * @return  result          DocumentiStat with linear interpolation on columns  
     * @throws	DomainException   
     */
    private DocumentiStat transformInterpolationColumn(DocumentiStat documentiStat, int finalLine, String finalColumn) throws DomainException {
        DocumentiStat result = null;

        if (ObjectUtils.allNotNull(documentiStat)) {
            if (CollectionUtils.isNotEmpty(documentiStat.getDatasets())) {

                Dataset dataset1 = documentiStat.getDatasets().get(0);
                Float[][] matrixDataset1 = dataset1.convertToMatrix();

                int matrixDataset1LineSize = matrixDataset1.length;
                int matrixDataset1ColumnSize = matrixDataset1[0].length;

                Float[][] matrixFinal = new Float[matrixDataset1LineSize][(matrixDataset1ColumnSize * 2) - 1];

                int linhas = matrixDataset1LineSize;
                int columns = (matrixDataset1ColumnSize * 2) - 1;

                for (int column = 0; column < matrixDataset1ColumnSize; column++) {
                    int aux = column * 2;
                    for (int line = 0; line < linhas; ++line) {
                        if (column == 0) {
                            matrixFinal[line][column] = matrixDataset1[line][column];
                        } else {
                            matrixFinal[line][aux] = matrixDataset1[line][column];
                        }
                    }
                }

                matrixFinal = MatrixUtils.fillMatrixEmptyValues(0, matrixFinal);

                for (int column = 0; column < columns; column++) {
                    for (int line = 0; line < linhas; ++line) {
                        if (column % 2 == 0 && column != columns - 1) {
                            float sum = matrixFinal[line][column] + matrixFinal[line][column + 2];
                            float total = sum / 2;
                            matrixFinal[line][column + 1] = total;
                        }
                    }
                }

                List<Cell<Integer, String>> output = dataset1.convertMatrixtToList(matrixFinal, finalColumn, finalLine);

                result = new DocumentiStatBuilder()
                    .withId("document")
                    .withDatasets(newArrayList(new DatasetBuilder().withCells(output).withName("dataset").build())).build();

            }
        }
        return result;
    }

}
