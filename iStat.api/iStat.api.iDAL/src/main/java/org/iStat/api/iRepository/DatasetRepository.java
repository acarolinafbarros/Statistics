package org.iStat.api.iRepository;

import org.iStat.api.iEntity.DocumentIStatEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DatasetRepository extends MongoRepository<DocumentIStatEntity, String> {

    public DocumentIStatEntity findByName(String name);

}
