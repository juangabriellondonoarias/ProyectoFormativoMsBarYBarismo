package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Schema(description = "Datos necesarios para crear o actualizar un detalle de menú")
public class DetalleMenuRequestDTO {

    @Schema(
        description = "ID del menú asociado",
        example = "1",
        required = true
    )
    @NotNull(message = "El ID del menú es obligatorio")
    private Long menuId;

    @Schema(
        description = "Cantidad de items del menú",
        example = "2",
        required = true
    )
    @NotNull(message = "La cantidad es obligatoria")
    @Positive(message = "La cantidad debe ser mayor que 0")
    private Integer cantidad;

    @Schema(
        description = "Subtotal calculado",
        example = "18000",
        required = true
    )
    @NotNull(message = "El subtotal es obligatorio")
    @Positive(message = "El subtotal debe ser mayor que 0")
    private Double subtotal;

    // Getters y setters
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
