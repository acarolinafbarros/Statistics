package org.iStat.api.iCommon.utils;

import java.util.Objects;

public class MatrixUtils {

    /**
     * Method to fill the null values of matrix with the value given on the
     * input.
     * 
     * @param valueToFill
     * @param matrixToChange
     * @return Float[][]
     */
    public static Float[][] fillMatrixEmptyValues(int valueToFill, Float[][] matrixToChange) {

        if (Objects.nonNull(matrixToChange)) {
            for (int linhas = 0; linhas < matrixToChange.length; linhas++) {
                for (int column = 0; column < matrixToChange[linhas].length; column++) {
                    if (matrixToChange[linhas][column] == null) {
                        matrixToChange[linhas][column] = new Float(valueToFill);
                    }
                }
            }
        }

        return matrixToChange;
    }

}
