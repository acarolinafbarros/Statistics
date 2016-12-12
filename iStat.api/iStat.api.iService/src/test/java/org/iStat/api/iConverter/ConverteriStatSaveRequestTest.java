package org.iStat.api.iConverter;

import static com.google.common.collect.Lists.newArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.function.Predicate;

import org.iStat.api.common.converter.Converter;
import org.iStat.api.iConverter.ConverteriStatSaveRequest;
import org.iStat.api.iEntity.Dataset;
import org.iStat.api.iEntity.DocumentiStat;
import org.iStat.api.iStatIO.request.RequestiStatSave;
import org.iStat.api.iStatIO.request.RequestiStatSaveCell;
import org.iStat.api.iStatIO.request.RequestiStatSaveDataset;
import org.junit.Test;

public class ConverteriStatSaveRequestTest {

    public Converter<RequestiStatSave, DocumentiStat> converter = new ConverteriStatSaveRequest();

    @Test
    public void shouldConverteriStatSaveRequest() {

        RequestiStatSave request = createRequest();

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

    private RequestiStatSave createRequest() {

        RequestiStatSaveCell cell1A = new RequestiStatSaveCell(2, "A",
                Float.valueOf(40.5f));
        RequestiStatSaveCell cell2A = new RequestiStatSaveCell(10,
                "A", Float.valueOf(40.5f));
        RequestiStatSaveCell cell3A = new RequestiStatSaveCell(3, "A",
                Float.valueOf(20.5f));
        RequestiStatSaveCell cell7B = new RequestiStatSaveCell(7, "B",
                Float.valueOf(30.5f));

        RequestiStatSaveDataset dataset1 = new RequestiStatSaveDataset(
                "dataset_1",
                newArrayList(cell1A, cell2A, cell3A, cell7B));

        RequestiStatSaveDataset dataset2 = new RequestiStatSaveDataset(
                "dataset_2", newArrayList(cell1A, cell3A));

        return new RequestiStatSave(newArrayList(dataset1, dataset2), "document1");

    }

}
