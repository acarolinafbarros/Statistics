package org.iStat.api.iRepository;

import org.iStat.api.iModel.DocumentIStatModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DatasetRepository extends MongoRepository<DocumentIStatModel, String> {

    public DocumentIStatModel findByName(String name);

}
