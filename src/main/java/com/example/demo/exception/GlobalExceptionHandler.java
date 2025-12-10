package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Manejador global de excepciones
 * Captura todas las excepciones de la aplicación y devuelve respuestas consistentes
 * 
 * @RestControllerAdvice: Hace que esta clase intercepte excepciones de todos los controladores
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    
    /**
     * Maneja ResourceNotFoundException
     * Devuelve HTTP 404 (Not Found)
     * 
     * Ejemplo: GET /api/recetas/999 (no existe)
     * Respuesta: {
     *   "success": false,
     *   "message": "Receta no encontrada con ID: 999",
     *   "status": 404
     * }
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleResourceNotFound(ResourceNotFoundException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", false);
        response.put("message", ex.getMessage());
        response.put("timestamp", LocalDateTime.now());
        response.put("status", HttpStatus.NOT_FOUND.value());
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
    
    /**
     * Maneja DuplicateResourceException
     * Devuelve HTTP 409 (Conflict)
     * 
     * Ejemplo: POST /api/recetas con nombre "Pasta Carbonara" (ya existe)
     * Respuesta: {
     *   "success": false,
     *   "message": "Ya existe una receta con el nombre: Pasta Carbonara",
     *   "status": 409
     * }
     */
    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<Map<String, Object>> handleDuplicateResource(DuplicateResourceException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", false);
        response.put("message", ex.getMessage());
        response.put("timestamp", LocalDateTime.now());
        response.put("status", HttpStatus.CONFLICT.value());
        
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }
    
    /**
     * Maneja errores de validación de @Valid
     * Devuelve HTTP 400 (Bad Request)
     * 
     * Ejemplo: POST /api/recetas sin campo obligatorio "nombreReceta"
     * Respuesta: {
     *   "success": false,
     *   "message": "Error de validación",
     *   "errors": {
     *     "nombreReceta": "El nombre de la receta es obligatorio"
     *   },
     *   "status": 400
     * }
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        
        // Extraer todos los errores de validación
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        
        Map<String, Object> response = new HashMap<>();
        response.put("success", false);
        response.put("message", "Error de validación");
        response.put("errors", errors);
        response.put("timestamp", LocalDateTime.now());
        response.put("status", HttpStatus.BAD_REQUEST.value());
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
    
    /**
     * Maneja cualquier otra excepción no capturada
     * Devuelve HTTP 500 (Internal Server Error)
     * 
     * Este es el "catch-all" para errores inesperados
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGlobalException(Exception ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", false);
        response.put("message", "Error interno del servidor");
        response.put("detail", ex.getMessage());
        response.put("timestamp", LocalDateTime.now());
        response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        
        // En producción, considera registrar el stack trace en logs
        // logger.error("Error no manejado: ", ex);
        
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}