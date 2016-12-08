package iStat.api.iDAL;

import java.util.ArrayList;
import java.util.List;

import org.iStat.api.iConfiguration.MongoConfigTest;
import org.iStat.api.iModel.CellModel;
import org.iStat.api.iModel.DatasetModel;
import org.iStat.api.iModel.DocumentIStatModel;
import org.iStat.api.iRepository.DatasetRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MongoConfigTest.class)
public class DatasetTest {
    
    @Autowired
    private DatasetRepository repository;
    
    @Test
    public void testSaveDataset(){
        CellModel cell1 = new CellModel("teste dataset", 1, "B", new Float(2));
        List<CellModel> cells = new ArrayList<>();
        cells.add(cell1);
        DatasetModel dataset = new DatasetModel("teste dataset", cells);

        DatasetModel dataset2 = new DatasetModel("teste dataset2", cells);
        List<DatasetModel> datasets = new ArrayList<>();
        datasets.add(dataset);
        datasets.add(dataset2);
        DocumentIStatModel document = new DocumentIStatModel("teste document",datasets);
        
        DocumentIStatModel returned = repository.save(document);
    }

}
