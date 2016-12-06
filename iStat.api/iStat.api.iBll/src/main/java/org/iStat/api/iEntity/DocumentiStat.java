package org.iStat.api.iEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class DocumentiStat {

    private List<Dataset> datasets;

    private DocumentiStat(DocumentiStatBuilder builder) {
        this.datasets = builder.datasets;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("datasets", datasets).build();
    }

    public static class DocumentiStatBuilder {

        private List<Dataset> datasets;

        public DocumentiStatBuilder withDatasets(List<Dataset> datasets) {
            this.datasets = datasets;
            return this;
        }

        public DocumentiStat build() {
            return new DocumentiStat(this);
        }

    }

}
