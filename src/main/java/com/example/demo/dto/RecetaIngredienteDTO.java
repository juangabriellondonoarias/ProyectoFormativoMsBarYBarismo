package com.example.demo.dto;

import java.math.BigDecimal;

public class RecetaIngredienteDTO {
	private Integer idIngrediente; // Solo necesitamos el ID del ingrediente del stock
    private BigDecimal cantidadRequerida;
    private String unidadMedida;
    // Opcional: si quieres mostrar el nombre al leer
    private String nombreIngrediente;
    
    public Integer getIdIngrediente() {
        return idIngrediente;
    }

    public void setIdIngrediente(Integer idIngrediente) {
        this.idIngrediente = idIngrediente;
    }

    public BigDecimal getCantidadRequerida() {
        return cantidadRequerida;
    }

    public void setCantidadRequerida(BigDecimal cantidadRequerida) {
        this.cantidadRequerida = cantidadRequerida;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public String getNombreIngrediente() {
        return nombreIngrediente;
    }

    public void setNombreIngrediente(String nombreIngrediente) {
        this.nombreIngrediente = nombreIngrediente;
    }
}
