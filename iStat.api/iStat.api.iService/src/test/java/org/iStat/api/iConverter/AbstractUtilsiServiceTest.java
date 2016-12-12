package org.iStat.api.iConverter;

import static com.natpryce.makeiteasy.MakeItEasy.a;
import static com.natpryce.makeiteasy.MakeItEasy.make;
import static com.natpryce.makeiteasy.MakeItEasy.with;
import static org.iStat.api.iEntity.makeit.CellMakeIt._cell;
import static org.iStat.api.iEntity.makeit.CellMakeIt.column;
import static org.iStat.api.iEntity.makeit.CellMakeIt.line;
import static org.iStat.api.iEntity.makeit.CellMakeIt.value;
import static org.iStat.api.iEntity.makeit.DatasetMakeIt._dataset;
import static org.iStat.api.iEntity.makeit.DatasetMakeIt.cells;
import static org.iStat.api.iEntity.makeit.DatasetMakeIt.name;
import static org.iStat.api.iEntity.makeit.DocumentiStatMakeIt._documentiStat;
import static org.iStat.api.iEntity.makeit.DocumentiStatMakeIt.datasets;
import static org.iStat.api.iEntity.makeit.DocumentiStatMakeIt.id;

import java.util.List;

import org.iStat.api.iEntity.Cell;
import org.iStat.api.iEntity.Dataset;
import org.iStat.api.iEntity.DocumentiStat;

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
