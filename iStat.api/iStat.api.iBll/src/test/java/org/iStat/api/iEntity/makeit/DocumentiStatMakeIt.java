package org.iStat.api.iEntity.makeit;

import static com.google.common.collect.Lists.newArrayList;

import java.util.List;

import org.iStat.api.iEntity.Dataset;
import org.iStat.api.iEntity.DocumentiStat;
import org.iStat.api.iEntity.DocumentiStat.DocumentiStatBuilder;

import com.natpryce.makeiteasy.Instantiator;
import com.natpryce.makeiteasy.Property;
import com.natpryce.makeiteasy.PropertyLookup;

public class DocumentiStatMakeIt {

    public static final Property<DocumentiStat, List<Dataset>> datasets = Property
        .newProperty();
    
    public static final Property<DocumentiStat, String> id = Property
            .newProperty();
    
    public static final Property<DocumentiStat, Float> scalar = Property
            .newProperty();

    public static final Instantiator<DocumentiStat> _documentiStat = new Instantiator<DocumentiStat>() {

        @Override
        public DocumentiStat instantiate(PropertyLookup<DocumentiStat> lookup) {

            return new DocumentiStatBuilder()
                .withDatasets(lookup
                    .valueOf(datasets, newArrayList()))
                .withId(lookup.valueOf(id, "documentDefault"))
                .withScalar(lookup.valueOf(scalar, 0.0f))
                .build();

        }
    };

}
