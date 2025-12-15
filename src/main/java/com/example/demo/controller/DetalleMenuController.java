package com.example.demo.controller;

import com.example.demo.models.DetalleMenu;
import com.example.demo.service.DetalleMenuService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detalle-menu")
@Tag(name = "Detalle del Menú", description = "Gestion de los detalles del menú")
public class DetalleMenuController {

    private final DetalleMenuService service;

    public DetalleMenuController(DetalleMenuService service) {
        this.service = service;
    }

    // Crear
    @Operation(summary = "Crear detalle del menú", description = "Registra un nuevo detalle de menú")
    @ApiResponse(responseCode = "201", description = "Detalle creado correctamente")
    @PostMapping
    public DetalleMenu create(@RequestBody DetalleMenu detalle) {
        return service.save(detalle);
    }

    // Listar todos
    @Operation(summary = "Listar detalles de menú", description = "Devuelve todos los detalles registrados")
    @GetMapping
    public List<DetalleMenu> getAll() {
        return service.findAll();
    }

    // Obtener por ID
    @Operation(summary = "Buscar detalle por ID", description = "Devuelve un detalle según su ID")
    @GetMapping("/{id}")
    public DetalleMenu getById(@PathVariable Long id) {
        return service.findById(id);
    }

    // Actualizar
    @Operation(summary = "Actualizar detalle del menú", description = "Actualiza un detalle por su ID")
    @PutMapping("/{id}")
    public DetalleMenu update(@PathVariable Long id, @RequestBody DetalleMenu detalle) {
        return service.update(id, detalle);
    }

    // Eliminar
    @Operation(summary = "Eliminar detalle del menú", description = "Elimina un detalle por su ID")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    // Obtener detalles por ID de menú
    @Operation(summary = "Listar detalles por menú", description = "Devuelve los detalles asociados a un menú")
    @GetMapping("/menu/{menuId}")
    public List<DetalleMenu> getByMenuId(@PathVariable Long menuId) {
        return service.findByMenuId(menuId);
    }
}
