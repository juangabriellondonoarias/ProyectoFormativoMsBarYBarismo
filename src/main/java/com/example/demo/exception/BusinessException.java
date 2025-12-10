package com.example.demo.exception;

/**
 * Excepción para errores de lógica de negocio
 * Se usa cuando hay reglas de negocio que no se cumplen
 */
public class BusinessException extends RuntimeException {
    
    public BusinessException(String message) {
        super(message);
    }
    
    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
