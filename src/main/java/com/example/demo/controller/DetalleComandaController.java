package com.example.demo.controller;

import com.example.demo.dto.DetalleComandaDTO;
import com.example.demo.service.DetalleComandaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/pedidos-cocina/{idPedido}/detalles")
public class DetalleComandaController {

    private final DetalleComandaService service;

    public DetalleComandaController(DetalleComandaService service) {
        this.service = service;
    }


    @PostMapping
    public ResponseEntity<DetalleComandaDTO> crear(
            @PathVariable Integer idPedido,
            @RequestBody DetalleComandaDTO dto
    ) {
        return ResponseEntity.ok(service.crear(idPedido, dto));
    }

    @GetMapping
    public ResponseEntity<List<DetalleComandaDTO>> listar(@PathVariable Integer idPedido) {
        return ResponseEntity.ok(service.listarPorPedido(idPedido));
    }

    @DeleteMapping("/{idDetalle}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer idDetalle) {
        service.eliminar(idDetalle);
        return ResponseEntity.noContent().build();
    }
}
