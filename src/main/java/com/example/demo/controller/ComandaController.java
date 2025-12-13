package com.example.demo.controller;

import com.example.demo.dto.ComandaDTO;
import com.example.demo.service.ComandaService;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/pedidos-cocina")
public class ComandaController {

    private final ComandaService service;

    public ComandaController(ComandaService service) {
        this.service = service;
    }


    @PostMapping
    public ResponseEntity<ComandaDTO> crear(@RequestBody ComandaDTO dto) {
        return ResponseEntity.ok(service.crear(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComandaDTO> obtener(@PathVariable Integer id) {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<ComandaDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ComandaDTO> actualizar(
            @PathVariable Integer id,
            @RequestBody ComandaDTO dto
    ) {
        return ResponseEntity.ok(service.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
