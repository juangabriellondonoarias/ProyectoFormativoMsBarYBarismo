package com.example.demo.controller;

import com.example.demo.dto.IngredienteDTO;
import com.example.demo.service.IngredienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ingredientes")
@Tag(name = "Ingredientes" , description = "API para la gestion de ingredientes")
@CrossOrigin(origins = "*") // Permite peticiones desde Angular/React local

public class IngredienteController {

	@Autowired
    private IngredienteService service;

    @GetMapping
    @Operation(summary = "Obtener todos los Ingredientes", description = "Devuelve una lista de todos los Ingredientes registrados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de Ingredientes obtenida con éxito"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<List<IngredienteDTO>> listar() {
        return ResponseEntity.ok(service.obtenerTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener ingrediente por ID", description = "Devuelve un ingredinte específico basado en su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ingrediente encontrado"),
            @ApiResponse(responseCode = "404", description = "Ingrediente no encontrado")
    })
    public ResponseEntity<IngredienteDTO> obtener(@PathVariable Integer id) {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo ingredinte", description = "Crea un nuevo ingrediente con los datos proporcionados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "ingrediente creado con éxito"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public ResponseEntity<IngredienteDTO> crear(@Valid @RequestBody IngredienteDTO dto) {
        // Retorna 201 Created
        return new ResponseEntity<>(service.crear(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un ingredinte", description = "Actualiza los datos de un ingrediente existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ingrediente actualizado con éxito"),
            @ApiResponse(responseCode = "404", description = "Ingrediente no encontrado")
    })
    public ResponseEntity<IngredienteDTO> actualizar(@PathVariable Integer id, @Valid @RequestBody IngredienteDTO dto) {
        return ResponseEntity.ok(service.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un ingredinte", description = "Elimina un ingredientebasado en su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Ingrediente eliminado con éxito"),
            @ApiResponse(responseCode = "404", description = "INgrediente no encontrado")
    })
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        service.eliminar(id);
        // Retorna 204 No Content (Correcto pero sin cuerpo de respuesta)
        return ResponseEntity.noContent().build();
    }
}
