package com.example.demo.controller;

import com.example.demo.models.DetalleMenu;
import com.example.demo.service.DetalleMenuService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detalle-menu")
public class DetalleMenuController {

    private final DetalleMenuService service;

    public DetalleMenuController(DetalleMenuService service) {
        this.service = service;
    }

    // Crear
    @PostMapping
    public DetalleMenu create(@RequestBody DetalleMenu detalle) {
        return service.save(detalle);
    }

    // Listar todos
    @GetMapping
    public List<DetalleMenu> getAll() {
        return service.findAll();
    }

    // Obtener por ID
    @GetMapping("/{id}")
    public DetalleMenu getById(@PathVariable Long id) {
        return service.findById(id);
    }

    // Actualizar
    @PutMapping("/{id}")
    public DetalleMenu update(@PathVariable Long id, @RequestBody DetalleMenu detalle) {
        return service.update(id, detalle);
    }

    // Eliminar
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    // Obtener detalles por ID de menú
    @GetMapping("/menu/{menuId}")
    public List<DetalleMenu> getByMenuId(@PathVariable Long menuId) {
        return service.findByMenuId(menuId);
    }
}
