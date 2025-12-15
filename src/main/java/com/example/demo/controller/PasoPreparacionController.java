package com.example.demo.controller;

import com.example.demo.dto.PasoPreparacionDTO;
import com.example.demo.service.PasoPreparacionService;

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
@RequestMapping("/api/pasos")
@CrossOrigin(origins = "*")
@Tag(name = "Pasos de Preparacion", description = "API para la gestion de los pasos de Preparacion")
public class PasoPreparacionController {

    @Autowired
    private PasoPreparacionService servicio;

    @GetMapping("/receta/{idReceta}")
    @Operation(summary = "Obtener todos los pasos de preparacion", description = "Devuelve una lista de todos los pasos de preparacion Registrados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de pasos de preparacion obtenida con éxito"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<List<PasoPreparacionDTO>> listarPorReceta(@PathVariable Integer idReceta) {
        return new ResponseEntity<>(servicio.listarPorReceta(idReceta), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener Paso de preparacion por ID", description = "Devuelve un paso de preparacion específico basado en su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Paso de preparacion encontrado"),
            @ApiResponse(responseCode = "404", description = "Paso de preparacion  no encontrada")
    })
    public ResponseEntity<PasoPreparacionDTO> obtenerPorId(@PathVariable Integer id) {
        return new ResponseEntity<>(servicio.obtenerPorId(id), HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo paso de preparacion", description = "Crea un nuevo paso de preparacion con los datos proporcionados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Paso de preparacion creado con éxito"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public ResponseEntity<PasoPreparacionDTO> guardar(@RequestBody PasoPreparacionDTO dto) {
        return new ResponseEntity<>(servicio.guardar(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un Paso de preparacion", description = "Actualiza los datos de un paso de preparacion existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Paso de preparacion actualizada con éxito"),
            @ApiResponse(responseCode = "404", description = "Paso de preparacion no encontrada")
    })
    public ResponseEntity<PasoPreparacionDTO> actualizar(@PathVariable Integer id, @RequestBody PasoPreparacionDTO dto) {
        return new ResponseEntity<>(servicio.actualizar(id, dto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un Paso de preparacion", description = "Elimina un Paso de preparacion basado en su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Paso de preparacion eliminada con éxito"),
            @ApiResponse(responseCode = "404", description = "Paso de preparacion no encontrada")
    })
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        servicio.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
