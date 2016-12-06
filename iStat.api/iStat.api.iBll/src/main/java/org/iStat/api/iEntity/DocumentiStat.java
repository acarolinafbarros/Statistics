package org.iStat.api.iEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DocumentiStat {

    private List<Dataset> datasets;

    private Float scalar;
    
    private DocumentiStat(DocumentiStatBuilder builder) {
        this.datasets = builder.datasets;
        this.scalar = builder.scalar;
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

    public static class DocumentiStatBuilder {

        private List<Dataset> datasets;

        private Float scalar;
        
        public DocumentiStatBuilder withDatasets(List<Dataset> datasets) {
            this.datasets = datasets;
            return this;
        }

        public DocumentiStatBuilder withScalar(Float scalar){
            this.scalar = scalar;
            return this;
        }
        
        public DocumentiStat build() {
            return new DocumentiStat(this);
        }

    }

}
