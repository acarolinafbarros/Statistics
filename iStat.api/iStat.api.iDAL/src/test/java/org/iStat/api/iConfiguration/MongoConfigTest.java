package org.iStat.api.iConfiguration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.MongoClient;

@Configuration
@EnableMongoRepositories(basePackages = "org.iStat.api.iRepository")
public class MongoConfigTest extends AbstractMongoConfiguration {

    @Override
    protected String getDatabaseName() {
        return "iStat";
    }

    @Override
    public MongoClient mongo() throws Exception {
        return new MongoClient("localhost:9090");
    }

    @Override
    protected String getMappingBasePackage() {
        return "org.iStat.api.iModel";
    }

}
