package org.iStat.api.iLogic;

import static com.natpryce.makeiteasy.MakeItEasy.a;
import static com.natpryce.makeiteasy.MakeItEasy.make;
import static com.natpryce.makeiteasy.MakeItEasy.with;
import static org.iStat.api.iDomain.makeit.CellMakeIt._cell;
import static org.iStat.api.iDomain.makeit.CellMakeIt.column;
import static org.iStat.api.iDomain.makeit.CellMakeIt.line;
import static org.iStat.api.iDomain.makeit.CellMakeIt.value;
import static org.iStat.api.iDomain.makeit.DatasetMakeIt._dataset;
import static org.iStat.api.iDomain.makeit.DatasetMakeIt.cells;
import static org.iStat.api.iDomain.makeit.DatasetMakeIt.name;
import static org.iStat.api.iDomain.makeit.DocumentiStatMakeIt._documentiStat;
import static org.iStat.api.iDomain.makeit.DocumentiStatMakeIt.datasets;

import java.util.List;
import java.util.function.Predicate;

import org.iStat.api.iDomain.Cell;
import org.iStat.api.iDomain.Dataset;
import org.iStat.api.iDomain.DocumentiStat;

public class AbstractUtilsiLogicTest {

    protected Cell<Integer, String> makeCell(Integer iLine, String iColumn, Float iValue) {
        return make(a(_cell, with(line, iLine), with(column, iColumn),
                with(value, Float.valueOf(iValue))));
    }

    protected Dataset makeDataset(String iName, List<Cell<Integer, String>> iCells) {
        return make(
                a(_dataset, with(name, iName), with(cells, iCells)));
    }

    protected DocumentiStat makeDocumentiStat(List<Dataset> iDatasets) {
        return make(a(_documentiStat, with(datasets, iDatasets)));
    }

    protected Predicate<Dataset> assertHasCell(Integer line, String column, Float value) {
        return new Predicate<Dataset>() {

            @Override
            public boolean test(Dataset dataset) {
                return dataset.getCells().stream().anyMatch(
                        l -> l.getLine().equals(Integer.valueOf(line))
                                && l.getColumn().equals(column)
                                && l.getValue() != null
                                && l.getValue().equals(value));
            }
        };
    }

}
