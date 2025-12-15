package com.example.demo.controller;

import com.example.demo.dto.CategoriaRecetaDTO;
import com.example.demo.service.CategoriaRecetaService;

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
@RequestMapping("/api/categoriaReceta")
@CrossOrigin(origins = "*") 
@Tag(name = "Categoria Receta", description = "API para la gestion de Categorias de Receta")
public class CategoriaRecetaController {

    @Autowired
    private CategoriaRecetaService servicio;

 
    @GetMapping
    @Operation(summary = "Obtener todas las categorias de receta", description = "Devuelve una lista de todas las categorias de receta Registradas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de las categorias de recetas obtenida con éxito"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<List<CategoriaRecetaDTO>> listar() {
        return new ResponseEntity<>(servicio.listarTodas(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener Categoria Receta por ID", description = "Devuelve una Categoria receta específica basado en su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoria Receta encontrado"),
            @ApiResponse(responseCode = "404", description = "Categoria Receta no encontrada")
    })
    public ResponseEntity<CategoriaRecetaDTO> obtenerPorId(@PathVariable Integer id) {
        return new ResponseEntity<>(servicio.obtenerPorId(id), HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Crear una nueva Categoria Receta", description = "Crea una nueva Categoria Receta con los datos proporcionados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Categoria Receta creado con éxito"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public ResponseEntity<CategoriaRecetaDTO> guardar(@RequestBody CategoriaRecetaDTO dto) {
        return new ResponseEntity<>(servicio.guardar(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una Categoria Receta", description = "Actualiza los datos de una Categoria Receta existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoria Receta actualizada con éxito"),
            @ApiResponse(responseCode = "404", description = "Categoria Receta no encontrada")
    })
    public ResponseEntity<CategoriaRecetaDTO> actualizar(@PathVariable Integer id, @RequestBody CategoriaRecetaDTO dto) {
        return new ResponseEntity<>(servicio.actualizar(id, dto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una Categoria Receta", description = "Elimina una Categoria Receta basado en su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Categoria Receta eliminada con éxito"),
            @ApiResponse(responseCode = "404", description = "Categoria Receta no encontrada")
    })
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        servicio.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204
    }
}