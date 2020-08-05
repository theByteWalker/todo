package com.technogise.internal.todo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.technogise.internal.todo.model.Item;
import com.technogise.internal.todo.service.ItemService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@WebMvcTest(TodoController.class)
public class TodoControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private ItemService service;

    @Test
    public void givenTodoDescription_whenPost_thenCreateAndReturnTodoJSON()
            throws Exception {
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

    @Test
    public void givenTodoItems_whenGet_thenAllItemsAreReturned() throws Exception {
        List<Item> itemList = Collections.singletonList(new Item(1L, "Test Todo Item"));
        String itemsString = new ObjectMapper().writeValueAsString(itemList);

        when(service.getAll()).thenReturn(itemList);

        mvc.perform(get("/api/item"))
                .andExpect(status().isOk())
                .andExpect(content().string(itemsString));
    }

    @Test
    public void givenNoTodoItems_whenGet_thenNoItemsAreReturned() throws Exception {
        List<Item> itemList = Collections.emptyList();
        String itemsString = new ObjectMapper().writeValueAsString(itemList);

        when(service.getAll()).thenReturn(itemList);

        mvc.perform(get("/api/item"))
                .andExpect(status().isOk())
                .andExpect(content().string(itemsString));
    }

    @Test
    public void givenTodoItem_whenDelete_thenExpectStatusOk() throws Exception {

        when(service.deleteItem(1L)).thenReturn(true);

        mvc.perform(delete("/api/item/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void givenNoTodoItem_whenDelete_thenExpectBadRequest() throws Exception {

        when(service.deleteItem(1L)).thenReturn(false);

        mvc.perform(delete("/api/item/1"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Unable to delete Specified Item"));
    }

    @Test
    public void giveAnItem_whenUpdate_expectDescriptionIsUpdated() throws Exception {
        String updatedDescription = "Updated Description";
        when(service.updateItem(1L, updatedDescription))
                .thenReturn(Optional.of(new Item(1L, updatedDescription)));

        mvc.perform(put("/api/item/1")
                .content("{\"description\": \"" + "Updated Description" + "\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.description").value(updatedDescription));
    }

    @Test
    public void giveNoItem_whenUpdate_expectBadRequest() throws Exception {
        when(service.updateItem(1L, "Updated Description"))
                .thenReturn(Optional.empty());

        mvc.perform(put("/api/item/1")
                .content("{\"description\": \"" + "Updated Description" + "\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Todo Item does not exist"));
    }

}
