package org.iStat.api.iConverter;

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
import static org.iStat.api.iDomain.makeit.DocumentiStatMakeIt.id;

import java.util.List;

import org.iStat.api.iDomain.Cell;
import org.iStat.api.iDomain.Dataset;
import org.iStat.api.iDomain.DocumentiStat;

public class AbstractUtilsiServiceTest {

    protected Cell<Integer, String> makeCell(Integer iLine, String iColumn, Float iValue) {
        return make(a(_cell, with(line, iLine), with(column, iColumn),
                with(value, Float.valueOf(iValue))));
    }

    protected Dataset makeDataset(String iName, List<Cell<Integer, String>> iCells) {
        return make(
                a(_dataset, with(name, iName), with(cells, iCells)));
    }

    protected DocumentiStat makeDocumentiStat(String fileName) {
        return make(a(_documentiStat, with(id, fileName)));
    }

    protected DocumentiStat makeDocumentiStat(List<Dataset> iDatasets) {
        return make(a(_documentiStat, with(datasets, iDatasets)));
    }

    protected DocumentiStat makeDocumentiStat(List<Dataset> iDatasets, String fileName) {
        return make(a(_documentiStat, with(datasets, iDatasets),
                with(id, fileName)));
    }

}
