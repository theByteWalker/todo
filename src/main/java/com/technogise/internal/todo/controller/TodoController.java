package com.technogise.internal.todo.controller;

import com.technogise.internal.todo.model.Item;
import com.technogise.internal.todo.request.CreateItemRequest;
import com.technogise.internal.todo.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoController
{
    @Autowired
    private ItemService itemService;

    @PostMapping("/api/item")
    public Item create(@RequestBody CreateItemRequest createItemRequest) {
        return itemService.add(createItemRequest.getDescription());
    }
}
