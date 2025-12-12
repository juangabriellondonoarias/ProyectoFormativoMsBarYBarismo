package com.example.demo.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para transferencia de datos de Paso de Preparación
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PasoPreparacionDTO {
    
    private Integer idPaso;
    
    @NotNull(message = "El ID de la receta es obligatorio")
    private Integer idReceta;
    
    @NotNull(message = "El orden del paso es obligatorio")
    @Min(value = 1, message = "El orden debe ser mayor a 0")
    private Integer orden;
    
    @NotBlank(message = "La descripción del paso es obligatoria")
    @Size(min = 10, message = "La descripción debe tener al menos 10 caracteres")
    private String descripcionPaso;
    
    // Información adicional para respuestas
    private String nombreReceta;
    
    public Integer getidPaso() {
        return idPaso;
    }

    public void setidPaso(Integer idPaso) {
        this.idPaso = idPaso;
    }

    public Integer getIdReceta() {
        return idReceta;
    }

    public void setIdReceta(Integer idReceta) {
        this.idReceta = idReceta;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public String getDescripcionPaso() {
        return descripcionPaso;
    }

    public void setDescripcionPaso(String descripcionPaso) {
        this.descripcionPaso = descripcionPaso;
    }

    public String getnombreReceta() {
        return nombreReceta;
    }

    public void setnombreReceta(String nombreReceta) {
        this.nombreReceta = nombreReceta;
    }

}

/**
 * DTO simplificado para crear/actualizar múltiples pasos
 * Se usa cuando se crean todos los pasos de una receta a la vez
 */
@Data
@NoArgsConstructor
@AllArgsConstructor 
class PasoPreparacionSimpleDTO {
    
    @NotNull(message = "El orden es obligatorio")
    @Min(value = 1, message = "El orden debe ser mayor a 0")
    private Integer orden;
    
    @NotBlank(message = "La descripción es obligatoria")
    private String descripcionPaso;
}
