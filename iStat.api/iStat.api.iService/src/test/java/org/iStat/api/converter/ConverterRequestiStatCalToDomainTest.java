package org.iStat.api.converter;

import static com.google.common.collect.Lists.newArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.function.Predicate;

import org.iStat.api.common.converter.Converter;
import org.iStat.api.iConverter.ConverterRequestiStatCalToDomain;
import org.iStat.api.iEntity.Dataset;
import org.iStat.api.iEntity.DocumentiStat;
import org.iStat.api.iResponse.iStatCalc.RequestiStatCalc;
import org.iStat.api.iResponse.iStatCalc.RequestiStatCalcCell;
import org.iStat.api.iResponse.iStatCalc.RequestiStatCalcDataset;
import org.junit.Test;

public class ConverterRequestiStatCalToDomainTest {

    public Converter<RequestiStatCalc, DocumentiStat> converter = new ConverterRequestiStatCalToDomain();

    @Test
    public void shouldConvertRequestiStatCalToDomain() {

        RequestiStatCalc request = createRequest();

        DocumentiStat document = converter.convert(request);

        assertNotNull(document);
        assertEquals(2, document.getDatasets().size());
        assertTrue(document
            .getDatasets().stream()
            .anyMatch(assertHasCell("2", "A", Float
                .valueOf(40.5f))));
        assertTrue(document
            .getDatasets().stream()
            .anyMatch(assertHasCell("10", "A", Float
                .valueOf(40.5f))));
        assertTrue(document
            .getDatasets().stream()
            .anyMatch(assertHasCell("3", "A", Float
                .valueOf(20.5f))));
        assertTrue(document
            .getDatasets().stream()
            .anyMatch(assertHasCell("7", "B", Float
                .valueOf(30.5f))));

    }

    private Predicate<Dataset> assertHasCell(String line, String column, Float value) {
        return new Predicate<Dataset>() {

            @Override
            public boolean test(Dataset dataset) {
                return dataset
                    .getCells().stream()
                    .anyMatch(l -> l
                        .getLine().equals(Integer.valueOf(line))
                            && l.getColumn().equals(column)
                            && l.getValue() != null
                            && l.getValue().equals(value));
            }
        };
    }

    private RequestiStatCalc createRequest() {

        RequestiStatCalcCell cell1A = new RequestiStatCalcCell("2",
                "A", 40.5f);
        RequestiStatCalcCell cell2A = new RequestiStatCalcCell("10",
                "A", 40.5f);
        RequestiStatCalcCell cell3A = new RequestiStatCalcCell("3",
                "A", 20.5f);
        RequestiStatCalcCell cell7B = new RequestiStatCalcCell("7",
                "B", 30.5f);

        RequestiStatCalcDataset dataset1 = new RequestiStatCalcDataset(
                "dataset_1",
                newArrayList(cell1A, cell2A, cell3A, cell7B));

        RequestiStatCalcDataset dataset2 = new RequestiStatCalcDataset(
                "dataset_2", newArrayList(cell1A, cell3A));

        return new RequestiStatCalc(newArrayList(dataset1, dataset2));

    }

}
