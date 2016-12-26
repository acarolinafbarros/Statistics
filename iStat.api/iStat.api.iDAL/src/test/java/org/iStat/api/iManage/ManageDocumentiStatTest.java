package org.iStat.api.iManage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import org.iStat.api.iEntity.CellEntity;
import org.iStat.api.iEntity.DatasetEntity;
import org.iStat.api.iEntity.DocumentIStatEntity;
import org.iStat.api.iManage.ManageDocumentiStat;
import org.iStat.api.iRepository.DatasetRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.google.common.collect.Lists;

public class ManageDocumentiStatTest {

	@Mock
	private DatasetRepository repository;

	private ManageDocumentiStat manageDocumentiStat = new ManageDocumentiStat();

	@Before
	public void setupMethod() {
		MockitoAnnotations.initMocks(this);
		manageDocumentiStat.setDatasetRepository(repository);
	}

	@Test
	public void shoudSuccessSaveData() {

		CellEntity cell1 = new CellEntity("teste dataset", 1, "B", new Float(2));
		DatasetEntity dataset = new DatasetEntity("teste dataset", Lists.newArrayList(cell1));
		DatasetEntity dataset2 = new DatasetEntity("teste dataset2", Lists.newArrayList(cell1));
		DocumentIStatEntity document = new DocumentIStatEntity("teste document", Lists.newArrayList(dataset, dataset2));

		when(repository.save((DocumentIStatEntity) any())).thenReturn(document);

		assertTrue(manageDocumentiStat.saveDataset(document));

	}

	@Test
	public void shoudSuccessFindData() {

		CellEntity cell1 = new CellEntity("teste dataset", 1, "B", new Float(2));
		DatasetEntity dataset = new DatasetEntity("teste dataset", Lists.newArrayList(cell1));
		DatasetEntity dataset2 = new DatasetEntity("teste dataset2", Lists.newArrayList(cell1));
		DocumentIStatEntity document = new DocumentIStatEntity("teste document", Lists.newArrayList(dataset, dataset2));

		when(repository.findByName(anyString())).thenReturn(document);

		assertEquals(document, manageDocumentiStat.findDocumentByName("teste dataset"));

	}

	@Test
	public void shoudInsuccessSaveData() {

		CellEntity cell1 = new CellEntity("teste dataset", 1, "B", new Float(2));
		DatasetEntity dataset = new DatasetEntity("teste dataset", Lists.newArrayList(cell1));
		DatasetEntity dataset2 = new DatasetEntity("teste dataset2", Lists.newArrayList(cell1));
		DocumentIStatEntity document = new DocumentIStatEntity("teste document", Lists.newArrayList(dataset, dataset2));

		when(repository.save((DocumentIStatEntity) any())).thenReturn(null);

		assertFalse(manageDocumentiStat.saveDataset(document));

	}

	@Test
	public void shoudFailBecauseNullValues() {

		assertFalse(manageDocumentiStat.saveDataset(null));
		assertNull(manageDocumentiStat.findDocumentByName(null));

	}

}
