package org.iStat.api.iEntity.makeit;

import static com.google.common.collect.Lists.newArrayList;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.iStat.api.iEntity.Cell;
import org.iStat.api.iEntity.Dataset;
import org.iStat.api.iEntity.Dataset.DatasetBuilder;

import com.natpryce.makeiteasy.Instantiator;
import com.natpryce.makeiteasy.Property;
import com.natpryce.makeiteasy.PropertyLookup;

public class DatasetMakeIt {

    public static final Property<Dataset, String> name = Property
        .newProperty();

    public static final Property<Dataset, List<Cell<Integer, String>>> cells = Property
        .newProperty();

    public static final Instantiator<Dataset> _dataset = new Instantiator<Dataset>() {

        @Override
        public Dataset instantiate(PropertyLookup<Dataset> lookup) {

            return new DatasetBuilder()
                .withName(lookup.valueOf(name, StringUtils.EMPTY))
                .withCells(lookup.valueOf(cells, newArrayList()))
                .build();

        }
    };

}
