package com.example.demo.controller;

import com.example.demo.dto.ComandaDTO;
import com.example.demo.service.ComandaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos-cocina")
@Tag(
    name = "Comandas",
    description = "Gestión de comandas del módulo Bar y Barismo"
)
public class ComandaController {

    private final ComandaService service;

    public ComandaController(ComandaService service) {
        this.service = service;
    }

    @Operation(summary = "Crear una nueva comanda")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Comanda creada correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    @PostMapping
    public ResponseEntity<ComandaDTO> crear(
            @RequestBody ComandaDTO dto
    ) {
        return ResponseEntity.ok(service.crear(dto));
    }

    @Operation(summary = "Obtener una comanda por su ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Comanda encontrada"),
        @ApiResponse(responseCode = "404", description = "Comanda no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ComandaDTO> obtener(
            @Parameter(description = "ID de la comanda")
            @PathVariable Integer id
    ) {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }

    @Operation(summary = "Listar todas las comandas")
    @GetMapping
    public ResponseEntity<List<ComandaDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @Operation(summary = "Actualizar una comanda existente")
    @PutMapping("/{id}")
    public ResponseEntity<ComandaDTO> actualizar(
            @Parameter(description = "ID de la comanda")
            @PathVariable Integer id,
            @RequestBody ComandaDTO dto
    ) {
        return ResponseEntity.ok(service.actualizar(id, dto));
    }

    @Operation(summary = "Eliminar una comanda")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Comanda eliminada"),
        @ApiResponse(responseCode = "404", description = "Comanda no encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @Parameter(description = "ID de la comanda")
            @PathVariable Integer id
    ) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
