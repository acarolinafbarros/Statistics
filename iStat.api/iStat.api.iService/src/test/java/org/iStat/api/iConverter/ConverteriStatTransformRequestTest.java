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
import org.iStat.api.iStatTransform.request.RequestiStatTransform;
import org.iStat.api.iStatTransform.request.RequestiStatTransformCell;
import org.iStat.api.iStatTransform.request.RequestiStatTransformDataset;
import org.junit.Test;

public class ConverteriStatTransformRequestTest
        extends AbstractUtilsiServiceTest {

    public Converter<RequestiStatTransform, DocumentiStat> converter = new ConverteriStatTransformRequest();

    @Test
    public void shouldConvertRequestiStatTransform()
            throws Exception {

        RequestiStatTransform request = (RequestiStatTransform) createRequest();

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

    @Test(expected = ConvertException.class)
    public void cantConvertRequestiStatCalToDomainNullCells()
            throws Exception {
        converter.convert(
                (RequestiStatTransform) createRequestWithNullCells());
    }

    @Test(expected = ConvertException.class)
    public void cantConvertRequestiStatCalToDomainNullRows()
            throws Exception {
        converter.convert(
                (RequestiStatTransform) createRequestWithRowsNullValues());
    }

    @Test(expected = ConvertException.class)
    public void cantConvertRequestiStatCalToDomainNullColumns()
            throws Exception {
        converter.convert(
                (RequestiStatTransform) createRequestWithColumnsNullValues());
    }

    @Test(expected = ConvertException.class)
    public void cantConvertRequestiStatCalToDomainNullValues()
            throws Exception {
        converter.convert(
                (RequestiStatTransform) createRequestWithNullValues());
    }

    private RequestiStat createRequest() {

        RequestiStatTransformCell cell1A = new RequestiStatTransformCell(
                2, "A", Float.valueOf(40.5f));
        RequestiStatTransformCell cell2A = new RequestiStatTransformCell(
                10, "A", Float.valueOf(40.5f));
        RequestiStatTransformCell cell3A = new RequestiStatTransformCell(
                3, "A", Float.valueOf(20.5f));
        RequestiStatTransformCell cell7B = new RequestiStatTransformCell(
                7, "B", Float.valueOf(30.5f));

        RequestiStatTransformDataset dataset1 = new RequestiStatTransformDataset(
                "dataset_1",
                newArrayList(cell1A, cell2A, cell3A, cell7B));

        RequestiStatTransformDataset dataset2 = new RequestiStatTransformDataset(
                "dataset_2", newArrayList(cell1A, cell3A));

        return new RequestiStatTransform(
                newArrayList(dataset1, dataset2));

    }

    private RequestiStat createRequestWithNullValues() {

        RequestiStatTransformCell cell1A = new RequestiStatTransformCell(
                2, "A", null);
        RequestiStatTransformCell cell2A = new RequestiStatTransformCell(
                10, "A", null);
        RequestiStatTransformCell cell3A = new RequestiStatTransformCell(
                3, "A", null);
        RequestiStatTransformCell cell7B = new RequestiStatTransformCell(
                7, "B", null);

        RequestiStatTransformDataset dataset1 = new RequestiStatTransformDataset(
                "dataset_1",
                newArrayList(cell1A, cell2A, cell3A, cell7B));

        RequestiStatTransformDataset dataset2 = new RequestiStatTransformDataset(
                "dataset_2", newArrayList(cell1A, cell3A));

        return new RequestiStatTransform(
                newArrayList(dataset1, dataset2));

    }

    private RequestiStat createRequestWithNullCells() {

        RequestiStatTransformDataset dataset1 = new RequestiStatTransformDataset(
                "dataset_1", newArrayList(null, null, null, null));

        return new RequestiStatTransform(newArrayList(dataset1));

    }

    private RequestiStat createRequestWithColumnsNullValues() {

        RequestiStatTransformCell cell1A = new RequestiStatTransformCell(
                2, null, Float.valueOf(40.5f));
        RequestiStatTransformCell cell2A = new RequestiStatTransformCell(
                10, null, Float.valueOf(40.5f));
        RequestiStatTransformCell cell3A = new RequestiStatTransformCell(
                3, null, Float.valueOf(20.5f));
        RequestiStatTransformCell cell7B = new RequestiStatTransformCell(
                7, null, Float.valueOf(30.5f));

        RequestiStatTransformDataset dataset1 = new RequestiStatTransformDataset(
                "dataset_1",
                newArrayList(cell1A, cell2A, cell3A, cell7B));

        RequestiStatTransformDataset dataset2 = new RequestiStatTransformDataset(
                "dataset_2", newArrayList(cell1A, cell3A));

        return new RequestiStatTransform(
                newArrayList(dataset1, dataset2));

    }

    private RequestiStat createRequestWithRowsNullValues() {

        RequestiStatTransformCell cell1A = new RequestiStatTransformCell(
                null, "A", Float.valueOf(40.5f));
        RequestiStatTransformCell cell2A = new RequestiStatTransformCell(
                null, "A", Float.valueOf(40.5f));
        RequestiStatTransformCell cell3A = new RequestiStatTransformCell(
                null, "A", Float.valueOf(20.5f));
        RequestiStatTransformCell cell7B = new RequestiStatTransformCell(
                null, "B", Float.valueOf(30.5f));

        RequestiStatTransformDataset dataset1 = new RequestiStatTransformDataset(
                "dataset_1",
                newArrayList(cell1A, cell2A, cell3A, cell7B));

        RequestiStatTransformDataset dataset2 = new RequestiStatTransformDataset(
                "dataset_2", newArrayList(cell1A, cell3A));

        return new RequestiStatTransform(
                newArrayList(dataset1, dataset2));

    }

}
