package com.technogise.internal.todo.Controller;

import com.technogise.internal.todo.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoController
{
    @Autowired
    private ItemService itemService;

    @RequestMapping("/")
    public String index() {
        itemService.add("Item 1");
        return "Greetings from Spring Boot!";
    }
}
