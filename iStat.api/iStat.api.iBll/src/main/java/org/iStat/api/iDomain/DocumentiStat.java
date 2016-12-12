package org.iStat.api.iDomain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class DocumentiStat {

    private String id;

    private List<Dataset> datasets;

    private Float scalar;

    private DocumentiStat(DocumentiStatBuilder builder) {
        this.datasets = builder.datasets;
        this.scalar = builder.scalar;
        this.id = builder.id;
    }

    public List<Dataset> getDatasets() {
        if (Objects.isNull(this.datasets)) {
            this.datasets = new ArrayList<>();
        }
        return datasets;
    }

    public void setDatasets(List<Dataset> datasets) {
        this.datasets = datasets;
    }

    public Float getScalar() {
        return scalar;
    }

    public void setScalar(Float scalar) {
        this.scalar = scalar;
    }
    
    public String getId() {
        return id;
    }

    
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("id", id).append("scalar", scalar)
            .append("datasets", datasets).build();
    }

    public static class DocumentiStatBuilder {

        private List<Dataset> datasets;

        private Float scalar;

        private String id;

        public DocumentiStatBuilder withDatasets(List<Dataset> datasets) {
            this.datasets = datasets;
            return this;
        }

        public DocumentiStatBuilder withScalar(Float scalar) {
            this.scalar = scalar;
            return this;
        }

        public DocumentiStatBuilder withId(String id) {
            this.id = id;
            return this;
        }

        public DocumentiStat build() {
            return new DocumentiStat(this);
        }

    }

}
