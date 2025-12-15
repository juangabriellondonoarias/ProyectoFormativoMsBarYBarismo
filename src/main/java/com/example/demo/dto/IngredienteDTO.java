package com.example.demo.dto;
import lombok.Data;

@Data
public class IngredienteDTO {
	private Integer idIngrediente;
    private String nombre;
    private int cantidad;
    private String unidadMedidaStock;
    
    public Integer getIdIngrediente() {
        return idIngrediente;
    }

    public void setIdIngrediente(Integer idIngrediente) {
        this.idIngrediente = idIngrediente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getUnidadMedidaStock() {
        return unidadMedidaStock;
    }

    public void setUnidadMedidaStock(String unidadMedidaStock) {
        this.unidadMedidaStock = unidadMedidaStock;
    }
}