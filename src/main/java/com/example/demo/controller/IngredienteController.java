package com.example.demo.controller;
import com.example.demo.dto.IngredienteDTO;
import com.example.demo.service.IngredienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ingredientes")
@CrossOrigin(origins = "*")
@Tag(name = "Ingrediente", description = "API para la gestion de Ingredientes")
public class IngredienteController {
	@Autowired
    private IngredienteService servicio;

    @GetMapping
    @Operation(summary = "Obtener todos los Ingredientes", description = "Devuelve una lista de todos los Ingredientes Registrados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de Ingredientes obtenida con éxito"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<List<IngredienteDTO>> listar() {
        return new ResponseEntity<>(servicio.listarTodos(), HttpStatus.OK);
    }

    
    @GetMapping("/{id}")
    @Operation(summary = "Obtener Ingrediente por ID", description = "Devuelve un Ingrediente específico basado en su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ingrediente encontrado"),
            @ApiResponse(responseCode = "404", description = "Ingediente no encontrado")
    })
    public ResponseEntity<IngredienteDTO> obtenerPorId(@PathVariable Integer id) {
        return new ResponseEntity<>(servicio.obtenerPorId(id), HttpStatus.OK);
    }

    
    @PostMapping
    @Operation(summary = "Crear un nuevo Ingrediente", description = "Crea un nuevo Ingrediente con los datos proporcionados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ingrediente creado con éxito"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public ResponseEntity<IngredienteDTO> guardar(@RequestBody IngredienteDTO dto) {
        return new ResponseEntity<>(servicio.guardar(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un Ingrediente", description = "Actualiza los datos de un Ingrediente existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ingrediente actualizado con éxito"),
            @ApiResponse(responseCode = "404", description = "Ingrediente no encontrado")
    })
    public ResponseEntity<IngredienteDTO> actualizar(@PathVariable Integer id, @RequestBody IngredienteDTO dto) {
        return new ResponseEntity<>(servicio.actualizar(id, dto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un Ingrediente", description = "Elimina un Ingrediente basado en su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Ingrediente eliminado con éxito"),
            @ApiResponse(responseCode = "404", description = "Ingrediente no encontrado")
    })
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        servicio.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @PatchMapping("/{id}/stock")
    @Operation(summary = "Actualizar Stock", description = "Actualiza solo la cantidad disponible de un ingrediente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Stock de Ingrediente Atualizado Correctamente."),
            @ApiResponse(responseCode = "404", description = "Stock de Ingrediente no encontrado")
    })
    public ResponseEntity<IngredienteDTO> actualizarStock(
            @PathVariable Integer id, 
            @RequestParam int cantidad) {
        
        IngredienteDTO actualizado = servicio.actualizarStock(id, cantidad);
        return new ResponseEntity<>(actualizado, HttpStatus.OK);
    }
}