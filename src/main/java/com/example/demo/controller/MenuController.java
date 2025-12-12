package com.example.demo.controller;

import com.example.demo.entity.Menu;
import com.example.demo.service.MenuService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu")
public class MenuController {

    private final MenuService service;

    public MenuController(MenuService service) {
        this.service = service;
    }

    @PostMapping
    public Menu create(@RequestBody Menu menu) {
        return service.save(menu);
    }

    @GetMapping
    public List<Menu> getAll() {
        return service.findAll();
    }
}
