package org.iStat.api.iService;

import org.iStat.api.common.converter.Converter;
import org.iStat.api.iEntity.DocumentiStat;
import org.iStat.api.iEntity.DocumentiStat.DocumentiStatBuilder;
import org.iStat.api.iManage.ManageDocumentIStat;
import org.iStat.api.iModel.DocumentIStatModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DatasetService {

    private final Logger LOG = LoggerFactory.getLogger(DatasetService.class);
    // @TODO

    @Autowired
    private Converter<DocumentiStat, DocumentIStatModel> converterDocumentiStatModel;

    @Autowired
    private Converter<DocumentIStatModel, DocumentiStat> converterDocumentiStatEntity;

    @Autowired
    private ManageDocumentIStat manageDocumentIStat;

    public Boolean saveDataset(DocumentiStat input) {
        DocumentIStatModel document = converterDocumentiStatModel.convert(input);
        return manageDocumentIStat.saveDataset(document);
    }

    public DocumentiStat openDataset(DocumentiStat input) {
        DocumentIStatModel result = manageDocumentIStat.findDocumentByName(input.getId());
        if (result != null) {
            return converterDocumentiStatEntity.convert(result);
        } else {
            DocumentiStatBuilder builder = new DocumentiStatBuilder();
            return builder.build();
        }

    }
}
