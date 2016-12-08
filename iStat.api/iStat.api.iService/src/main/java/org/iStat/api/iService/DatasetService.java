package org.iStat.api.iService;

import org.iStat.api.iEntity.DocumentiStat;
import org.iStat.api.iManage.ManageDocumentIStat;
import org.iStat.api.iModel.DocumentIStatModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.iStat.api.common.converter.Converter;

@Service
public class DatasetService {

    private final Logger LOG = LoggerFactory.getLogger(DatasetService.class);
    // @TODO

    @Autowired
    private Converter<DocumentiStat, DocumentIStatModel> converterDocumentiStat;

    public Boolean saveDataset(DocumentiStat input) {
        DocumentIStatModel document = converterDocumentiStat.convert(input);
        ManageDocumentIStat manageDocumentIStat = new ManageDocumentIStat();
        return manageDocumentIStat.saveDataset(document);
    }
}
