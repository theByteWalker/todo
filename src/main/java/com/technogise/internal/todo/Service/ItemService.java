package com.technogise.internal.todo.Service;

import com.technogise.internal.todo.Model.Item;
import com.technogise.internal.todo.Repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService
{
    @Autowired
    private ItemRepository itemRepository;

    public Item add(String description) {
        Item item = new Item();
        item.setDescription(description);
        return itemRepository.save(item);
    }
}
