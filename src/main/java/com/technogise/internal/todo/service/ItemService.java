package com.technogise.internal.todo.service;

import com.technogise.internal.todo.model.Item;
import com.technogise.internal.todo.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService
{
    @Autowired
    private ItemRepository itemRepository;

    public Item add(String description) {
        Item item = new Item(description);
        return itemRepository.save(item);
    }

    public List<Item> getAll() {
        return itemRepository.findAll();
    }

    public boolean deleteItem(Long id) {
        try {
            itemRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Optional<Item> updateItem(Long id, String description) {
        Optional<Item> originalItem = itemRepository.findById(id);
        if (originalItem.isPresent()) {
            Item updatedItem = new Item(id, description);
            return Optional.of(itemRepository.save(updatedItem));
        }
        return  Optional.empty();
    }
}
