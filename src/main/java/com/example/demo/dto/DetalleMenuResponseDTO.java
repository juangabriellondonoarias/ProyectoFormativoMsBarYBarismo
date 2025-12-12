package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Respuesta con la información del detalle del menú")
public class DetalleMenuResponseDTO {

    @Schema(description = "ID del detalle de menú", example = "10")
    private Long id;

    @Schema(description = "ID del menú asociado", example = "1")
    private Long menuId;

    @Schema(description = "Cantidad solicitada", example = "2")
    private Integer cantidad;

    @Schema(description = "Subtotal calculado", example = "18000")
    private Double subtotal;

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }
}
