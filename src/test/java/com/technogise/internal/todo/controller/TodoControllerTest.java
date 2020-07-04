package com.technogise.internal.todo.controller;

import com.technogise.internal.todo.TodoApplication;
import com.technogise.internal.todo.model.Item;
import com.technogise.internal.todo.service.ItemService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(TodoController.class)
public class TodoControllerTest
{
    @Autowired
    private MockMvc mvc;

    @MockBean
    private ItemService service;

    @Test
    public void givenTodoDescription_whenPost_thenCreateAndReturnTodoJSON()
            throws Exception
    {
        String itemDescription = "item 1";
        Item mockItem = new Item(1L, itemDescription);

        when(service.add(itemDescription)).thenReturn(mockItem);

        mvc.perform(post("/api/item")
                .content("{\"description\": \"item 1\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.description").value(itemDescription));
    }
}
