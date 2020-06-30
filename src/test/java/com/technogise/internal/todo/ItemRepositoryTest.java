package com.technogise.internal.todo;

import com.technogise.internal.todo.Model.Item;
import com.technogise.internal.todo.Repository.ItemRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = TodoApplication.class)
public class ItemRepositoryTest {

    @Autowired
    private ItemRepository genericEntityRepository;

    @Test
    public void givenGenericEntityRepository_whenSaveAndRetreiveEntity_thenOK() {
        Item genericEntity = genericEntityRepository
                .save(new Item("test"));
        Optional<Item> foundEntity = genericEntityRepository.findById(genericEntity.getId());

        assertTrue(foundEntity.isPresent());
        assertNotNull(foundEntity);
        assertEquals(genericEntity.getDescription(), foundEntity.get().getDescription());
    }
}