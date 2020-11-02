package guru.springframework.repositories.reactive;

import guru.springframework.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataMongoTest
public class UnitOfMeasureReactiveRepositoryTest {

    @Autowired
    UnitOfMeasureReactiveRepository unitOfMeasureReactiveRepository;

    @Before
    public void setUp() throws Exception {
        unitOfMeasureReactiveRepository.deleteAll().block();
    }

    @Test
    public void testSave(){
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setDescription("MyUOM");
        unitOfMeasureReactiveRepository.save(unitOfMeasure).block();

        Long callsToRepository = unitOfMeasureReactiveRepository.count().block();
        assertEquals(Long.valueOf(1L), callsToRepository);
    }

    @Test
    public void testFindByDescription(){
        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setDescription("MyUOM");
        unitOfMeasureReactiveRepository.save(uom).block();

        UnitOfMeasure uomReturned  = unitOfMeasureReactiveRepository.findByDescription("MyUOM").block();
        assertNotNull(uomReturned);
        assertNotNull(uomReturned.getId());
    }
}