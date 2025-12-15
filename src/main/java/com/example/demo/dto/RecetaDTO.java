package com.example.demo.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecetaDTO {
    
    private Integer idReceta;
    
    @NotNull(message = "La categoría es obligatoria")
    private Integer idCategoria;
    
    @NotBlank(message = "El nombre de la receta es obligatorio")
    @Size(max = 150, message = "El nombre no puede exceder 150 caracteres")
    private String nombreReceta;
    
    @Min(value = 1, message = "Las porciones deben ser al menos 1")
    private Integer porciones;
    
    @Size(max = 50, message = "La temperatura no puede exceder 50 caracteres")
    private String temperatura;
    
    private String notasAdicionales;
    
    // Información adicional para respuestas
    private String nombreCategoria;
    private String nombreModulo;
    
    // Para crear recetas completas con ingredientes y pasos
    private List<RecetaIngredienteDTO> ingredientes;
    private List<PasoPreparacionDTO> pasos;

    public Integer getIdReceta() {
        return idReceta;
    }

    public void setIdReceta(Integer idReceta) {
        this.idReceta = idReceta;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombreReceta() {
        return nombreReceta;
    }

    public void setNombreReceta(String nombreReceta) {
        this.nombreReceta = nombreReceta;
    }

//		ESTAS 2 COMENTADAS, TENGO DUDA SI EL setTiempoPreparacion ES NECESARIO
//    public Integer getTiempoPreparacion() {
//        return tiempoPreparacion;
//    }
//
//    public void setTiempoPreparacion(Integer tiempoPreparacion) {
//        this.tiempoPreparacion = tiempoPreparacion;
//    }

    public Integer getPorciones() {
        return porciones;
    }

    public void setPorciones(Integer porciones) {
        this.porciones = porciones;
    }

    public String getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(String temperatura) {
        this.temperatura = temperatura;
    }

    public String getNotasAdicionales() {
        return notasAdicionales;
    }

    public void setNotasAdicionales(String notasAdicionales) {
        this.notasAdicionales = notasAdicionales;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public List<RecetaIngredienteDTO> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<RecetaIngredienteDTO> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public List<PasoPreparacionDTO> getPasos() {
        return pasos;
    }

    public void setPasos(List<PasoPreparacionDTO> pasos) {
        this.pasos = pasos;
    }
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class RecetaIngredienteDTO {
    private Integer idIngrediente;
    private String nombreIngrediente;
    
    @NotNull(message = "La cantidad es obligatoria")
    @DecimalMin(value = "0.01", message = "La cantidad debe ser mayor a 0")
    private Double cantidadRequerida;
    
    @NotBlank(message = "La unidad de medida es obligatoria")
    @Size(max = 50)
    private String unidadMedida;
}