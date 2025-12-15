package com.example.demo.controller;

import java.util.List; 
import java.util.Map;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.DetalleComanda;
import com.example.demo.service.DetalleComandaService;


@RestController
@RequestMapping("/api/detalle-comanda-bar-y-barismo")
@Tag(name = "Detalles de Comandas", description = "Gestión de los ítems individuales (bebidas) dentro de una comanda de bar y barismo.")
public class DetalleComandaController {

    @Autowired
    private DetalleComandaService detalleComandaService;

    @Operation(summary = "Crear nuevo detalle de comanda")
    @PostMapping
    public ResponseEntity<DetalleComanda> crear(@RequestBody DetalleComanda detalle) {
        DetalleComanda nuevoDetalle = detalleComandaService.save(detalle);
        return ResponseEntity.ok(nuevoDetalle);
    }

    @Operation(summary = "Obtener todos los detalles")
    @GetMapping
    public List<DetalleComanda> obtener() {
        return detalleComandaService.findAll();
    }

    @Operation(summary = "Obtener detalle por ID")
    @GetMapping("/{id}")
    public ResponseEntity<DetalleComanda> obtener(@PathVariable Integer id) {
        DetalleComanda detalle = detalleComandaService.findById(id);
        if (detalle != null) {
            return ResponseEntity.ok(detalle);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Eliminar detalle por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        detalleComandaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Actualizar cantidad")
    @PatchMapping("/{id}/cantidad")
    public ResponseEntity<DetalleComanda> actualizarCantidad(
            @PathVariable Integer id,
            @RequestBody Map<String, Integer> body) {

        Integer cantidad = body.get("cantidad");

        if (cantidad == null || cantidad < 1) {
            return ResponseEntity.badRequest().build();
        }

        DetalleComanda detalleActualizado =
                detalleComandaService.actualizarCantidad(id, cantidad);

        if (detalleActualizado != null) {
            return ResponseEntity.ok(detalleActualizado);
        }
        return ResponseEntity.notFound().build();
    }
}
