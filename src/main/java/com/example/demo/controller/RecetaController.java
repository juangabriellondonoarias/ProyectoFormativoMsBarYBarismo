package com.example.demo.controller;

import com.example.demo.dto.RecetaDTO;
import com.example.demo.service.RecetaService;
import org.springframework.web.bind.annotation.PatchMapping;

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
@RequestMapping("/api/recetas")
@CrossOrigin(origins = "*")
@Tag(name = "Receta", description = "API para la gestion de Recetas")
public class RecetaController {

    @Autowired
    private RecetaService servicio;

    @GetMapping
    @Operation(summary = "Obtener todas las recetas", description = "Devuelve una lista de todas las recetas Registradas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de recetas obtenidas con éxito"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<List<RecetaDTO>> listar() {
        return new ResponseEntity<>(servicio.listarTodas(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener Receta por ID", description = "Devuelve una receta específica basado en su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Receta encontrado"),
            @ApiResponse(responseCode = "404", description = "Receta no encontrada")
    })
    public ResponseEntity<RecetaDTO> obtenerPorId(@PathVariable Integer id) {
        return new ResponseEntity<>(servicio.obtenerPorId(id), HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Crear una nueva Receta", description = "Crea una nueva Receta con los datos proporcionados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Receta creado con éxito"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public ResponseEntity<RecetaDTO> guardar(@RequestBody RecetaDTO dto) {
        return new ResponseEntity<>(servicio.guardar(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una Receta", description = "Actualiza los datos de una Receta existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Receta actualizada con éxito"),
            @ApiResponse(responseCode = "404", description = "Receta no encontrada")
    })
    public ResponseEntity<RecetaDTO> actualizar(@PathVariable Integer id, @RequestBody RecetaDTO dto) {
        return new ResponseEntity<>(servicio.actualizar(id, dto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una Receta", description = "Elimina una Receta basado en su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Receta eliminada con éxito"),
            @ApiResponse(responseCode = "404", description = "Receta no encontrada")
    })
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        servicio.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @PatchMapping("/{id}")
    @Operation(summary = "Actualizar parcialmente una Receta", description = "Actualiza campos simples (nombre, tiempo, notas) sin afectar ingredientes ni pasos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Receta actualizada con éxito"),
            @ApiResponse(responseCode = "404", description = "Receta no encontrada")
    })
    public ResponseEntity<RecetaDTO> actualizarParcial(@PathVariable Integer id, @RequestBody RecetaDTO dto) {
        RecetaDTO actualizada = servicio.actualizarParcial(id, dto);
        return new ResponseEntity<>(actualizada, HttpStatus.OK);
    }

//    // -------------------------------------------------------------------------
//    
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
