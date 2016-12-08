package org.iStat.api.iManage;

import org.iStat.api.iModel.DocumentIStatModel;
import org.iStat.api.iRepository.DatasetRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ManageDocumentIStat {

    @Autowired
    private DatasetRepository datasetRepository;

    public Boolean saveDataset(DocumentIStatModel input) {
        DocumentIStatModel insertedDocument = null;
        Boolean result = false;
        try {
            insertedDocument = datasetRepository.save(input);
            if (insertedDocument.equals(input)) {
                result = true;
            }
            return result;
        } catch (Exception e) {
            return result;
        }
    }

    public DocumentIStatModel findDocumentByName(String name) {
        DocumentIStatModel foundDocument = null;
        try {
            foundDocument = datasetRepository.findByName(name);
            return foundDocument;
        } catch (Exception e) {
            return foundDocument;
        }
    }

}
