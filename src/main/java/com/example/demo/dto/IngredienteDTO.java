package com.example.demo.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class IngredienteDTO {

	private Integer idIngrediente;

    @NotBlank(message = "El nombre del ingrediente es obligatorio")
    @Size(max = 100, message = "El nombre no puede exceder 100 caracteres")
    private String nombre;
    
    private int cantidad;

    @NotBlank(message = "La unidad de medida es obligatoria (ej: Kg, Lt, Unid)")
    @Size(max = 20, message = "La unidad de medida es muy larga")
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
