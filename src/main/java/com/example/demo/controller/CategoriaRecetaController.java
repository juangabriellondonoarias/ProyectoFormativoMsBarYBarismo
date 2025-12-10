package com.example.demo.controller;

import com.example.demo.dto.CategoriaRecetaDTO;
import com.example.demo.service.CategoriaRecetaService;

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
@RequestMapping("/api/categorias-receta")
@Tag(name = " Categoria recetas", description = "API para la gestión de categoria recetas")
@CrossOrigin(origins = "*")
public class CategoriaRecetaController {

	@Autowired
    private CategoriaRecetaService service;

    @GetMapping
    @Operation(summary = "Obtener todas las categorias recetas", description = "Devuelve una lista de todas las categoria recetas registrados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de categoria recetas obtenida con éxito"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<List<CategoriaRecetaDTO>> listar() {
        return ResponseEntity.ok(service.obtenerTodas());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener categoria recetas por ID", description = "Devuelve una categoria receta específico basado en su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoria receta encontrado"),
            @ApiResponse(responseCode = "404", description = "Categoria receta no encontrado")
    })
    public ResponseEntity<CategoriaRecetaDTO> obtener(@PathVariable Integer id) {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }

    @PostMapping
    @Operation(summary = "Crear una nueva categoria receta", description = "Crea una nueva categoria receta con los datos proporcionados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Categoria receta creado con éxito"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public ResponseEntity<CategoriaRecetaDTO> crear(@Valid @RequestBody CategoriaRecetaDTO dto) {
        return new ResponseEntity<>(service.crear(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una categoria receta", description = "Actualiza los datos de una categoria receta existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoria receta actualizado con éxito"),
            @ApiResponse(responseCode = "404", description = "Categoria receta no encontrado")
    })
    public ResponseEntity<CategoriaRecetaDTO> actualizar(@PathVariable Integer id, @Valid @RequestBody CategoriaRecetaDTO dto) {
        return ResponseEntity.ok(service.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una Categoria receta", description = "Elimina una categoria receta basado en su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Categoria receta eliminado con éxito"),
            @ApiResponse(responseCode = "404", description = "Categoria receta no encontrado")
    })
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
