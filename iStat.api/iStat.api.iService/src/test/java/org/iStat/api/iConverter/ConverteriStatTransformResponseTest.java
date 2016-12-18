package org.iStat.api.iConverter;

import static com.google.common.collect.Lists.newArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.function.Predicate;

import org.iStat.api.iCommon.converter.Converter;
import org.iStat.api.iConverter.ConverteriStatTransformResponse;
import org.iStat.api.iDomain.Cell;
import org.iStat.api.iDomain.Dataset;
import org.iStat.api.iDomain.DocumentiStat;
import org.iStat.api.iStatTransform.response.ResponseiStatTransform;
import org.iStat.api.iStatTransform.response.ResponseiStatTransformDataset;
import org.junit.Test;

public class ConverteriStatTransformResponseTest
        extends AbstractUtilsiServiceTest {

    public Converter<DocumentiStat, ResponseiStatTransform> converter = new ConverteriStatTransformResponse();

    @Test
    public void shouldConvertRequestiStatCalToDomain() throws Exception{

        DocumentiStat request = createDomainResponse();

        ResponseiStatTransform document = converter.convert(request);

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

    private Predicate<ResponseiStatTransformDataset> assertHasCell(Integer line, String column, Float value) {
        return new Predicate<ResponseiStatTransformDataset>() {

            @Override
            public boolean test(ResponseiStatTransformDataset dataset) {
                return dataset.getCells().stream().anyMatch(
                        l -> l.getLine().equals(Integer.valueOf(line))
                                && l.getColumn().equals(column)
                                && l.getValue() != null
                                && l.getValue().equals(value));
            }
        };
    }

    private DocumentiStat createDomainResponse() {

        Cell<Integer, String> cell1A = makeCell(2, "A",
                Float.valueOf(40.5f));
        Cell<Integer, String> cell10A = makeCell(10, "A",
                Float.valueOf(40.5f));
        Cell<Integer, String> cell3A = makeCell(3, "A",
                Float.valueOf(20.5f));
        Cell<Integer, String> cell7B = makeCell(7, "B",
                Float.valueOf(30.5f));

        Dataset dataset1 = makeDataset("dataset_1",
                newArrayList(cell1A, cell10A, cell3A, cell7B));

        Dataset dataset2 = makeDataset("dataset_2",
                newArrayList(cell1A, cell3A));

        return makeDocumentiStat(newArrayList(dataset1, dataset2));
    }

}
