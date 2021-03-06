package com.technogise.internal.todo.repository;

import com.technogise.internal.todo.TodoApplication;
import com.technogise.internal.todo.model.Item;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = TodoApplication.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ItemRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void givenGenericEntityRepository_whenSaveAndRetreiveEntity_thenOK() {
        Item item = itemRepository.save(new Item("test"));
        Optional<Item> foundEntity = itemRepository.findById(item.getId());

        assertTrue(foundEntity.isPresent());
        assertNotNull(foundEntity);
        assertEquals(item.getDescription(), foundEntity.get().getDescription());
    }
}