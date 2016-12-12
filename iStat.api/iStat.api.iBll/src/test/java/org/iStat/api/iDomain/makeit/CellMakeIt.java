package org.iStat.api.iDomain.makeit;

import org.apache.commons.lang3.StringUtils;
import org.iStat.api.iDomain.Cell;
import org.iStat.api.iDomain.Cell.CellBuilder;

import com.natpryce.makeiteasy.Instantiator;
import com.natpryce.makeiteasy.Property;
import com.natpryce.makeiteasy.PropertyLookup;

public class CellMakeIt {

    public static final Property<Cell<Integer, String>, String> parentDatasetName = Property
        .newProperty();

    public static final Property<Cell<Integer, String>, Integer> line = Property
        .newProperty();

    public static final Property<Cell<Integer, String>, String> column = Property
        .newProperty();

    public static final Property<Cell<Integer, String>, Float> value = Property
        .newProperty();

    public static final Instantiator<Cell<Integer, String>> _cell = new Instantiator<Cell<Integer, String>>() {

        @Override
        public Cell<Integer, String> instantiate(PropertyLookup<Cell<Integer, String>> lookup) {

            return new CellBuilder<Integer, String>()
                .withLine(lookup.valueOf(line, 1))
                .withColumn(lookup.valueOf(column, StringUtils.EMPTY))
                .withValue(lookup.valueOf(value, 0.0f))
                .withParentDatasetName(StringUtils.EMPTY).build();

        }
    };

}
