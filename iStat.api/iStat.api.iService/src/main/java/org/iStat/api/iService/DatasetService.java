package org.iStat.api.iService;

import org.iStat.api.iCommon.converter.Converter;
import org.iStat.api.iCommon.converter.exception.ConvertException;
import org.iStat.api.iDomain.DocumentiStat;
import org.iStat.api.iDomain.DocumentiStat.DocumentiStatBuilder;
import org.iStat.api.iEntity.DocumentIStatEntity;
import org.iStat.api.iManage.ManageDocumentiStat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DatasetService {

    @Autowired
    private Converter<DocumentiStat, DocumentIStatEntity> converterDocumentiStatModel;

    @Autowired
    private Converter<DocumentIStatEntity, DocumentiStat> converterDocumentiStatEntity;

    @Autowired
    private ManageDocumentiStat manageDocumentIStat;

    public Boolean saveDataset(DocumentiStat input) throws ConvertException {
        DocumentIStatEntity document = converterDocumentiStatModel.convert(input);
        return manageDocumentIStat.saveDataset(document);
    }

    public DocumentiStat openDataset(DocumentiStat input) throws ConvertException {
        DocumentIStatEntity result = manageDocumentIStat.findDocumentByName(input.getId());
        if (result != null) {
            return converterDocumentiStatEntity.convert(result);
        } else {
            DocumentiStatBuilder builder = new DocumentiStatBuilder();
            return builder.build();
        }

    }
}
