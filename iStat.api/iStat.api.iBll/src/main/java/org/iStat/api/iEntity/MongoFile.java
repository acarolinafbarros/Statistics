package org.iStat.api.iEntity;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class MongoFile {

    private String name;

    private MongoFile(MongoFileBuilder builder) {
        this.name = builder.name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("name", name).build();
    }

    public static class MongoFileBuilder {

        private String name;

        public MongoFileBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public MongoFile build() {
            return new MongoFile(this);
        }

    }

}
