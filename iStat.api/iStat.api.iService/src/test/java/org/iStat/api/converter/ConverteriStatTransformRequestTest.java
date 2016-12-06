package org.iStat.api.converter;

import static com.google.common.collect.Lists.newArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.function.Predicate;

import org.iStat.api.common.converter.Converter;
import org.iStat.api.iConverter.ConverteriStatTransformRequest;
import org.iStat.api.iEntity.Dataset;
import org.iStat.api.iEntity.DocumentiStat;
import org.iStat.api.iStatTransform.request.RequestiStatTransform;
import org.iStat.api.iStatTransform.request.RequestiStatTransformCell;
import org.iStat.api.iStatTransform.request.RequestiStatTransformDataset;
import org.junit.Test;

public class ConverteriStatTransformRequestTest {

    public Converter<RequestiStatTransform, DocumentiStat> converter = new ConverteriStatTransformRequest();

    @Test
    public void shouldConvertRequestiStatTransform() {

        RequestiStatTransform request = createRequest();

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

    private RequestiStatTransform createRequest() {

        RequestiStatTransformCell cell1A = new RequestiStatTransformCell(2, "A",
                Float.valueOf(40.5f));
        RequestiStatTransformCell cell2A = new RequestiStatTransformCell(10,
                "A", Float.valueOf(40.5f));
        RequestiStatTransformCell cell3A = new RequestiStatTransformCell(3, "A",
                Float.valueOf(20.5f));
        RequestiStatTransformCell cell7B = new RequestiStatTransformCell(7, "B",
                Float.valueOf(30.5f));

        RequestiStatTransformDataset dataset1 = new RequestiStatTransformDataset(
                "dataset_1",
                newArrayList(cell1A, cell2A, cell3A, cell7B));

        RequestiStatTransformDataset dataset2 = new RequestiStatTransformDataset(
                "dataset_2", newArrayList(cell1A, cell3A));

        return new RequestiStatTransform(newArrayList(dataset1, dataset2));

    }

}
