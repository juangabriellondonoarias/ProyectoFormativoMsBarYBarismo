package com.example.demo.controller;

import com.example.demo.dto.DetalleComandaDTO;
import com.example.demo.service.DetalleComandaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos-bar-barismo/{idPedido}/detalles")
@Tag(
    name = "Detalle de Comandas",
    description = "Gestión de bebidas asociadas a una comanda"
)
public class DetalleComandaController {

    private final DetalleComandaService service;

    public DetalleComandaController(DetalleComandaService service) {
        this.service = service;
    }

    @Operation(summary = "Agregar una bebida a una comanda")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Detalle creado correctamente"),
        @ApiResponse(responseCode = "404", description = "Comanda no encontrada")
    })
    @PostMapping
    public ResponseEntity<DetalleComandaDTO> crear(
            @Parameter(description = "ID de la comanda")
            @PathVariable Integer idPedido,
            @RequestBody DetalleComandaDTO dto
    ) {
        return ResponseEntity.ok(service.crear(idPedido, dto));
    }

    @Operation(summary = "Listar las bebidas de una comanda")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Lista de detalles"),
        @ApiResponse(responseCode = "404", description = "Comanda no encontrada")
    })
    @GetMapping
    public ResponseEntity<List<DetalleComandaDTO>> listar(
            @Parameter(description = "ID de la comanda")
            @PathVariable Integer idPedido
    ) {
        return ResponseEntity.ok(service.listarPorPedido(idPedido));
    }

    @Operation(summary = "Eliminar una bebida de la comanda")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Detalle eliminado"),
        @ApiResponse(responseCode = "404", description = "Detalle no encontrado")
    })
    @DeleteMapping("/{idDetalle}")
    public ResponseEntity<Void> eliminar(
            @Parameter(description = "ID del detalle de la comanda")
            @PathVariable Integer idDetalle
    ) {
        service.eliminar(idDetalle);
        return ResponseEntity.noContent().build();
    }
}
