package org.iStat.api.iManage;

import org.iStat.api.iEntity.DocumentIStatEntity;
import org.iStat.api.iRepository.DatasetRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class ManageDocumentiStat {

	private final Logger LOGGER = LoggerFactory.getLogger(ManageDocumentiStat.class);

	private DatasetRepository datasetRepository;

	public Boolean saveDataset(DocumentIStatEntity input) {

		DocumentIStatEntity insertedDocument = null;
		Boolean result = false;

		try {
			insertedDocument = datasetRepository.save(input);
			if (insertedDocument.equals(input)) {
				result = true;
			}
			return result;
		} catch (Exception ex) {
			LOGGER.error("'operation=saveDataset', 'message=Catch exception when execute the save dataset!'", ex);
			return result;
		}
	}

	public DocumentIStatEntity findDocumentByName(String name) {
		DocumentIStatEntity foundDocument = null;
		try {
			foundDocument = datasetRepository.findByName(name);
			return foundDocument;
		} catch (Exception ex) {
			LOGGER.error("'operation=findDocumentByName', 'message=Catch exception when execute the find dataset!'", ex);
			return foundDocument;
		}
	}

	@Autowired
	public void setDatasetRepository(DatasetRepository repository) {
		this.datasetRepository = repository;
	}

}
