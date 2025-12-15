package com.example.demo.controller;

import com.example.demo.dto.RecetaDTO;
import com.example.demo.service.RecetaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/recetas")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Tag(name = "Recetas", description = "Endpoints para la gestión de recetas en el sistema")
public class RecetaController {

    @Autowired
    private RecetaService recetaService;

    // -------------------------------------------------------------------------
    @Operation(summary = "Obtener todas las recetas",
               description = "Retorna una lista completa de todas las recetas registradas.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Listado obtenido correctamente")
    })
    @GetMapping
    public ResponseEntity<Map<String, Object>> obtenerTodas() {
        List<RecetaDTO> recetas = recetaService.obtenerTodas();

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Recetas obtenidas exitosamente");
        response.put("data", recetas);
        response.put("total", recetas.size());

        return ResponseEntity.ok(response);
    }

    // -------------------------------------------------------------------------
    @Operation(summary = "Obtener una receta por ID",
               description = "Retorna una receta específica según su identificador.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Receta encontrada"),
        @ApiResponse(responseCode = "404", description = "Receta no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> obtenerPorId(@PathVariable Integer id) {
        RecetaDTO receta = recetaService.obtenerPorId(id);

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Receta encontrada");
        response.put("data", receta);

        return ResponseEntity.ok(response);
    }

    // -------------------------------------------------------------------------
    @Operation(summary = "Obtener receta con detalles",
               description = "Retorna una receta junto con sus ingredientes y pasos de preparación.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Detalles obtenidos correctamente"),
        @ApiResponse(responseCode = "404", description = "Receta no encontrada")
    })
    @GetMapping("/{id}/detalles")
    public ResponseEntity<Map<String, Object>> obtenerConDetalles(@PathVariable Integer id) {
        RecetaDTO receta = recetaService.obtenerConDetalles(id);

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Receta con detalles obtenida exitosamente");
        response.put("data", receta);

        return ResponseEntity.ok(response);
    }

    // -------------------------------------------------------------------------
    @Operation(summary = "Crear una nueva receta",
               description = "Registra una nueva receta en el sistema.")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Receta creada exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    @PostMapping
    public ResponseEntity<Map<String, Object>> crear(@Valid @RequestBody RecetaDTO recetaDTO) {
        RecetaDTO recetaCreada = recetaService.crear(recetaDTO);

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Receta creada exitosamente");
        response.put("data", recetaCreada);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // -------------------------------------------------------------------------
    @Operation(summary = "Actualizar una receta",
               description = "Actualiza todos los datos de una receta existente.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Receta actualizada"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos"),
        @ApiResponse(responseCode = "404", description = "Receta no encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> actualizar(
            @PathVariable Integer id,
            @Valid @RequestBody RecetaDTO recetaDTO) {

        RecetaDTO recetaActualizada = recetaService.actualizar(id, recetaDTO);

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Receta actualizada exitosamente");
        response.put("data", recetaActualizada);

        return ResponseEntity.ok(response);
    }

    // -------------------------------------------------------------------------
    @Operation(summary = "Eliminar una receta",
               description = "Elimina una receta según su ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Receta eliminada"),
        @ApiResponse(responseCode = "404", description = "Receta no encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> eliminar(@PathVariable Integer id) {
        recetaService.eliminar(id);

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Receta eliminada exitosamente");

        return ResponseEntity.ok(response);
    }

    // -------------------------------------------------------------------------
    @Operation(summary = "Buscar recetas por categoría",
               description = "Devuelve todas las recetas asociadas a una categoría específica.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Búsqueda exitosa")
    })
    @GetMapping("/buscar/categoria/{idCategoria}")
    public ResponseEntity<Map<String, Object>> buscarPorCategoria(@PathVariable Integer idCategoria) {
        List<RecetaDTO> recetas = recetaService.buscarPorCategoria(idCategoria);

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Recetas encontradas por categoría");
        response.put("data", recetas);
        response.put("total", recetas.size());

        return ResponseEntity.ok(response);
    }

    // -------------------------------------------------------------------------
    @Operation(summary = "Buscar recetas por nombre",
               description = "Permite búsqueda parcial por nombre de receta.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Búsqueda exitosa")
    })
    @GetMapping("/buscar/nombre")
    public ResponseEntity<Map<String, Object>> buscarPorNombre(@RequestParam String q) {
        List<RecetaDTO> recetas = recetaService.buscarPorNombre(q);

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Búsqueda completada");
        response.put("data", recetas);
        response.put("total", recetas.size());

        return ResponseEntity.ok(response);
    }

//    // -------------------------------------------------------------------------
    
//		COMENTADA ESTA SECCION, NO LO VEO NECESARIO POR AHORA
//    @Operation(summary = "Buscar recetas por tiempo máximo",
//               description = "Devuelve recetas cuyo tiempo de preparación es igual o menor al valor indicado.")
//    @ApiResponses({
//        @ApiResponse(responseCode = "200", description = "Búsqueda exitosa")
//    })
//    @GetMapping("/buscar/tiempo")
//    public ResponseEntity<Map<String, Object>> buscarPorTiempo(@RequestParam Integer max) {
//        List<RecetaDTO> recetas = recetaService.buscarPorTiempoMaximo(max);
//
//        Map<String, Object> response = new HashMap<>();
//        response.put("success", true);
//        response.put("message", "Recetas con tiempo máximo de " + max + " minutos obtenidas");
//        response.put("data", recetas);
//        response.put("total", recetas.size());
//
//        return ResponseEntity.ok(response);
//    }
}
