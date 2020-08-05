package com.technogise.internal.todo.service;

import com.technogise.internal.todo.TodoApplication;
import com.technogise.internal.todo.model.Item;
import com.technogise.internal.todo.repository.ItemRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TodoApplication.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ItemServiceTest {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemRepository repository;

    @Test
    public void givenTodoDescription_whenCalledAdd_thenCreateAndReturnTodoJSON() {
        String itemDescription = "item 1";
        Item item = itemService.add(itemDescription);
        assertEquals(itemDescription, item.getDescription());
    }

    @Test
    public void givenNoTodoItems_whenGetAll_thenExpectNoItemsAreReturned() {
        assertEquals(itemService.getAll(), Collections.emptyList());
    }

    @Test
    public void givenTodoItems_whenGetAll_thenExpectAllItemsAreReturned() {
        repository.save(new Item(1L, "Item 1"));
        repository.save(new Item(2L, "Item 2"));
        assertEquals(itemService.getAll().size(), 2);
    }

    @Test
    public void givenTodoItems_whenDelete_thenItemIsDeleted() {
        repository.save(new Item(1L, "Item 1"));
        itemService.deleteItem(1L);
        assertEquals(itemService.getAll().size(), 0);
        assertFalse(repository.findById(1L).isPresent());
    }

    @Test
    public void givenTodoItem_whenUpdate_thenItemDescriptionIsUpdated() {
        repository.save(new Item(1L, "Item 1"));
        String updatedItemDescription = "Updated";
        itemService.updateItem(1L, updatedItemDescription);
        Optional<Item> updatedItem = repository.findById(1L);
        assertTrue(updatedItem.isPresent());
        assertEquals(updatedItem.get().getDescription(), updatedItemDescription);

    }

    @Test
    public void givenTodoItem_whenUpdateWithInCorrectId_thenExpectNothingIsReturned() {
        String originalDescription = "Item 1";
        repository.save(new Item(1L, originalDescription));
        String updatedItemDescription = "Updated";
        Optional<Item> optionalItem = itemService.updateItem(2L, updatedItemDescription);
        assertFalse(optionalItem.isPresent());

        Optional<Item> updatedItem = repository.findById(1L);
        assertTrue(updatedItem.isPresent());
        assertEquals(updatedItem.get().getDescription(), originalDescription);



    }
}
