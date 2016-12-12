package org.iStat.api.iDAL;

import java.util.ArrayList;
import java.util.List;

import org.iStat.api.iConfiguration.MongoConfigTest;
import org.iStat.api.iEntity.CellEntity;
import org.iStat.api.iEntity.DatasetEntity;
import org.iStat.api.iEntity.DocumentIStatEntity;
import org.iStat.api.iRepository.DatasetRepository;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MongoConfigTest.class)
@Ignore
public class DatasetTest {
    
    @Autowired
    private DatasetRepository repository;
    
    @Test
    @Ignore
    public void testSaveDataset(){
        CellEntity cell1 = new CellEntity("teste dataset", 1, "B", new Float(2));
        List<CellEntity> cells = new ArrayList<>();
        cells.add(cell1);
        DatasetEntity dataset = new DatasetEntity("teste dataset", cells);

        DatasetEntity dataset2 = new DatasetEntity("teste dataset2", cells);
        List<DatasetEntity> datasets = new ArrayList<>();
        datasets.add(dataset);
        datasets.add(dataset2);
        DocumentIStatEntity document = new DocumentIStatEntity("teste document",datasets);
        
        DocumentIStatEntity returned = repository.save(document);
    }
    
    @Test
    @Ignore
    public void testgetDataset(){
        String id ="teste document";
        
        DocumentIStatEntity returned = repository.findByName(id);
        System.out.println(returned.toString());
    }

}
