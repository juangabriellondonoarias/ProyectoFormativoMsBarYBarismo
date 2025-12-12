package com.example.demo.controller;

import com.example.demo.dto.PasoPreparacionDTO;
import com.example.demo.service.PasoPreparacionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Swagger
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/api/pasos-preparacion")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Tag(name = "Pasos de Preparación", description = "Gestión de pasos de preparación para recetas")
public class PasoPreparacionController {

    @Autowired
    private PasoPreparacionService pasoService;

    // ------------------------------------------------------------
    @Operation(summary = "Obtener todos los pasos", description = "Retorna todos los pasos de preparación registrados.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente")
    })
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

    // ------------------------------------------------------------
    @Operation(summary = "Obtener un paso por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Paso encontrado"),
            @ApiResponse(responseCode = "404", description = "Paso no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> obtenerPorId(
            @Parameter(description = "ID del paso", required = true)
            @PathVariable Integer id) {

        PasoPreparacionDTO paso = pasoService.obtenerPorId(id);

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Paso de preparación encontrado");
        response.put("data", paso);

        return ResponseEntity.ok(response);
    }

    // ------------------------------------------------------------
    @Operation(summary = "Obtener pasos por receta", description = "Retorna los pasos asociados a una receta, ordenados.")
    @GetMapping("/receta/{idReceta}")
    public ResponseEntity<Map<String, Object>> obtenerPasosPorReceta(
            @Parameter(description = "ID de la receta", required = true)
            @PathVariable Integer idReceta) {

        List<PasoPreparacionDTO> pasos = pasoService.obtenerPasosPorReceta(idReceta);

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Pasos de la receta obtenidos exitosamente");
        response.put("data", pasos);
        response.put("total", pasos.size());

        return ResponseEntity.ok(response);
    }

    // ------------------------------------------------------------
    @Operation(summary = "Crear un paso")
    @PostMapping
    public ResponseEntity<Map<String, Object>> crear(
            @Valid @RequestBody PasoPreparacionDTO pasoDTO) {

        PasoPreparacionDTO pasoCreado = pasoService.crear(pasoDTO);

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Paso de preparación creado exitosamente");
        response.put("data", pasoCreado);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // ------------------------------------------------------------
    @Operation(summary = "Crear el siguiente paso automático", description = "Crea un paso incrementando el orden al final de la receta.")
    @PostMapping("/receta/{idReceta}/siguiente")
    public ResponseEntity<Map<String, Object>> crearSiguientePaso(
            @Parameter(description = "ID de la receta", required = true)
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

    // ------------------------------------------------------------
    @Operation(summary = "Actualizar un paso completo")
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> actualizar(
            @Parameter(description = "ID del paso", required = true)
            @PathVariable Integer id,
            @Valid @RequestBody PasoPreparacionDTO pasoDTO) {

        PasoPreparacionDTO pasoActualizado = pasoService.actualizar(id, pasoDTO);

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Paso de preparación actualizado exitosamente");
        response.put("data", pasoActualizado);

        return ResponseEntity.ok(response);
    }

    // ------------------------------------------------------------
    @Operation(summary = "Actualizar solo la descripción de un paso")
    @PatchMapping("/{id}/descripcion")
    public ResponseEntity<Map<String, Object>> actualizarDescripcion(
            @Parameter(description = "ID del paso", required = true)
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

    // ------------------------------------------------------------
    @Operation(summary = "Eliminar un paso")
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> eliminar(
            @Parameter(description = "ID del paso", required = true)
            @PathVariable Integer id) {

        pasoService.eliminar(id);

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Paso de preparación eliminado exitosamente");

        return ResponseEntity.ok(response);
    }

    // ------------------------------------------------------------
    @Operation(summary = "Eliminar todos los pasos de una receta")
    @DeleteMapping("/receta/{idReceta}")
    public ResponseEntity<Map<String, Object>> eliminarPasosPorReceta(
            @Parameter(description = "ID de la receta", required = true)
            @PathVariable Integer idReceta) {

        pasoService.eliminarPasosPorReceta(idReceta);

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Todos los pasos de la receta han sido eliminados");

        return ResponseEntity.ok(response);
    }

    // ------------------------------------------------------------
    @Operation(summary = "Reordenar pasos")
    @PutMapping("/receta/{idReceta}/reordenar")
    public ResponseEntity<Map<String, Object>> reordenarPasos(
            @Parameter(description = "ID de la receta", required = true)
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

    // ------------------------------------------------------------
    @Operation(summary = "Contar pasos de una receta")
    @GetMapping("/receta/{idReceta}/contar")
    public ResponseEntity<Map<String, Object>> contarPasos(
            @Parameter(description = "ID de la receta", required = true)
            @PathVariable Integer idReceta) {

        Long cantidad = pasoService.contarPasos(idReceta);

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Cantidad de pasos obtenida");
        response.put("cantidad", cantidad);

        return ResponseEntity.ok(response);
    }

    // ------------------------------------------------------------
    @Operation(summary = "Buscar pasos por texto")
    @GetMapping("/receta/{idReceta}/buscar")
    public ResponseEntity<Map<String, Object>> buscarPorTexto(
            @Parameter(description = "ID de la receta", required = true)
            @PathVariable Integer idReceta,
            @Parameter(description = "Texto a buscar", required = true)
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
