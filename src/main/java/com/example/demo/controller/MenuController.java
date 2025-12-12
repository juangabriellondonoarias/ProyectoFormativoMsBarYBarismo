package com.example.demo.controller;

import com.example.demo.models.Menu;
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

    // Crear
    @PostMapping
    public Menu create(@RequestBody Menu menu) {
        return service.save(menu);
    }

    // Obtener todos
    @GetMapping
    public List<Menu> getAll() {
        return service.findAll();
    }

    // Obtener por ID
    @GetMapping("/{id}")
    public Menu getById(@PathVariable Long id) {
        return service.findById(id);
    }

    // Actualizar
    @PutMapping("/{id}")
    public Menu update(@PathVariable Long id, @RequestBody Menu menu) {
        return service.update(id, menu);
    }

    // Eliminar
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
