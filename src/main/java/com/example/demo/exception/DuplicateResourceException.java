package com.example.demo.exception;

/**
 * Excepción personalizada para recursos duplicados
 * Se lanza cuando se intenta crear un registro que ya existe
 */
public class DuplicateResourceException extends RuntimeException {
    
    public DuplicateResourceException(String message) {
        super(message);
    }
    
    public DuplicateResourceException(String message, Throwable cause) {
        super(message, cause);
    }
}