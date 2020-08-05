package com.technogise.internal.todo.controller;

import com.technogise.internal.todo.model.Item;
import com.technogise.internal.todo.request.CreateItemRequest;
import com.technogise.internal.todo.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TodoController {
    @Autowired
    private ItemService itemService;

    @PostMapping("/api/item")
    public Item create(@RequestBody CreateItemRequest createItemRequest) {
        return itemService.add(createItemRequest.getDescription());
    }

    @GetMapping("/api/item")
    public List<Item> getAllItems() {
        return itemService.getAll();
    }

    @DeleteMapping("/api/item/{id}")
    public ResponseEntity deleteItem(@PathVariable Long id) {
        if (itemService.deleteItem(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().body("Unable to delete Specified Item");
        }
    }

    @PutMapping("/api/item/{id}")
    public ResponseEntity updateItem(@PathVariable Long id, @RequestBody CreateItemRequest itemRequest) {
        Optional<Item> updatedItem = itemService.updateItem(id, itemRequest.getDescription());
        if (updatedItem.isPresent()) {
            return ResponseEntity.ok(updatedItem.get());
        }
        return ResponseEntity.badRequest().body("Todo Item does not exist");
    }
}
