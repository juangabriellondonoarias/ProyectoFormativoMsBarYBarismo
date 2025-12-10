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
    
    @NotNull(message = "El módulo es obligatorio")
    private Integer idModulo;
    
    @NotBlank(message = "El nombre de la receta es obligatorio")
    @Size(max = 150, message = "El nombre no puede exceder 150 caracteres")
    private String nombreReceta;
    
    @Min(value = 1, message = "El tiempo de preparación debe ser mayor a 0")
    private Integer tiempoPreparacion;
    
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
    
	public String getNombreReceta() {
		// TODO Auto-generated method stub
		return this.nombreReceta;
	}

	public Object getIdCategoria() {
		// TODO Auto-generated method stub
		return this.idCategoria;
	}

	public Object getIdModulo() {
		// TODO Auto-generated method stub
		return this.idModulo;
	}

	public Object getTiempoPreparacion() {
		// TODO Auto-generated method stub
		return this.tiempoPreparacion;
	}

	public Object getPorciones() {
		// TODO Auto-generated method stub
		return this.porciones;
	}

	public Object getTemperatura() {
		// TODO Auto-generated method stub
		return this.temperatura;
	}

	public Object getNotasAdicionales() {
		// TODO Auto-generated method stub
		return this.notasAdicionales;
	}

	public void setIdReceta(Object idReceta2) {
		// TODO Auto-generated method stub
		
	}

	public void setIdCategoria(Object idCategoria2) {
		// TODO Auto-generated method stub
		
	}

	public void setIdModulo(Object idModulo2) {
		// TODO Auto-generated method stub
		
	}

	public void setNombreReceta(Object nombreReceta2) {
		// TODO Auto-generated method stub
		
	}

	public void setPorciones(Object porciones2) {
		// TODO Auto-generated method stub
		
	}

	public void setTiempoPreparacion(Object tiempoPreparacion2) {
		// TODO Auto-generated method stub
		
	}

	public void setTemperatura(Object temperatura2) {
		// TODO Auto-generated method stub
		
	}

	public void setNotasAdicionales(Object notasAdicionales2) {
		// TODO Auto-generated method stub
		
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

@Data
@NoArgsConstructor
@AllArgsConstructor
class PasoPreparacionDTO {
    private Integer idPaso;
    
    @NotNull(message = "El orden es obligatorio")
    @Min(value = 1, message = "El orden debe ser mayor a 0")
    private Integer orden;
    
    @NotBlank(message = "La descripción del paso es obligatoria")
    private String descripcionPaso;
}