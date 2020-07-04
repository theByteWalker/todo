package com.technogise.internal.todo.service;

import com.technogise.internal.todo.TodoApplication;
import com.technogise.internal.todo.model.Item;
import com.technogise.internal.todo.service.ItemService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TodoApplication.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ItemServiceTest
{

    @Autowired
    private ItemService itemService;

    @Test
    public void givenTodoDescription_whenCalledAdd_thenCreateAndReturnTodoJSON()
            throws Exception
    {
        String itemDescription = "item 1";
        Item item = itemService.add(itemDescription);
        assertEquals(itemDescription, item.getDescription());
    }
}
