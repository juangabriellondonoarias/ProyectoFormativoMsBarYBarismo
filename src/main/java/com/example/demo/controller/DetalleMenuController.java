package com.example.demo.controller;

import com.example.demo.entity.DetalleMenu;
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

    @PostMapping
    public DetalleMenu create(@RequestBody DetalleMenu detalle) {
        return service.save(detalle);
    }

    @GetMapping
    public List<DetalleMenu> getAll() {
        return service.findAll();
    }
}
