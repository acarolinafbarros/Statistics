package org.iStat.api.iConverter;

import static com.google.common.collect.Lists.newArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.function.Predicate;

import org.iStat.api.iCommon.converter.Converter;
import org.iStat.api.iCommon.converter.exception.ConvertException;
import org.iStat.api.iDomain.Dataset;
import org.iStat.api.iDomain.DocumentiStat;
import org.iStat.api.iRequest.RequestiStat;
import org.iStat.api.iStatCalc.request.RequestiStatCalc;
import org.iStat.api.iStatCalc.request.RequestiStatCalcCell;
import org.iStat.api.iStatCalc.request.RequestiStatCalcDataset;
import org.junit.Test;

public class ConverteriStatCalRequestTest
        extends AbstractUtilsiServiceTest {

    public Converter<RequestiStatCalc, DocumentiStat> converter = new ConverteriStatCalRequest();

    @Test
    public void shouldConvertRequestiStatCalToDomain()
            throws Exception {

        RequestiStatCalc request = (RequestiStatCalc) createRequest();

        DocumentiStat document = converter.convert(request);

        assertNotNull(document);
        assertEquals(2, document.getDatasets().size());
        assertTrue(document.getDatasets().stream().anyMatch(
                assertHasCell(2, "A", Float.valueOf(40.5f))));
        assertTrue(document.getDatasets().stream().anyMatch(
                assertHasCell(10, "A", Float.valueOf(40.5f))));
        assertTrue(document.getDatasets().stream().anyMatch(
                assertHasCell(3, "A", Float.valueOf(20.5f))));
        assertTrue(document.getDatasets().stream().anyMatch(
                assertHasCell(7, "B", Float.valueOf(30.5f))));

    }

    @Test(expected = ConvertException.class)
    public void cantConvertRequestiStatCalToDomainNullCells()
            throws Exception {
        converter
            .convert((RequestiStatCalc) createRequestWithNullCells());
    }

    @Test(expected = ConvertException.class)
    public void cantConvertRequestiStatCalToDomainNullRows()
            throws Exception {
        converter.convert(
                (RequestiStatCalc) createRequestWithRowsNullValues());
    }

    @Test(expected = ConvertException.class)
    public void cantConvertRequestiStatCalToDomainNullColumns()
            throws Exception {
        converter.convert(
                (RequestiStatCalc) createRequestWithColumnsNullValues());
    }

    @Test(expected = ConvertException.class)
    public void cantConvertRequestiStatCalToDomainNullValues()
            throws Exception {
        converter.convert(
                (RequestiStatCalc) createRequestWithNullValues());
    }

    private Predicate<Dataset> assertHasCell(Integer line, String column, Float value) {
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

    private RequestiStat createRequest() {

        RequestiStatCalcCell cell1A = new RequestiStatCalcCell(2, "A",
                Float.valueOf(40.5f));
        RequestiStatCalcCell cell2A = new RequestiStatCalcCell(10,
                "A", Float.valueOf(40.5f));
        RequestiStatCalcCell cell3A = new RequestiStatCalcCell(3, "A",
                Float.valueOf(20.5f));
        RequestiStatCalcCell cell7B = new RequestiStatCalcCell(7, "B",
                Float.valueOf(30.5f));

        RequestiStatCalcDataset dataset1 = new RequestiStatCalcDataset(
                "dataset_1",
                newArrayList(cell1A, cell2A, cell3A, cell7B));

        RequestiStatCalcDataset dataset2 = new RequestiStatCalcDataset(
                "dataset_2", newArrayList(cell1A, cell3A));

        return new RequestiStatCalc(newArrayList(dataset1, dataset2));

    }

    private RequestiStat createRequestWithNullValues() {

        RequestiStatCalcCell cell1A = new RequestiStatCalcCell(2, "A",
                null);
        RequestiStatCalcCell cell2A = new RequestiStatCalcCell(10,
                "A", null);
        RequestiStatCalcCell cell3A = new RequestiStatCalcCell(3, "A",
                null);
        RequestiStatCalcCell cell7B = new RequestiStatCalcCell(7, "B",
                null);

        RequestiStatCalcDataset dataset1 = new RequestiStatCalcDataset(
                "dataset_1",
                newArrayList(cell1A, cell2A, cell3A, cell7B));

        RequestiStatCalcDataset dataset2 = new RequestiStatCalcDataset(
                "dataset_2", newArrayList(cell1A, cell3A));

        return new RequestiStatCalc(newArrayList(dataset1, dataset2));

    }

    private RequestiStat createRequestWithNullCells() {

        RequestiStatCalcDataset dataset1 = new RequestiStatCalcDataset(
                "dataset_1", newArrayList(null, null, null, null));

        return new RequestiStatCalc(newArrayList(dataset1));

    }

    private RequestiStat createRequestWithColumnsNullValues() {

        RequestiStatCalcCell cell1A = new RequestiStatCalcCell(2,
                null, Float.valueOf(40.5f));
        RequestiStatCalcCell cell2A = new RequestiStatCalcCell(10,
                null, Float.valueOf(40.5f));
        RequestiStatCalcCell cell3A = new RequestiStatCalcCell(3,
                null, Float.valueOf(20.5f));
        RequestiStatCalcCell cell7B = new RequestiStatCalcCell(7,
                null, Float.valueOf(30.5f));

        RequestiStatCalcDataset dataset1 = new RequestiStatCalcDataset(
                "dataset_1",
                newArrayList(cell1A, cell2A, cell3A, cell7B));

        RequestiStatCalcDataset dataset2 = new RequestiStatCalcDataset(
                "dataset_2", newArrayList(cell1A, cell3A));

        return new RequestiStatCalc(newArrayList(dataset1, dataset2));

    }

    private RequestiStat createRequestWithRowsNullValues() {

        RequestiStatCalcCell cell1A = new RequestiStatCalcCell(null,
                "A", Float.valueOf(40.5f));
        RequestiStatCalcCell cell2A = new RequestiStatCalcCell(null,
                "A", Float.valueOf(40.5f));
        RequestiStatCalcCell cell3A = new RequestiStatCalcCell(null,
                "A", Float.valueOf(20.5f));
        RequestiStatCalcCell cell7B = new RequestiStatCalcCell(null,
                "B", Float.valueOf(30.5f));

        RequestiStatCalcDataset dataset1 = new RequestiStatCalcDataset(
                "dataset_1",
                newArrayList(cell1A, cell2A, cell3A, cell7B));

        RequestiStatCalcDataset dataset2 = new RequestiStatCalcDataset(
                "dataset_2", newArrayList(cell1A, cell3A));

        return new RequestiStatCalc(newArrayList(dataset1, dataset2));

    }

}
