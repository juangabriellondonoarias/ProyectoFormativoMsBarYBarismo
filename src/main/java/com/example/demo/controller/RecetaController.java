package com.example.demo.controller;

import com.example.demo.dto.RecetaDTO;
import com.example.demo.service.RecetaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
public class RecetaController {
    
    private final RecetaService recetaService;
    
    /**
     * GET /api/recetas
     * Obtener todas las recetas
     */
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
    
    /**
     * GET /api/recetas/{id}
     * Obtener una receta por ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> obtenerPorId(@PathVariable Integer id) {
        RecetaDTO receta = recetaService.obtenerPorId(id);
        
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Receta encontrada");
        response.put("data", receta);
        
        return ResponseEntity.ok(response);
    }
    
    /**
     * GET /api/recetas/{id}/detalles
     * Obtener una receta con todos sus detalles (ingredientes y pasos)
     */
    @GetMapping("/{id}/detalles")
    public ResponseEntity<Map<String, Object>> obtenerConDetalles(@PathVariable Integer id) {
        RecetaDTO receta = recetaService.obtenerConDetalles(id);
        
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Receta con detalles obtenida exitosamente");
        response.put("data", receta);
        
        return ResponseEntity.ok(response);
    }
    
    /**
     * POST /api/recetas
     * Crear una nueva receta
     */
    @PostMapping
    public ResponseEntity<Map<String, Object>> crear(@Valid @RequestBody RecetaDTO recetaDTO) {
        RecetaDTO recetaCreada = recetaService.crear(recetaDTO);
        
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Receta creada exitosamente");
        response.put("data", recetaCreada);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    /**
     * PUT /api/recetas/{id}
     * Actualizar una receta existente
     */
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
    
    /**
     * DELETE /api/recetas/{id}
     * Eliminar una receta
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> eliminar(@PathVariable Integer id) {
        recetaService.eliminar(id);
        
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Receta eliminada exitosamente");
        
        return ResponseEntity.ok(response);
    }
    
    /**
     * GET /api/recetas/buscar/categoria/{idCategoria}
     * Buscar recetas por categoría
     */
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
    
    /**
     * GET /api/recetas/buscar/modulo/{idModulo}
     * Buscar recetas por módulo
     */
    @GetMapping("/buscar/modulo/{idModulo}")
    public ResponseEntity<Map<String, Object>> buscarPorModulo(@PathVariable Integer idModulo) {
        List<RecetaDTO> recetas = recetaService.buscarPorModulo(idModulo);
        
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Recetas encontradas por módulo");
        response.put("data", recetas);
        response.put("total", recetas.size());
        
        return ResponseEntity.ok(response);
    }
    
    /**
     * GET /api/recetas/buscar/nombre?q={nombre}
     * Buscar recetas por nombre (búsqueda parcial)
     */
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
    
    /**
     * GET /api/recetas/buscar/tiempo?max={minutos}
     * Buscar recetas por tiempo máximo de preparación
     */
    @GetMapping("/buscar/tiempo")
    public ResponseEntity<Map<String, Object>> buscarPorTiempo(@RequestParam Integer max) {
        List<RecetaDTO> recetas = recetaService.buscarPorTiempoMaximo(max);
        
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Recetas encontradas con tiempo máximo de " + max + " minutos");
        response.put("data", recetas);
        response.put("total", recetas.size());
        
        return ResponseEntity.ok(response);
    }
}