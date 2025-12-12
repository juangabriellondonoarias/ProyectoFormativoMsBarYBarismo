package com.example.demo.controller;

import com.example.demo.dto.PasoPreparacionDTO;
import com.example.demo.service.PasoPreparacionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/pasos-preparacion")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Service
public class PasoPreparacionController {
	
    @Autowired
    private PasoPreparacionService pasoService;
    
    /**
     * GET /api/pasos-preparacion
     * Obtener todos los pasos de preparación
     */
    
    @GetMapping
    public ResponseEntity<Map<String, Object>> obtenerTodos() {
        List<PasoPreparacionDTO> pasos = pasoService.obtenerTodos();
        
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Pasos de preparación obtenidos exitosamente");
        response.put("data", pasos);
        response.put("total", pasos.size());
        
        return ResponseEntity.ok(response);
    }
    
    /**
     * GET /api/pasos-preparacion/{id}
     * Obtener un paso por ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> obtenerPorId(@PathVariable Integer id) {
        PasoPreparacionDTO paso = pasoService.obtenerPorId(id);
        
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Paso de preparación encontrado");
        response.put("data", paso);
        
        return ResponseEntity.ok(response);
    }
    
    /**
     * GET /api/pasos-preparacion/receta/{idReceta}
     * Obtener todos los pasos de una receta (ordenados)
     */
    @GetMapping("/receta/{idReceta}")
    public ResponseEntity<Map<String, Object>> obtenerPasosPorReceta(@PathVariable Integer idReceta) {
        List<PasoPreparacionDTO> pasos = pasoService.obtenerPasosPorReceta(idReceta);
        
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Pasos de la receta obtenidos exitosamente");
        response.put("data", pasos);
        response.put("total", pasos.size());
        
        return ResponseEntity.ok(response);
    }
    
    /**
     * POST /api/pasos-preparacion
     * Crear un nuevo paso de preparación
     */
    @PostMapping
    public ResponseEntity<Map<String, Object>> crear(@Valid @RequestBody PasoPreparacionDTO pasoDTO) {
        PasoPreparacionDTO pasoCreado = pasoService.crear(pasoDTO);
        
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Paso de preparación creado exitosamente");
        response.put("data", pasoCreado);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    /**
     * POST /api/pasos-preparacion/receta/{idReceta}/siguiente
     * Crear el siguiente paso automáticamente (al final)
     */
    @PostMapping("/receta/{idReceta}/siguiente")
    public ResponseEntity<Map<String, Object>> crearSiguientePaso(
            @PathVariable Integer idReceta,
            @RequestBody Map<String, String> payload) {
        
        String descripcion = payload.get("descripcion");
        if (descripcion == null || descripcion.trim().isEmpty()) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "La descripción es obligatoria");
            return ResponseEntity.badRequest().body(errorResponse);
        }
        
        PasoPreparacionDTO pasoCreado = pasoService.crearSiguientePaso(idReceta, descripcion);
        
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Paso agregado al final de la receta");
        response.put("data", pasoCreado);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    /**
     * PUT /api/pasos-preparacion/{id}
     * Actualizar un paso completo
     */
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> actualizar(
            @PathVariable Integer id,
            @Valid @RequestBody PasoPreparacionDTO pasoDTO) {
        
        PasoPreparacionDTO pasoActualizado = pasoService.actualizar(id, pasoDTO);
        
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Paso de preparación actualizado exitosamente");
        response.put("data", pasoActualizado);
        
        return ResponseEntity.ok(response);
    }
    
    /**
     * PATCH /api/pasos-preparacion/{id}/descripcion
     * Actualizar solo la descripción de un paso
     */
    @PatchMapping("/{id}/descripcion")
    public ResponseEntity<Map<String, Object>> actualizarDescripcion(
            @PathVariable Integer id,
            @RequestBody Map<String, String> payload) {
        
        String nuevaDescripcion = payload.get("descripcion");
        if (nuevaDescripcion == null || nuevaDescripcion.trim().isEmpty()) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "La descripción es obligatoria");
            return ResponseEntity.badRequest().body(errorResponse);
        }
        
        PasoPreparacionDTO pasoActualizado = pasoService.actualizarDescripcion(id, nuevaDescripcion);
        
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Descripción actualizada exitosamente");
        response.put("data", pasoActualizado);
        
        return ResponseEntity.ok(response);
    }
    
    /**
     * DELETE /api/pasos-preparacion/{id}
     * Eliminar un paso
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> eliminar(@PathVariable Integer id) {
        pasoService.eliminar(id);
        
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Paso de preparación eliminado exitosamente");
        
        return ResponseEntity.ok(response);
    }
    
    /**
     * DELETE /api/pasos-preparacion/receta/{idReceta}
     * Eliminar todos los pasos de una receta
     */
    @DeleteMapping("/receta/{idReceta}")
    public ResponseEntity<Map<String, Object>> eliminarPasosPorReceta(@PathVariable Integer idReceta) {
        pasoService.eliminarPasosPorReceta(idReceta);
        
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Todos los pasos de la receta han sido eliminados");
        
        return ResponseEntity.ok(response);
    }
    
    /**
     * PUT /api/pasos-preparacion/receta/{idReceta}/reordenar
     * Reordenar los pasos de una receta
     * Body: { "ordenIds": [3, 1, 2, 4] }
     */
    @PutMapping("/receta/{idReceta}/reordenar")
    public ResponseEntity<Map<String, Object>> reordenarPasos(
            @PathVariable Integer idReceta,
            @RequestBody Map<String, List<Integer>> payload) {
        
        List<Integer> ordenIds = payload.get("ordenIds");
        if (ordenIds == null || ordenIds.isEmpty()) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Debe proporcionar la lista de IDs en el nuevo orden");
            return ResponseEntity.badRequest().body(errorResponse);
        }
        
        List<PasoPreparacionDTO> pasosReordenados = pasoService.reordenarPasos(idReceta, ordenIds);
        
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Pasos reordenados exitosamente");
        response.put("data", pasosReordenados);
        
        return ResponseEntity.ok(response);
    }
    
    /**
     * GET /api/pasos-preparacion/receta/{idReceta}/contar
     * Contar cuántos pasos tiene una receta
     */
    @GetMapping("/receta/{idReceta}/contar")
    public ResponseEntity<Map<String, Object>> contarPasos(@PathVariable Integer idReceta) {
        Long cantidad = pasoService.contarPasos(idReceta);
        
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Cantidad de pasos obtenida");
        response.put("cantidad", cantidad);
        
        return ResponseEntity.ok(response);
    }
    
    /**
     * GET /api/pasos-preparacion/receta/{idReceta}/buscar?q={texto}
     * Buscar pasos por texto en la descripción
     */
    @GetMapping("/receta/{idReceta}/buscar")
    public ResponseEntity<Map<String, Object>> buscarPorTexto(
            @PathVariable Integer idReceta,
            @RequestParam String q) {
        
        List<PasoPreparacionDTO> pasos = pasoService.buscarPorTexto(idReceta, q);
        
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Búsqueda completada");
        response.put("data", pasos);
        response.put("total", pasos.size());
        
        return ResponseEntity.ok(response);
    }
}
