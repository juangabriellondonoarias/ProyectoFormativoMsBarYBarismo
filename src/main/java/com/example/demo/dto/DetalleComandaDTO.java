package com.example.demo.dto;

public class DetalleComandaDTO {

    private Integer idDetalleCocina;
    private Integer idReceta;
    private Integer cantidad;
    private String notas;
    private String estadoPlato;

    // ===== GETTERS =====

    public Integer getIdDetalleCocina() {
        return idDetalleCocina;
    }

    public Integer getIdReceta() {
        return idReceta;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public String getNotas() {
        return notas;
    }

    public String getEstadoPlato() {
        return estadoPlato;
    }

    // ===== SETTERS =====

    public void setIdDetalleCocina(Integer idDetalleCocina) {
        this.idDetalleCocina = idDetalleCocina;
    }

    public void setIdReceta(Integer idReceta) {
        this.idReceta = idReceta;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public void setEstadoPlato(String estadoPlato) {
        this.estadoPlato = estadoPlato;
    }
}
