package com.example.demo.controller;

import com.example.demo.models.Menu;
import com.example.demo.service.MenuService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu")
@Tag(name = "Menú", description = "Controlador para la gestión del menú")
public class MenuController {

    private final MenuService service;

    public MenuController(MenuService service) {
        this.service = service;
    }

    // Crear
    @Operation(
        summary = "Crear menú",
        description = "Registra un nuevo menú"
    )
    @ApiResponse(responseCode = "201", description = "Menú creado correctamente")
    @PostMapping
    public Menu create(@RequestBody Menu menu) {
        return service.save(menu);
    }

    // Obtener todos
    @Operation(
        summary = "Listar menús",
        description = "Devuelve todos los menús registrados"
    )
    @GetMapping
    public List<Menu> getAll() {
        return service.findAll();
    }

    // Obtener por ID
    @Operation(
        summary = "Buscar menú por ID",
        description = "Devuelve un menú según su ID"
    )
    @GetMapping("/{id}")
    public Menu getById(@PathVariable Long id) {
        return service.findById(id);
    }

    // Actualizar
    @Operation(
        summary = "Actualizar menú",
        description = "Actualiza un menú existente por su ID"
    )
    @PutMapping("/{id}")
    public Menu update(@PathVariable Long id, @RequestBody Menu menu) {
        return service.update(id, menu);
    }

    // Eliminar
    @Operation(
        summary = "Eliminar menú",
        description = "Elimina un menú por su ID"
    )
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
